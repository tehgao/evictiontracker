
package org.dsacleveland.evictiontracker.model.evictiondata.legacy;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Address {

    private String city;
    private long id;
    private String state;
    private String streetAddress;
    private String streetAddress2;
    private String zip;

}
