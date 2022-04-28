package com.example.java_group_11_homework_63_alfit_bashirov_microgram.service;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.UserDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    // Поиск пользователей по имени
    public void searchUserByName(UserDto userDto) {
        var user = userRepository.findUserByName(userDto.getName());
        if (user.isEmpty()) {
            throw new NullPointerException();
        }
    }


    // Поиск пользователей по логину
    public void searchUserByLogin(UserDto userDto) {
        var user = userRepository.findUserByLogin(userDto.getLogin());
        if (user == null) {
            log.error("Не нашел такой логин: {}", userDto.getLogin());
            throw new NullPointerException();
        }
    }

    // Поиск пользователей по емеилу
    public void searchUserByEmail(UserDto userDto) {
        var user = userRepository.findUserByEmail(userDto.getEmail());
        if (user == null) {
            log.error("Не нашел такой емеил: {}", userDto.getEmail());
            throw new NullPointerException();
        }

    }

}

