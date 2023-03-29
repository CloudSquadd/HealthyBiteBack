package com.esprit.pidev.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Restaurant extends Commerciale{

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Repas> repas;
}
