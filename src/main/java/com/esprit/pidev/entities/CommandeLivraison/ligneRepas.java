package com.esprit.pidev.entities.CommandeLivraison;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneRepas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLR;

    @ManyToOne
    @JsonIgnore
    private Repas repas;

    @ManyToOne
    @JoinColumn(name = "idCom")
    @JsonIgnore
    private Commande commande;

    private int quantite;
}
