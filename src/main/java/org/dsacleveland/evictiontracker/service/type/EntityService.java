package org.dsacleveland.evictiontracker.service.type;

import java.util.List;
import java.util.Optional;

public interface EntityService<T, DTO, ID> {
    T create(DTO obj);

    Optional<T> readOne(ID id);

    List<T> readAll();

    Optional<T> update(ID id, DTO obj);

    void delete(ID id);
}
