package org.dsacleveland.evictiontracker.service.mapper;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.PartyDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.service.evictiondata.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DataAwarePartyMapper implements DtoMapper<PartyEntity, PartyDto> {

    private AddressService addressService;

    private PartyMapper partyMapper;

    @Autowired
    public DataAwarePartyMapper(AddressService addressService) {
        this.addressService = addressService;
        this.partyMapper = PartyMapper.INSTANCE;
    }

    @Override
    public PartyDto toDto(PartyEntity entity) {
        return this.partyMapper.toDto(entity);
    }

    @Override
    public PartyEntity toEntity(PartyDto partyDto) {
        PartyEntity entity = this.partyMapper.toEntity(partyDto);

        entity.setAddress(this.addressService.findOrCreateNew(entity.getAddress()));

        return entity;
    }
}
