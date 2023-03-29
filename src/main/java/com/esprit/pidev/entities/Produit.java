package com.esprit.pidev.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Produit extends Aliment {

    @ManyToOne
    private Fournisseur fournisseur;


    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    private Nutrition nutrition;
}
