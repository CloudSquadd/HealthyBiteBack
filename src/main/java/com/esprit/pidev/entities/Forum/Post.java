package com.esprit.pidev.entities.Forum;

import com.esprit.pidev.entities.UserRole.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(length = 100, nullable = true, columnDefinition = "VARCHAR(100) DEFAULT 'Untitled'")
    @JsonProperty

    private String title;

    @JsonProperty
    private String content;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty
    private Date addedDate = new Date();


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnoreProperties("posts")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("posts")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonProperty
    @JoinTable(
            name = "likeDislike",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();



    @Column(name = "image_data")
    @Lob
    private byte[] imageData;


    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_path")
    private String imagePath;


    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    private Integer likeCount;
    private  Integer dislikeCount;




    // Getter and Setter for comments
    public List<Comment> getComments() {
        return comments;
    }

    public Integer getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}