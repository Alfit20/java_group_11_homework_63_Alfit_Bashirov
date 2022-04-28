package com.example.java_group_11_homework_63_alfit_bashirov_microgram.controller;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.ILikeItDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.service.ILikeItService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class ILikeItController {
    private final ILikeItService service;


    @PostMapping
    public ResponseEntity<String> getLike(@RequestBody ILikeItDto iLikeItDto, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            service.likeThePost(iLikeItDto, user);
            return new ResponseEntity<>("Поставили лайк", HttpStatus.OK);
        } catch (NoSuchElementException n) {
            return new ResponseEntity<>("Неправильная публикация или пользователь", HttpStatus.BAD_REQUEST);
        } catch (NullPointerException re) {
            return new ResponseEntity<>("Вы уже поставили лайк", HttpStatus.BAD_REQUEST);
        }
    }
}
