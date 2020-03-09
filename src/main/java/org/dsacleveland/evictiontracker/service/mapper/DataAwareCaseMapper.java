package org.dsacleveland.evictiontracker.service.mapper;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.AddressDto;
import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.AddressEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.repository.PartyRepository;
import org.dsacleveland.evictiontracker.service.evictiondata.AddressServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Primary
public class DataAwareCaseMapper implements DtoMapper<CaseEntity, CaseDto> {

    private AddressServiceImpl addressService;
    private PartyRepository partyRepository;

    private CaseMapper caseMapper;

    public DataAwareCaseMapper(AddressServiceImpl addressService, PartyRepository partyRepository) {
        this.addressService = addressService;
        this.partyRepository = partyRepository;
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
                .map(party -> this.mapParty(party))
                .collect(Collectors.toList())
        );

        mapped.setDefendants(mapped
                .getDefendants().stream()
                .map(party -> this.mapParty(party))
                .collect(Collectors.toList())
        );

        return mapped;
    }

    public PartyEntity mapParty(PartyEntity entity) {
        return this.partyRepository
                .findByName(entity.getName())
                .orElseGet(() -> {
                    entity.setAddress(this.mapAddress(entity.getAddress()));
                    return entity;
                });
    }

    public AddressEntity mapAddress(AddressEntity entity) {
        return this.addressService
                .findOne(Example.of(
                        entity,
                        ExampleMatcher
                                .matching()
                                .withIgnorePaths("createdBy", "createdDate", "lastModifiedBy",
                                        "lastModifiedDate", "id", "new")
                        )
                )
                .orElseGet(() -> addressService
                        .create(AddressDto
                                .builder()
                                .streetAddress(entity.getStreetAddress())
                                .streetAddressSecondary(entity
                                        .getStreetAddressSecondary())
                                .city(entity.getCity())
                                .state(entity.getState())
                                .zipCode(entity.getZipCode())
                                .build()
                        )
                );
    }
}
