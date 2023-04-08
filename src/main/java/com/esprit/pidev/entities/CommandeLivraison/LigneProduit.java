package com.esprit.pidev.entities.CommandeLivraison;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLP;

    @ManyToOne
    @JsonIgnore
    private Produit produit;

    @ManyToOne
    @JsonIgnore
    private Commande commande;

    private int quantite;
}
