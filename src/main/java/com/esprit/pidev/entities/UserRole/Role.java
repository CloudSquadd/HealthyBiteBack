package com.esprit.pidev.entities.UserRole;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    private Integer roleId;
    private String roleDescription;
}
