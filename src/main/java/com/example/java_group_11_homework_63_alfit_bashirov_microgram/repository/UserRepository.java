package com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // Поиск пользователя по имени
    List<User> findUserByName(String name);

    Optional<User> getByEmail(String email);


    // Поиск пользователя по логину
    User findUserByLogin(String login);

    // Поиск пользователя по емеилу
    User findUserByEmail(String email);


    // Поиск пользователя по паролю
    User findUserByPassword(String password);

    // Проверка на наличие пользователя в системе на основе email
    boolean existsUserByEmail(String email);


}