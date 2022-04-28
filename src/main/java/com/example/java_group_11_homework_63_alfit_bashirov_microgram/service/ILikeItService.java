package com.example.java_group_11_homework_63_alfit_bashirov_microgram.service;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.ILikeItDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.ILikeIt;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
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
public class ILikeItService {
    private final ILikeItRepository repository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;


    //Функция "мне понравилось" на публикации. Пользователь может отметить те публикации,
    //которые ему понравились.
//    public void likeThePost(ILikeItDto iLikeItDto) {
//        var login = userRepository.findUserByLogin(iLikeItDto.getLikeOwner().getLogin());
//        var publication = publicationRepository.findById(iLikeItDto.getPublication().getId()).get();
//        if(login == null || publication == null) {
//            throw new NullPointerException();
//        }
//        if (repository.existsByLikeOwnerAndPublication(login, publication)) {
//            log.error("Вы уже поставили лайк: {}");
//            throw new NoSuchElementException();
//        }
//        repository.save(ILikeIt.builder()
//                .dateTimeLike(iLikeItDto.getDateTimeLike())
//                .likeOwner(login)
//                .publication(publication)
//                .build());
//    }
    public void likeThePost(ILikeItDto iLikeItDto, User user) {
        var login = userRepository.findUserByEmail(user.getEmail());
        var publication = publicationRepository.findById(iLikeItDto.getPublication().getId()).get();
        if (login == null) {
            throw new NullPointerException();
        }
        if (repository.existsByLikeOwnerAndPublication(login, publication)) {
            log.error("Вы уже поставили лайк: {}");
            throw new NoSuchElementException();
        }
        repository.save(ILikeIt.builder()
                .dateTimeLike(iLikeItDto.getDateTimeLike())
                .likeOwner(login)
                .publication(publication)
                .build());
    }


}