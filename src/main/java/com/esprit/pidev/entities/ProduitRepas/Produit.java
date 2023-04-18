package com.esprit.pidev.entities.ProduitRepas;

import com.esprit.pidev.entities.ConseilRecette.TypeActivite;
import com.esprit.pidev.entities.UserRole.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit implements Serializable {

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
    private User user;

    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Nutrition nutrition;
}
