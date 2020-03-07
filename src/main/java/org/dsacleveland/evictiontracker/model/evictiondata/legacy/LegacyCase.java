
package org.dsacleveland.evictiontracker.model.evictiondata.legacy;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class LegacyCase {

    private List<Object> additionalParties;
    private String caseNumber;
    private List<Defendant> defendants;
    private List<EventSet> eventSet;
    private String fileDate;
    private long id;
    private List<Plaintiff> plaintiffs;

}
