package com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "like_table")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ILikeIt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "like_owner_id")
    private User likeOwner;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @Column(name = "date_added")
    private LocalDateTime dateTimeLike;

}