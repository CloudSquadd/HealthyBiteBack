package com.esprit.pidev.entities.ProduitRepas;

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
