
package org.dsacleveland.evictiontracker.model.evictiondata.legacy;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class EventSet {

    private long assocCase;
    private String dateTime;
    private String eventType;
    private long id;
    private Boolean isProSe;

}
