package org.dsacleveland.evictiontracker.model.auth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "evictions_user")
public class User {
    @Id
    private String userId;
}
