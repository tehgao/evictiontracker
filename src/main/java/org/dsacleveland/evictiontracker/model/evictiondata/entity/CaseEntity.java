package org.dsacleveland.evictiontracker.model.evictiondata.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Case;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CaseEntity extends AbstractAuditable<User, UUID> implements Case<PartyEntity, EventEntity> {
    @Column(unique = true)
    private String caseNumber;

    private LocalDate fileDate;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<PartyEntity> plaintiffs;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<PartyEntity> defendants;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EventEntity> events;
}
