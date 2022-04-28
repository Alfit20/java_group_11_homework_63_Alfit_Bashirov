package com.example.java_group_11_homework_63_alfit_bashirov_microgram.service;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.CommentDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Comment;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Publication;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.CommentRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.ILikeItRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.PublicationRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ILikeItRepository iLikeItRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;


    //Добавление комментариев к своей или чужой публикации. Пользователь оставляет
    //комментарии к понравившимся ему публикациям.
    public void addComment(CommentDto commentDto, User user) {
        var login = userRepository.findUserByEmail(user.getEmail());
        var publication = publicationRepository.findById(commentDto.getPublication().getId()).get();
        if (login == null || publication == null) {
            throw new NoSuchElementException();
        }
        // Если лайк стоит то добавляем комментарий
//        if (iLikeItRepository.existsByLikeOwnerAndPublication(login, publication)) {
        commentRepository.save(Comment.builder()
                .commentText(commentDto.getCommentText())
                .dateTimeComment(commentDto.getDateTimeComment())
                .author(login)
                .publication(publication)
                .build());
//        } else {
//            throw new NullPointerException();
//        }

    }

    //Удаление комментариев. Можно удалить любые комментарии, но только под своей
    //публикацией.
//    public void deleteComment(CommentDto commentDto, User user) {
//        var comment = commentRepository.findCommentByIdAndPublicationIdAndPublicationUserId(commentDto.getId(), commentDto.getPublication().getId(), user.getId());
//        if (comment == null) {
//            log.error("Не нашел этот комментарий: {}", commentDto.getId());
//            throw new NullPointerException();
//        }
//        commentRepository.delete(comment);
//    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        Publication publication = comment.getPublication();
        if (!publication.getUser().equals(user)) {
            throw new NoSuchElementException("access denied");
        }
        commentRepository.delete(comment);
    }
}