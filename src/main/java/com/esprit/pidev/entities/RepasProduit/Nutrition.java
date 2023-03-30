package com.esprit.pidev.entities.RepasProduit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JsonIgnore
    private Produit produit;

    @OneToOne
    @JsonIgnore
    private Repas repas;

}
