package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.dsacleveland.evictiontracker.repository.CaseRepository;
import org.dsacleveland.evictiontracker.service.mapper.CaseMapper;
import org.dsacleveland.evictiontracker.service.mapper.legacy.LegacyCaseMapper;
import org.dsacleveland.evictiontracker.service.type.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseService extends AbstractEntityService<CaseEntity, CaseDto, CaseRepository> {

    @Autowired
    public CaseService(CaseRepository repository) {
        super(repository, CaseMapper.INSTANCE);
    }

    @Transactional
    public List<CaseDto> importFromLegacy(List<LegacyCase> cases) {
        LegacyCaseMapper legacyCaseMapper = LegacyCaseMapper.INSTANCE;

        return cases.stream()
                    .map(legacyCase -> this.repository.save(legacyCaseMapper.toEntity(legacyCase)))
                    .map(caseEntity -> this.mapper.toDto(caseEntity))
                    .collect(Collectors.toList());
    }
}
