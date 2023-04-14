package com.esprit.pidev.entities.CommandeLivraison;

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
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AdresseLivraison adresse;

    @Enumerated(EnumType.STRING)
    private EtatCommande etat;


    
}
