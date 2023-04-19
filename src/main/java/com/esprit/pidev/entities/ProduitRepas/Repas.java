package com.esprit.pidev.entities.ProduitRepas;

import com.esprit.pidev.entities.UserRole.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Repas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private String ingredient;
    private String allergene;
    private LocalDateTime dateAjout;
    @Enumerated(EnumType.STRING)
    private  ObjectifType objectif;

    @Column(name = "image_url")
    private String imageUrl;

    @Transient
    private MultipartFile imageFile;


    @Enumerated(EnumType.STRING)
    private CategRepas CategorieRep;
    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "repas", cascade = CascadeType.ALL)
    private Nutrition nutrition;
}
