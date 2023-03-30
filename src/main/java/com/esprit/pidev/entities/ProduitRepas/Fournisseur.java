package com.esprit.pidev.entities.ProduitRepas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fournisseur extends Commerciale {

    @OneToMany(mappedBy = "fournisseur")
    @JsonIgnore
    private Set<Produit> produits;
}
