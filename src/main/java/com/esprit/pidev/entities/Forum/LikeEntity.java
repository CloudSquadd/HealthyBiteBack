package com.esprit.pidev.entities.Forum;


import com.esprit.pidev.entities.UserRole.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likeDislike")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @JsonBackReference
    private Comment comment;

    private boolean liked;

    public LikeEntity(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public LikeEntity(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }


    // constructors, getters, and setters
}
