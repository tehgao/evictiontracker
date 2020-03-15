package org.dsacleveland.evictiontracker.service.mapper;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.service.evictiondata.AddressService;
import org.dsacleveland.evictiontracker.service.evictiondata.PartyService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Primary
public class DataAwareCaseMapper implements DtoMapper<CaseEntity, CaseDto> {

    private AddressService addressService;
    private PartyService partyService;

    private CaseMapper caseMapper;

    public DataAwareCaseMapper(AddressService addressService, PartyService partyService) {
        this.addressService = addressService;
        this.partyService = partyService;
        this.caseMapper = CaseMapper.INSTANCE;
    }

    @Override
    public CaseDto toDto(CaseEntity entity) {
        return this.caseMapper.toDto(entity);
    }

    @Override
    public CaseEntity toEntity(CaseDto caseDto) {
        CaseEntity mapped = this.caseMapper.toEntity(caseDto);

        mapped.setPlaintiffs(mapped
                .getPlaintiffs().stream()
                .map(party -> this.partyService.findOrCreateNew(party))
                .collect(Collectors.toList())
        );

        mapped.setDefendants(mapped
                .getDefendants().stream()
                .map(party -> this.partyService.findOrCreateNew(party))
                .collect(Collectors.toList())
        );

        mapped.setProperty(
                this.addressService.findOrCreateNew(mapped.getProperty())
        );

        return mapped;
    }


}
