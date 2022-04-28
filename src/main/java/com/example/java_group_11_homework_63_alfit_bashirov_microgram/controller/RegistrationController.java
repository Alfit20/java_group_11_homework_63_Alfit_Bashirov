package com.example.java_group_11_homework_63_alfit_bashirov_microgram.controller;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.UserDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.service.AuthUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final AuthUserDetailsService service;


    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> userRegistration(@RequestBody UserDto userDto) {
        try {
            service.registration(userDto);
            return new ResponseEntity<>("Успешная регистрация", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Такая почта уже существует", HttpStatus.BAD_REQUEST);
        }

    }
}
