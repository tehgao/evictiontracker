package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Address;

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
}
