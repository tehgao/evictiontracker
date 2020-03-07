package org.dsacleveland.evictiontracker.repository;

import org.dsacleveland.evictiontracker.model.evictiondata.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
}
