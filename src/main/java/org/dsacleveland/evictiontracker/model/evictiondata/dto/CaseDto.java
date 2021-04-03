package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDto {
    private String caseNumber;
    private LocalDate fileDate;

    private List<PartyDto> plaintiffs;

    private List<PartyDto> defendants;
    private List<EventDto> events;

    private AddressDto property;

    final static public String[] HEADERS = new String[]{"case_number","file_date", "event_type", "date_time", "defendant",
            "defendant_attorney",
            "plaintiff", "plantiff_attorney",
            "street_address", "street_address_secondary", "city", "zip_code"};

    public List<String> toCSV() {
        List csv = new ArrayList<>();
        csv.add(caseNumber);
        csv.add(fileDate);
        csv.addAll(EventDto.toCSV(events));
        csv.addAll(PartyDto.toCSV(defendants, false));
        csv.addAll(PartyDto.toCSV(plaintiffs, false));
        csv.addAll(AddressDto.toCSV(property));
        return csv;
    }
}