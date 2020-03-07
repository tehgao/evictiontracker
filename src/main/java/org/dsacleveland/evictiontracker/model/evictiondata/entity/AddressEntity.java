package org.dsacleveland.evictiontracker.model.evictiondata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.auth.User;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Address;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity extends AbstractAuditable<User, UUID> implements Address {
    private String streetAddress;
    private String streetAddressSecondary;
    private String city;
    private String state;
    private String zipCode;
}