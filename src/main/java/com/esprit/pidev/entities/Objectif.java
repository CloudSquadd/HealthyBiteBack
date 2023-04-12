package com.esprit.pidev.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Objectif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long poidDepart;
    private Long poidActuel;
    private Long taille;
    private Long objectifPoid;

    private TypeActivite typeActivite;

}
