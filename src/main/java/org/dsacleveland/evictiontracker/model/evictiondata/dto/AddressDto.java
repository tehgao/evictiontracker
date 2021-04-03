package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Address;

import java.util.Arrays;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Address {
    private String streetAddress;
    private String streetAddressSecondary;
    private String city;
    private String state;
    private String zipCode;

    public static Collection toCSV(AddressDto property) {
        return Arrays.asList(property.streetAddress, property.streetAddressSecondary, property.city, property.zipCode);
    }
}
