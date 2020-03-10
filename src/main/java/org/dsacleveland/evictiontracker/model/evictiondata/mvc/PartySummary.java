package org.dsacleveland.evictiontracker.model.evictiondata.mvc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartySummary {
    private String id;

    private String name;
    private String address;

    private int numCasesPlaintiffOf;
    private int numCasesDefendantOf;
}
