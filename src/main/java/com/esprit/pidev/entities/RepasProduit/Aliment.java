package com.esprit.pidev.entities.RepasProduit;

import javax.persistence.*;

@MappedSuperclass
public abstract class Aliment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private String ingredient;

}
