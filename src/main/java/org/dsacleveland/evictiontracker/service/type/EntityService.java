package org.dsacleveland.evictiontracker.service.type;

import java.util.List;
import java.util.Optional;

public interface EntityService<T, ID> {
    T create(T obj);

    Optional<T> readOne(ID id);

    List<T> readAll();

    Optional<T> update(ID id, T obj);

    void delete(ID id);
}
