package org.dsacleveland.evictiontracker.model.evictiondata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Party;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PartyEntity extends AbstractAuditable<User, UUID> implements Party<AddressEntity, AttorneyEntity> {
    private String name;

    @ManyToOne
    private AddressEntity address;

    @OneToOne
    private AttorneyEntity attorney;
}
