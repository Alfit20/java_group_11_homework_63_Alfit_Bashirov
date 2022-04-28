package com.example.java_group_11_homework_63_alfit_bashirov_microgram.service;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.PublicationDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Publication;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.CommentRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.ILikeItRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.PublicationRepository;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ILikeItRepository likeItRepository;


    //Функция добавления новой публикаций (картинки и текстового описания). Пользователь
    //может загрузить любое количество публикаций.
    public Publication addImage(MultipartFile file, String email, String description) {
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        var user = userRepository.findUserByEmail(email);
        user.setCountPublications(user.getCountPublications() + 1);
        var image = Publication.builder()
                .image(data)
                .user(user)
                .description(description)
                .build();
        image.setPublicationTime(LocalDate.now());
        return publicationRepository.save(image);
    }


    public ResponseEntity<Resource> getById(Long id) {
        var image = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication image with " + id + " doesn't exists!"));
//        return new ByteArrayResource(image.getImage());
        var resource = new ByteArrayResource(image.getImage());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(resource);
    }

    //Удаление публикаций. Пользователь может удалить только свою публикацию. При
    //удалении публикации так же удаляются все связанные с ней комментарии.
    public void deletePublication(Long publicationId, User user) {
        Publication publication = publicationRepository.findById(publicationId).orElseThrow();
        if (!publication.getUser().equals(user)) {
            throw new NoSuchElementException("AccessDenied");
        }
        var likes = likeItRepository.findAllByPublication(publication);
        likeItRepository.deleteAll(likes);
        var comments = commentRepository.findAllByPublication(publication);
        commentRepository.deleteAll(comments);
        publicationRepository.delete(publication);
        user.setCountPublications(user.getCountPublications() - 1);
        userRepository.save(user);
    }


    //Просмотр лент других пользователей. Мы можем просмотреть публикации, которые сделал
    //этот пользователь. Проверяйте в постмане
    public List<PublicationDto> anotherUsersFeed(PublicationDto publicationDto) {
        var login = userRepository.findUserByLogin(publicationDto.getUser().getLogin());
        if (login == null) {
            log.error("Не нашел юзера: {}");
            throw new NullPointerException();
        }
        var publication = publicationRepository.findByUser(login);
        List<PublicationDto> publicationDtos = new ArrayList<>();
        publication.stream().forEach(a -> publicationDtos.add(PublicationDto.toDto(a)));
        return publicationDtos;
    }

    // Просмотр лент пользователя на которого мы подписаны
    public List<PublicationDto> viewFeedsWhoAreFollowing(User user) {
        var login = userRepository.findUserByLogin(user.getLogin());
        if (login == null) {
            log.error("Не нашел юзера: {}");
            throw new NullPointerException();
        }
        var publication = publicationRepository.subscriptions(login);
        List<PublicationDto> publicationDtos = new ArrayList<>();
        publication.stream().forEach(a -> publicationDtos.add(PublicationDto.toDto(a)));
        return publicationDtos;
    }


}
