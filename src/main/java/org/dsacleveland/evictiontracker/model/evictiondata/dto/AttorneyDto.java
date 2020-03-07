package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Attorney;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttorneyDto implements Attorney {
    private String name;
}
