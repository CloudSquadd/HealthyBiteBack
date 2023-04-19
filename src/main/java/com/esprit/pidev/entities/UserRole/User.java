package com.esprit.pidev.entities.UserRole;

import com.esprit.pidev.entities.CommandeLivraison.AdresseLivraison;
import com.esprit.pidev.entities.Objectif;
import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.LikeDislike;
import com.esprit.pidev.entities.Forum.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Integer tel;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )

    private Set<Role> role;
    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    private Long maxCalories;

    private String besoin;

    private Long poids;
    @Enumerated(EnumType.STRING)
    private GenderType Gender;

    private int age;
    private Long perdrePoids;
    private Long taille;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdresseLivraison> addresses;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Repas> repas;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Produit> produits;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikeDislike> likeDislikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Objectif> objectifs = new ArrayList<>();




}
