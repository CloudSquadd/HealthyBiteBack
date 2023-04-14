package com.esprit.pidev.entities.ProduitRepas;

import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;
import com.esprit.pidev.entities.UserRole.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Repas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private String ingredient;
    private String allergene;
    private Boolean bloquee=false;

    @Enumerated(EnumType.STRING)
    private CategRepas CategorieRep;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "repas", cascade = CascadeType.ALL)
    private Nutrition nutrition;

    @OneToMany(mappedBy = "repas",cascade = CascadeType.ALL)
    private Set<Reclamation> reclamations;
}
