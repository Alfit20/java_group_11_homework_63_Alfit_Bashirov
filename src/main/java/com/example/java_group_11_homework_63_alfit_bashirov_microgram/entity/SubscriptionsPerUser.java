package com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscribe")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SubscriptionsPerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber; // Подписчик

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private User subscription; // Подписка

    @Column(name = "date_added")
    private LocalDateTime eventDate;

}
