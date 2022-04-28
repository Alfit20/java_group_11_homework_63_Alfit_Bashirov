package com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Comment;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Publication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByPublication(Publication publication);

}
