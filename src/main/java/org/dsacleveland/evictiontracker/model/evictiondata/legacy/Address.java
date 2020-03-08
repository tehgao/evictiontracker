
package org.dsacleveland.evictiontracker.model.evictiondata.legacy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Address {

    private String city;
    private long id;
    private String state;
    private String streetAddress;

    @JsonProperty("street_address_2")
    private String streetAddress2;
    private String zip;

}
