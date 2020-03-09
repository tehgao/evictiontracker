package org.dsacleveland.evictiontracker.service.mapper.legacy;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.AddressDto;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.Address;
import org.dsacleveland.evictiontracker.service.mapper.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LegacyAddressMapper extends DtoMapper<AddressDto, Address> {
    LegacyAddressMapper INSTANCE = Mappers.getMapper(LegacyAddressMapper.class);

    @Override
    @Mapping(source = "streetAddressSecondary", target = "streetAddress2")
    @Mapping(source = "zipCode", target = "zip")
    @Mapping(target = "id", ignore = true)
    Address toDto(AddressDto addressEntity);

    @Override
    @Mapping(source = "streetAddress2", target = "streetAddressSecondary")
    @Mapping(source = "zip", target = "zipCode")
    AddressDto toEntity(Address entity);
}
