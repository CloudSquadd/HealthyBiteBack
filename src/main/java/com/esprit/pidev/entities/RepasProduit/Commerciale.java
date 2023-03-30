package com.esprit.pidev.entities.RepasProduit;

import javafx.scene.input.DataFormat;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Commerciale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private Long telephone;

    private String email;
    private String password;

}
