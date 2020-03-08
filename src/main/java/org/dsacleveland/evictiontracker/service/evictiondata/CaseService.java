package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.dsacleveland.evictiontracker.repository.CaseRepository;
import org.dsacleveland.evictiontracker.service.mapper.CaseMapper;
import org.dsacleveland.evictiontracker.service.type.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService extends AbstractEntityService<CaseEntity, CaseDto, CaseRepository> {

    @Autowired
    public CaseService(CaseRepository repository) {
        super(repository, CaseMapper.INSTANCE);
    }

    public List<CaseDto> importFromLegacy(List<LegacyCase> cases) {


        return null;
    }
}
