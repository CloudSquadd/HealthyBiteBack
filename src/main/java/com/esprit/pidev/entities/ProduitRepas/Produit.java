package com.esprit.pidev.entities.ProduitRepas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private String ingredient;
    @Enumerated(EnumType.STRING)
    private CategProduit categoriePro;

    @ManyToOne
    @JsonIgnore
    private Fournisseur fournisseur;


    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    private Nutrition nutrition;
}
