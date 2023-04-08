package com.esprit.pidev.entities.Forum;

import com.esprit.pidev.entities.User;

import javax.persistence.*;

@Entity
public class LikeDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    private Comment comment;



    // Getters and Setters
}
