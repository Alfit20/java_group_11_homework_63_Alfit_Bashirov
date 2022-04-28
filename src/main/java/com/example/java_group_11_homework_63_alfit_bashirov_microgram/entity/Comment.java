package com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "date_added")
    private LocalDate dateTimeComment;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;


}


