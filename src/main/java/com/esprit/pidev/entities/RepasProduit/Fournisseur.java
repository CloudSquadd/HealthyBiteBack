package com.esprit.pidev.entities.RepasProduit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
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

    @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL)
    private Set<Produit> produits;
}
