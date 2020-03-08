
package org.dsacleveland.evictiontracker.model.evictiondata.legacy;

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
    private String streetAddress2;
    private String zip;

}
