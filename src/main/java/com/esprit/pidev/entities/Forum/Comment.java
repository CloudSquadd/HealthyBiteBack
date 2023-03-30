package com.esprit.pidev.entities.Forum;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String commentText;
    private Date commentDate;

}
