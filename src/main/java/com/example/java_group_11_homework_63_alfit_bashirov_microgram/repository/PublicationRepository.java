package com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Publication;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, Long> {

    // Выборка публикаций других пользователей.
    List<Publication> findByUser(User user);

    // Выборка публикаций для своей ленты на основе подписок на других пользователей.
    @Query("select p from Publication p inner join SubscriptionsPerUser s on p.user = s.subscription where s.subscriber = :user")
    List<Publication> subscriptions(@Param("user") User user);

    Publication findPublicationByIdAndUserId(Long id, Long userId);
}