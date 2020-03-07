
package org.dsacleveland.evictiontracker.model.evictiondata.legacy;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Plaintiff {

    private Address address;
    private List<AttorneySet> attorneySet;
    private String name;

}
