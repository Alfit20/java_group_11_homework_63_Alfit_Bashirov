package com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.ILikeIt;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Publication;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeItRepository extends CrudRepository<ILikeIt, Long> {


    // Проверка на установку отметки "мне нравится" на публикацию.
    boolean existsByLikeOwnerAndPublication(User user, Publication publication);

    List<ILikeIt> findAllByPublication(Publication publication);


}
