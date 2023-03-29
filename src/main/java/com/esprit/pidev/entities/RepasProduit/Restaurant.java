package com.esprit.pidev.entities.RepasProduit;

import com.esprit.pidev.entities.RepasProduit.Commerciale;
import com.esprit.pidev.entities.RepasProduit.Repas;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Restaurant extends Commerciale {

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Repas> repas;
}
