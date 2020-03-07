package org.dsacleveland.evictiontracker.model.evictiondata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Case;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CaseEntity extends AbstractAuditable<User, UUID> implements Case<PartyEntity, EventEntity> {
    private String caseNumber;
    private LocalDate fileDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<PartyEntity> plaintiffs;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<PartyEntity> defendants;

    @OneToMany
    private List<EventEntity> events;
}
