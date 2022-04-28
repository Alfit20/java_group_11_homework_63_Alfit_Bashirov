package com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.SubscriptionsPerUser;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsPerUserRepository extends CrudRepository<SubscriptionsPerUser, Long> {

    boolean existsSubscriptionsPerUsersBySubscriberAndSubscription(User subscriber, User subscription);

}