package com.esprit.pidev.entities.RepasProduit;

import com.esprit.pidev.entities.RepasProduit.Aliment;
import com.esprit.pidev.entities.RepasProduit.CategProduit;
import com.esprit.pidev.entities.RepasProduit.Fournisseur;
import com.esprit.pidev.entities.RepasProduit.Nutrition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit extends Aliment {

    @Enumerated(EnumType.STRING)
    private CategProduit categoriePro;

    @ManyToOne
    @JsonIgnore
    private Fournisseur fournisseur;


    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    private Nutrition nutrition;
}
