package com.esprit.pidev.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Fournisseur extends Commerciale{

    @OneToMany(mappedBy = "fournisseur")
    private Set<Produit> produits;
}
