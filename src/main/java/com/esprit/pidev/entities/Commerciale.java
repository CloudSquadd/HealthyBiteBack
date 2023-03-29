package com.esprit.pidev.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class Commerciale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;

    private Long telephone;
}
