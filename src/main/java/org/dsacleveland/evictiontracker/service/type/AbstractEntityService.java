package org.dsacleveland.evictiontracker.service.type;

import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.service.mapper.DtoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractEntityService<T extends AbstractAuditable<User, UUID>,
        DTO, R extends JpaRepository<T, UUID>> implements EntityService<DTO, UUID> {

    protected R repository;

    protected DtoMapper<T, DTO> mapper;

    @Autowired
    public AbstractEntityService(R repository, DtoMapper<T, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DTO create(DTO obj) {
        return this.mapper.toDto(
                this.repository.save(this.mapper.toEntity(obj))
        );
    }

    @Override
    public Optional<DTO> readOne(UUID uuid) {
        return this.repository
                .findById(uuid)
                .map(obj -> this.mapper.toDto(obj));
    }

    @Override
    public List<DTO> readAll() {
        return this.repository
                .findAll()
                .stream()
                .map(obj ->
                        this.mapper.toDto(obj)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DTO> update(UUID uuid, DTO dto) {
        return this.repository
                .findById(uuid)
                .map(result -> {
                    T entity = this.mapper.toEntity(dto);
                    BeanUtils.copyProperties(entity, result);
                    return this.mapper.toDto(this.repository.save(result));
                });
    }

    @Override
    public void delete(UUID uuid) {
        this.repository.deleteById(uuid);
    }
}
