package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.dsacleveland.evictiontracker.service.type.EntityService;

import java.util.List;
import java.util.UUID;

public interface CaseService extends EntityService<CaseEntity, CaseDto, UUID> {
    List<CaseEntity> importFromLegacy(List<LegacyCase> cases);
}
