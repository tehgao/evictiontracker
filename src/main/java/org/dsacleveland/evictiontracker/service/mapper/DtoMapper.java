package org.dsacleveland.evictiontracker.service.mapper;

public interface DtoMapper<T, DTO> {
    DtoMapper INSTANCE = null;

    DTO toDto(T entity);

    T toEntity(DTO dto);
}
