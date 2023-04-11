package com.esprit.pidev.entities.UserRole;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Integer roleId;
    private String roleDescription;
}
