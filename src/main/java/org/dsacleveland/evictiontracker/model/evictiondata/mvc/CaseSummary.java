package org.dsacleveland.evictiontracker.model.evictiondata.mvc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseSummary {
    private String id;

    private String caseNumber;
    private LocalDate fileDate;

    private String plaintiffs;
    private String defendants;
}
