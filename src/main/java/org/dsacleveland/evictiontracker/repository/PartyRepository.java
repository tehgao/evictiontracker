package org.dsacleveland.evictiontracker.repository;

import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartyRepository extends JpaRepository<PartyEntity, UUID> {
    Optional<PartyEntity> findByName(String name);
}
