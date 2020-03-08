package org.dsacleveland.evictiontracker.service.mapper.legacy;

import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.Defendant;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.Plaintiff;
import org.dsacleveland.evictiontracker.service.mapper.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LegacyAddressMapper.class})
public interface LegacyCaseMapper extends DtoMapper<CaseEntity, LegacyCase> {
    LegacyCaseMapper INSTANCE = Mappers.getMapper(LegacyCaseMapper.class);

    @Override
    @Mapping(target = "id", ignore = true)
    LegacyCase toDto(CaseEntity entity);

    @Override
    CaseEntity toEntity(LegacyCase legacyCase);

    @Mapping(target = "attorney", expression = "java(attorneySet.get(0))")
    List<PartyEntity> listPlaintiffToEntity(List<Plaintiff> plaintiffs);

    @Mapping(target = "attorney", expression = "java(attorneySet.get(0))")
    List<PartyEntity> listDefendantToEntity(List<Defendant> defendants);
}
