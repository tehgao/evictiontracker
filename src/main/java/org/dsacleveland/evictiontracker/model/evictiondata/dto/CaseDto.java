package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDto {
    private String caseNumber;
    private LocalDate fileDate;

    private List<PartyDto> plaintiffs;

    private List<PartyDto> defendants;
    private List<EventDto> events;
}
