package org.dsacleveland.evictiontracker.model.evictiondata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Party;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PartyEntity extends AbstractAuditable<User, UUID> implements Party<AddressEntity, AttorneyEntity> {
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "plaintiffs")
    @JsonBackReference
    List<CaseEntity> isPlaintiffOf;

    @ManyToMany(mappedBy = "defendants")
    @JsonBackReference
    List<CaseEntity> isDefendantOf;

    @ManyToOne
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    private AttorneyEntity attorney;
}
