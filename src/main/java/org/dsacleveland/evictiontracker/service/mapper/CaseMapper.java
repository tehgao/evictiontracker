package org.dsacleveland.evictiontracker.service.mapper;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CaseMapper extends DtoMapper<CaseEntity, CaseDto> {
    CaseMapper INSTANCE = Mappers.getMapper(CaseMapper.class);

    @Override
    CaseDto toDto(CaseEntity caseEntity);

    @Override
    CaseEntity toEntity(CaseDto caseDto);
}
