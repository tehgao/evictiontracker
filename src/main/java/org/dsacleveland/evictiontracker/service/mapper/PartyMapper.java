package org.dsacleveland.evictiontracker.service.mapper;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.PartyDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartyMapper extends DtoMapper<PartyEntity, PartyDto> {
    PartyMapper INSTANCE = Mappers.getMapper(PartyMapper.class);

    @Override
    PartyDto toDto(PartyEntity entity);

    @Override
    PartyEntity toEntity(PartyDto partyDto);
}
