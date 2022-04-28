package com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publications")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    private String description;
    @Column(name = "publication_time")
    private LocalDate publicationTime;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;


}
