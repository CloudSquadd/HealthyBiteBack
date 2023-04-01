package com.esprit.pidev.entities.ProduitRepas;

import com.esprit.pidev.entities.CommandeLivraison.AdresseLivraison;
import com.esprit.pidev.entities.UserRole.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Repas> repas;

    @OneToOne
    private AdresseLivraison adresse;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



}
