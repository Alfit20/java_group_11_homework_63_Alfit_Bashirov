package com.example.java_group_11_homework_63_alfit_bashirov_microgram.controller;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.CommentDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            commentService.addComment(commentDto, user);
            return new ResponseEntity<>("Добавлен комментарий", HttpStatus.OK);
        } catch (NoSuchElementException npe) {
            return new ResponseEntity<>("Ошибка! Не удалось добавить комментарий", HttpStatus.BAD_REQUEST);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Ошибка! Сначала нужно поставить лайк", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            commentService.deleteComment(commentId, user);
            return new ResponseEntity<>("Удален комментарий", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Не нашел этот комментарий", HttpStatus.BAD_REQUEST);
        }
    }
}
