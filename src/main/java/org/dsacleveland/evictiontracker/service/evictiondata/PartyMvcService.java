package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.PartyDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.mvc.PartySummary;
import org.dsacleveland.evictiontracker.repository.PartyRepository;
import org.dsacleveland.evictiontracker.service.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PartyMvcService extends PartyServiceImpl {
    @Autowired
    public PartyMvcService(PartyRepository repository, DtoMapper<PartyEntity, PartyDto> mapper) {
        super(repository, mapper);
    }

    public Page<PartySummary> getPaginatedPartySummaries(Pageable pageable) {
        return this.repository.findAll(pageable).map(this::mapToPartySummary);
    }

    private PartySummary mapToPartySummary(PartyEntity partyEntity) {
        return PartySummary
                .builder()
                .id(partyEntity.getId().toString())
                .name(partyEntity.getName())
                .address(partyEntity.getAddress().toPrintableString())
                .numCasesDefendantOf(partyEntity.getIsDefendantOf().size())
                .numCasesPlaintiffOf(partyEntity.getIsPlaintiffOf().size())
                .build();
    }
}
