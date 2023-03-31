package com.esprit.pidev.entities.ProduitRepas;

import com.esprit.pidev.entities.UserRole.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class Fournisseur extends User {

    @OneToMany(mappedBy = "fournisseur")
    @JsonIgnore
    private Set<Produit> produits;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
