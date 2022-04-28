package com.example.java_group_11_homework_63_alfit_bashirov_microgram.util;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitDatabase {
    private final PasswordEncoder encoder;

    @Bean
    CommandLineRunner init(UserRepository userRepository, CommentRepository commentRepository,
                           ILikeItRepository iLikeItRepository, PublicationRepository publicationRepository,
                           SubscriptionsPerUserRepository subscriptionsPerUserRepository) {
        return (args) -> {
            commentRepository.deleteAll();
            iLikeItRepository.deleteAll();
            publicationRepository.deleteAll();
            subscriptionsPerUserRepository.deleteAll();
            userRepository.deleteAll();


            User user1 = new User();
            user1.setEmail("anton.a@gmail.com");
            user1.setName("Anton");
            user1.setCountPublications(0);
            user1.setCountSubscribers(0);
            user1.setCountSubscribes(0);
            user1.setLogin("anton.a");
            user1.setPassword(encoder.encode("qwe"));
            userRepository.save(user1);

            User user2 = new User();
            user2.setEmail("pavel.p@gmail.com");
            user2.setName("Pavel");
            user2.setCountPublications(0);
            user2.setCountSubscribers(0);
            user2.setCountSubscribes(0);
            user2.setLogin("pavel.p");
            user2.setPassword(encoder.encode("asd"));
            userRepository.save(user2);

            User user3 = new User();
            user3.setEmail("sergey.s@gmail.com");
            user3.setName("Sergey");
            user3.setCountPublications(0);
            user3.setCountSubscribers(0);
            user3.setCountSubscribes(0);
            user3.setLogin("sergey.s");
            user3.setPassword(encoder.encode("zxc"));
            userRepository.save(user3);
        };
    }

}