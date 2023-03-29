package com.esprit.pidev.entities;

import javax.persistence.*;

@Entity
public class Repas extends Aliment{
    private String allergene;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToOne(mappedBy = "repas", cascade = CascadeType.ALL)
    private Nutrition nutrition;
}
