package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Party;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyDto implements Party<AddressDto, AttorneyDto> {
    private String name;
    private AddressDto address;
    private AttorneyDto attorney;

    // name, attorney, address
    public static Collection<String> toCSV(List<PartyDto> parties, boolean withAddress) {
        Collection<String> result = new ArrayList<String>();
        String name = parties.stream().map(PartyDto::getName)
                .filter(Objects::nonNull)
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining(" & "));
        result.add(name);
        String attorney = parties.stream()
                .map(PartyDto::getAttorney)
                .filter(Objects::nonNull)
                .map(AttorneyDto::getName)
                .filter(Objects::nonNull)
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining(" & "));
        result.add(attorney);

        if (withAddress) {
            String address1 = String.join(" & ", parties.stream()
                    .map(PartyDto::getAddress)
                    .map(AddressDto::getStreetAddress)
                    .distinct()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.equals(""))
                    .toArray(String[]::new));
            String address2 = String.join(" & ", parties.stream()
                    .map(PartyDto::getAddress)
                    .map(AddressDto::getStreetAddressSecondary)
                    .distinct()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.equals(""))
                    .toArray(String[]::new));
            String city = String.join(" & ", parties.stream()
                    .map(PartyDto::getAddress)
                    .map(AddressDto::getCity)
                    .distinct()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.equals(""))
                    .toArray(String[]::new));
            String zip = String.join(" & ", parties.stream()
                    .map(PartyDto::getAddress)
                    .map(AddressDto::getZipCode)
                    .distinct()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.equals(""))
                    .toArray(String[]::new));
            result.add(address1);
            result.add(address2);
            result.add(city);
            result.add(zip);
        }
        return result;
    }
}
