package com.example.java_group_11_homework_63_alfit_bashirov_microgram.service;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.SubscriptionsPerUserDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.SubscriptionsPerUser;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.PublicationRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.SubscriptionsPerUserRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionsPerUserService {
    private final SubscriptionsPerUserRepository subscriptionsPerUserRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    //Иметь возможность подписываться на других пользователей, что бы видеть их публикации
    //у себя в ленте. Метод который показывает публикации сделан в PublicationService
    public void subscribeToOtherUsers(SubscriptionsPerUserDto subscriptionsPerUserDto, User user) {
        var loginSubscribers = userRepository.findUserByLogin(user.getLogin());
        var loginSubscribed = userRepository.findUserByLogin(subscriptionsPerUserDto.getSubscription().getLogin());
        if (loginSubscribers == null || loginSubscribed == null || loginSubscribers == loginSubscribed) {
            log.error("Не удалось подписаться так как не нашел юзера: {}");
            throw new NullPointerException();
        }
        if (subscriptionsPerUserRepository.existsSubscriptionsPerUsersBySubscriberAndSubscription(loginSubscribers, loginSubscribed)) {
            log.error("Не удалось подписаться так как вы уже подписались: {}");
            throw new RuntimeException();
        }
        subscriptionsPerUserDto.setSubscriber(loginSubscribers);
        subscriptionsPerUserDto.getSubscriber().setCountSubscribers(subscriptionsPerUserDto.getSubscriber().getCountSubscribers() + 1);
        subscriptionsPerUserDto.setSubscription(loginSubscribed);
        subscriptionsPerUserDto.getSubscription().setCountSubscribes(subscriptionsPerUserDto.getSubscription().getCountSubscribes() + 1);
        subscriptionsPerUserRepository.save(SubscriptionsPerUser.builder()
                .id(subscriptionsPerUserDto.getId())
                .subscriber(loginSubscribers)
                .subscription(loginSubscribed)
                .eventDate(subscriptionsPerUserDto.getEventDate())
                .build());
    }
}
