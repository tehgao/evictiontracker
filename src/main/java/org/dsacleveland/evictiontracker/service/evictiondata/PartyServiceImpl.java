package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.PartyDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.repository.PartyRepository;
import org.dsacleveland.evictiontracker.service.mapper.DtoMapper;
import org.dsacleveland.evictiontracker.service.mapper.PartyMapper;
import org.dsacleveland.evictiontracker.service.type.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class PartyServiceImpl extends AbstractEntityService<PartyEntity, PartyDto, PartyRepository> implements PartyService {

    @Autowired
    public PartyServiceImpl(PartyRepository repository, DtoMapper<PartyEntity, PartyDto> mapper) {
        super(repository, mapper);
    }

    @Override
    public Optional<PartyEntity> findByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public PartyEntity findOrCreateNew(PartyEntity entity) {
        return this
                .findByName(entity.getName())
                .orElseGet(() ->
                        this.create(PartyMapper.INSTANCE.toDto(entity))
                );
    }
}
