package com.esprit.pidev.entities.CommandeLivraison;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCom")
    private Long idCom;

   //@ManyToOne
    //@JoinColumn(name = "client_id")
    //private Client client;

    @OneToMany
    private List<LigneProduit> lignesProduit;
    @OneToMany
    private List<LigneRepas> lignesrepas;

    @OneToOne
    @JsonIgnore
    private Livraison livraison;

    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    private Date dateCommande;



}
