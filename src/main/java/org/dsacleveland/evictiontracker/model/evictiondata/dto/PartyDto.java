package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Party;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyDto implements Party<AddressDto, AttorneyDto> {
    private String name;
    private AddressDto address;
    private AttorneyDto attorney;
}
