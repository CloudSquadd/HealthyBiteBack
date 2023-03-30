package com.esprit.pidev.entities.ProduitRepas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Repas extends Aliment {
    private String allergene;

    @Enumerated(EnumType.STRING)
    private CategRepas CategorieRep;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @OneToOne(mappedBy = "repas", cascade = CascadeType.ALL)
    private Nutrition nutrition;
}
