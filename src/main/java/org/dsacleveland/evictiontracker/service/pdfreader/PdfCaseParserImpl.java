package org.dsacleveland.evictiontracker.service.pdfreader;

import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.text.WordUtils;
import org.dsacleveland.evictiontracker.model.evictiondata.dto.*;
import org.dsacleveland.evictiontracker.model.evictiondata.type.EventType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@XSlf4j
public class PdfCaseParserImpl implements PdfCaseParser {
    @Override
    public List<CaseDto> parseCasesFromPdfText(String pdfText) {
        final List<String> rawLines = pdfText.lines().collect(Collectors.toList());

        List<Integer> pageBoundaries = Stream
                .concat(rawLines
                        .stream()
                        .filter(line -> line.matches(".*Legal News: New Cases.*"))
                        .map(line -> rawLines.indexOf(line)), Stream.of(rawLines.size()))
                .collect(Collectors.toList());

        List<String> lines = IntStream
                .range(0, pageBoundaries.size() - 1)
                .mapToObj(idx -> new ImmutablePair<Integer, Integer>(pageBoundaries.get(idx) + 4, pageBoundaries
                        .get(idx + 1)))
                .map(pair -> rawLines.subList(pair.getLeft(), pair.getRight()))
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        List<Integer> partitionIndices = Stream
                .concat(lines
                        .stream()
                        .filter(line -> line.matches("20(\\d{2}) CVG (\\d+).+ADMINH"))
                        .map(line -> lines.indexOf(line)), Stream.of(lines.size()))
                .collect(Collectors.toList());

        List<List<String>> casePartitioned = this.partition(lines, partitionIndices);

        return casePartitioned
                .stream()
                .map(this::processCasePartition)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<CaseDto> processCasePartition(List<String> partition) {
        List<Integer> partitionIndices;
        try {
            partitionIndices = Stream
                    .concat(Stream.of(0),
                            Stream.concat(Stream
                                    .of("PLAINTIFF", "DEFENDANT", "Additional Party", "File Date", "Event Type")
                                    .map(key -> partition
                                            .stream()
                                            .filter(line -> line.startsWith(key))
                                            .findFirst()
                                            .orElseThrow(() ->
                                                    new IllegalArgumentException("Partition " + partition + " contains malformed data")
                                            )
                                    )
                                    .map(partition::indexOf), Stream.of(partition.size())))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            log.error("Encountered malformed data, on " + partition + ", skipping");
            return Optional.empty();
        }

        List<List<String>> segments = this.partition(partition, partitionIndices);


        CaseDto caseDto = new CaseDto();
        Matcher caseNumberMatcher = Pattern.compile(".*(\\d{4} CVG \\d{6}).*").matcher(segments.get(0).get(0));
        if (caseNumberMatcher.matches()) {
            caseDto.setCaseNumber(caseNumberMatcher.group(1));
        } else {
            log.error("Could not parse case number from " + segments.get(0));
        }
        caseDto.setPlaintiffs(this.processPartyListSubpartition(segments.get(1)));
        caseDto.setDefendants(this.processPartyListSubpartition(segments.get(2)));
        caseDto.setProperty(this.processPartySubpartition(segments.get(3).subList(1, segments.get(3).size()))
                                .map(party -> party.getAddress())
                                .orElse(caseDto.getDefendants().get(0).getAddress())
        );
        Matcher dateMatcher = Pattern.compile(".*(\\d{2}/\\d{2}/\\d{4}).*").matcher(segments.get(4).get(0));
        if (dateMatcher.matches()) {
            caseDto.setFileDate(LocalDate.parse(dateMatcher.group(1), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } else {
            log.error("Could not parse case file date from " + segments.get(4));
        }
        caseDto.setEvents(this.processEventsSubpartition(segments.get(5)));

        return Optional.of(caseDto);
    }

    private List<EventDto> processEventsSubpartition(List<String> subpartition) {
        List<String> lines = subpartition.subList(1, subpartition.size());

        return lines
                .stream()
                .map(line -> Pattern
                        .compile(".*(ATTY)?.+(FIRST|SECOND).+(\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}).*")
                        .matcher(line))
                .filter(Matcher::matches)
                .map(matcher -> EventDto
                        .builder()
                        .proSe(!(matcher.group(1) != null && matcher.group(1).equals("ATTY")))
                        .eventType(matcher.group(2).equals("FIRST") ? EventType.FC : EventType.SC)
                        .dateTime(LocalDateTime
                                .parse(matcher.group(3), DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")))
                        .build())
                .collect(Collectors.toList());
    }

    private List<PartyDto> processPartyListSubpartition(List<String> subpartition) {
        List<String> lines = subpartition.subList(1, subpartition.size());

        Set<String> partitionLines = lines
                .stream()
                .filter(line -> line.matches("[a-zA-Z ]+, [A-Z]{2} \\d{5}"))
                .collect(Collectors.toSet());

        List<Integer> partitionIndices = Stream
                .concat(Stream.of(-1), IntStream
                        .range(0, lines.size())
                        .filter(idx -> partitionLines.contains(lines.get(idx)))
                        .boxed())
                .map(idx -> idx + 1)
                .collect(Collectors.toList());

        return this
                .partition(lines, partitionIndices)
                .stream()
                .map(this::processPartySubpartition)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<PartyDto> processPartySubpartition(List<String> subpartition) {
        if (subpartition.size() < 3) {
            log.error("Could not read badly formed party " + subpartition);
            return Optional.empty();
        }

        PartyDto partyDto = new PartyDto();
        partyDto.setName(WordUtils.capitalizeFully(subpartition.get(0)));

        Pattern attorney = Pattern.compile("(.+?) ([\\w']+( (I+|JR|SR))? ESQ, .+)");

        AddressDto addressDto = new AddressDto();
        Matcher m = attorney.matcher(subpartition.get(1));
        if (m.matches()) {
            addressDto.setStreetAddress(WordUtils.capitalizeFully(m.group(1)));
            partyDto.setAttorney(new AttorneyDto(WordUtils.capitalizeFully(m.group(2))));
        } else {
            addressDto.setStreetAddress(WordUtils.capitalizeFully(subpartition.get(1)));
        }

        if (subpartition.size() == 4) {
            addressDto.setStreetAddressSecondary(WordUtils.capitalizeFully(subpartition.get(2)));
        } else {
            Matcher secondary = Pattern
                    .compile("(.*)\\s((#|up|down|dn|unit|apt|apartment|suite|ste)[ \\d].*)", Pattern.CASE_INSENSITIVE)
                    .matcher(addressDto.getStreetAddress());
            if (secondary.matches()) {
                addressDto.setStreetAddressSecondary(secondary.group(2));
                addressDto.setStreetAddress(secondary.group(1).trim());
            }
        }

        Pattern cityStateZip = Pattern.compile("(.+), ([A-Z]{2}) (.+)");
        Matcher csz = cityStateZip.matcher(subpartition.get(subpartition.size() - 1));
        if (!csz.matches()) {
            log.error("Could not parse city, state, and/or zip code for party " + subpartition);
            return Optional.empty();
        } else {
            addressDto.setCity(WordUtils.capitalizeFully(csz.group(1)));
            addressDto.setState(csz.group(2));
            addressDto.setZipCode(WordUtils.capitalizeFully(csz.group(3)));
        }

        partyDto.setAddress(addressDto);

        return Optional.of(partyDto);
    }

    private <T> List<List<T>> partition(List<T> input, List<Integer> partitionIndices) {
        return IntStream
                .range(0, partitionIndices.size() - 1)
                .mapToObj(i ->
                        new ImmutablePair<Integer, Integer>(partitionIndices.get(i), partitionIndices.get(i + 1))
                )
                .map(idxPair -> input.subList(idxPair.getLeft(), idxPair.getRight()))
                .collect(Collectors.toList());
    }
}
