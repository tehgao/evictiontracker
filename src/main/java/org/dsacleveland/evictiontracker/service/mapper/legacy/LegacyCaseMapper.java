package org.dsacleveland.evictiontracker.service.mapper.legacy;

import org.dsacleveland.evictiontracker.model.evictiondata.entity.AttorneyEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.EventEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.*;
import org.dsacleveland.evictiontracker.service.mapper.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Mapper(uses = {LegacyAddressMapper.class})
public interface LegacyCaseMapper extends DtoMapper<CaseEntity, LegacyCase> {
    LegacyCaseMapper INSTANCE = Mappers.getMapper(LegacyCaseMapper.class);

    @Override
    @Mapping(target = "id", ignore = true)
    LegacyCase toDto(CaseEntity entity);

    @Override
    @Mapping(source = "eventSet", target = "events")
    CaseEntity toEntity(LegacyCase legacyCase);

    List<PartyEntity> listPlaintiffToEntity(List<Plaintiff> plaintiffs);

    List<PartyEntity> listDefendantToEntity(List<Defendant> defendants);

    @Mapping(source = "attorneySet", target = "attorney")
    PartyEntity plaintiffToEntity(Plaintiff plaintiff);

    @Mapping(source = "attorneySet", target = "attorney")
    PartyEntity defendantToEntity(Defendant defendant);

    List<EventEntity> eventSetToEvent(List<EventSet> eventSets);

    default LocalDateTime zonedStringToDate(String dateString) {
        return ZonedDateTime.parse(dateString)
                            .withZoneSameInstant(ZoneId.of("America/New_York"))
                            .toLocalDateTime();
    }

    default AttorneyEntity listToAttorney(List<AttorneySet> attorneys) {
        return Optional.ofNullable(attorneys)
                       .filter(atts -> atts.size() > 0)
                       .map(attorney -> attorney.get(0))
                       .map(at -> AttorneyEntity.builder().name(at.getName()).build())
                       .orElse(null);
    }
}
