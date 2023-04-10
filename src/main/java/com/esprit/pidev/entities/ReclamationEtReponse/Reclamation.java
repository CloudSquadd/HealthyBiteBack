package com.esprit.pidev.entities.ReclamationEtReponse;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamation;

    @Temporal(TemporalType.DATE )
    private Date dateReclamation;
    private String textReclamation;
    private String etatReclamation;
    @Column(columnDefinition = "false")
    private Boolean archived;

    @OneToOne
    private Notification notifications;

    @OneToOne
    private ReponseReclamation reponseReclamation;

}
