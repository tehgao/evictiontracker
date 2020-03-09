package org.dsacleveland.evictiontracker.model.evictiondata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Address;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Coordinate;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AddressEntity extends AbstractAuditable<User, UUID> implements Address {
    private String streetAddress;
    private String streetAddressSecondary;
    private String city;
    private String state;
    private String zipCode;

    private String neighborhood;
    private Coordinate coordinates;
}