package org.dsacleveland.evictiontracker.model.evictiondata.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Event;
import org.dsacleveland.evictiontracker.model.evictiondata.type.EventType;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EventEntity extends AbstractAuditable<User, UUID> implements Event {

    private EventType eventType;
    private boolean proSe;
    private LocalDateTime dateTime;
}
