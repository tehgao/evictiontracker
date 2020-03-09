package org.dsacleveland.evictiontracker.service.mapper;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.AddressDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper extends DtoMapper<AddressEntity, AddressDto> {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressEntity toEntity(AddressDto dto);

    AddressDto toDto(AddressEntity entity);
}
