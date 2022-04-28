package com.example.java_group_11_homework_63_alfit_bashirov_microgram.controller;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.UserDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.service.AuthUserDetailsService;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 36000)
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthUserDetailsService service;

    @GetMapping("/name")
    public ResponseEntity<String> getName(@RequestBody UserDto userDto) {
        try {
            userService.searchUserByName(userDto);
            return new ResponseEntity<>(userDto.getName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Не нашел такое имя", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> auth(Authentication authentication) {
        try {
            authentication.getPrincipal();
            return new ResponseEntity<>("Вы успешно авторизовались", HttpStatus.OK);
        } catch (NullPointerException npe) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/login")
    public ResponseEntity<String> getLogin(@RequestBody UserDto userDto) {
        try {
            userService.searchUserByLogin(userDto);
            return new ResponseEntity<>(userDto.getLogin(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Не нашел такой логин", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/email")
    public ResponseEntity<String> getEmail(@RequestBody UserDto userDto) {
        try {
            userService.searchUserByEmail(userDto);
            return new ResponseEntity<>(userDto.getEmail(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Не нашел такой email", HttpStatus.BAD_REQUEST);
        }
    }
}
