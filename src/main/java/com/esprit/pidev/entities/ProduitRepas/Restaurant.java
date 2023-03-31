package com.esprit.pidev.entities.ProduitRepas;

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
public class Restaurant extends User{

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Repas> repas;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
