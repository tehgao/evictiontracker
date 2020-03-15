package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.PartyDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.service.type.EntityService;

import java.util.Optional;
import java.util.UUID;

public interface PartyService extends EntityService<PartyEntity, PartyDto, UUID> {
    Optional<PartyEntity> findByName(String name);

    PartyEntity findOrCreateNew(PartyEntity partyEntity);
}
