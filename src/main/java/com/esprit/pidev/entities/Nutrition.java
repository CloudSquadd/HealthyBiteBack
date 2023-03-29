package com.esprit.pidev.entities;

import javax.persistence.*;

@Entity
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double energies;
    private double acides;
    private double glucides;
    private double sucres;
    private double fibre;
    private double calories;
    private double proteines;
    private double lipides;
    private double sel;

    @OneToOne
    private Produit produit;

    @OneToOne
    private Repas repas;

}
