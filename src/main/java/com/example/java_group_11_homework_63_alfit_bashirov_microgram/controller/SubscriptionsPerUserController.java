package com.example.java_group_11_homework_63_alfit_bashirov_microgram.controller;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.SubscriptionsPerUserDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.service.SubscriptionsPerUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionsPerUserController {
    private final SubscriptionsPerUserService subscriptionsPerUserService;


    @PostMapping
    public ResponseEntity<String> sign(@RequestBody SubscriptionsPerUserDto dto, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            subscriptionsPerUserService.subscribeToOtherUsers(dto, user);
            return new ResponseEntity<>("Спасибо за подписку", HttpStatus.OK);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>("Не удалось подписаться так как не нашел юзера", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException re) {
            return new ResponseEntity<>("Не удалось подписаться так как вы уже подписаны", HttpStatus.BAD_REQUEST);
        }
    }
}

