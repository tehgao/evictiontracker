package org.dsacleveland.evictiontracker.model.auth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private String userId;
}
