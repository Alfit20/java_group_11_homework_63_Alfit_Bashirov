package com.example.java_group_11_homework_63_alfit_bashirov_microgram.controller;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.PublicationDto;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Publication;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/publication")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Publication addPublication(@RequestParam("file") MultipartFile file, Authentication authentication, String description) {
        try {
            User user = (User) authentication.getPrincipal();
            return publicationService.addImage(file, user.getEmail(), description);
        } catch (NullPointerException npe) {
            return null;
        }
    }


    @PostMapping("{publicationId}")
    public ResponseEntity<String> deletePublication(@PathVariable Long publicationId, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            publicationService.deletePublication(publicationId, user);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException npe) {
            return new ResponseEntity<>("Не нашел эту публикацию или юзера", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> serveFile(@PathVariable Long imageId) {
        try {
            return publicationService.getById(imageId);
        } catch (RuntimeException e) {
            return null;
        }
    }


    @GetMapping("/view")
    public ResponseEntity<List<PublicationDto>> viewingPublicationsOfOthersUsers(@RequestBody PublicationDto publicationDto) {
        try {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE)
                    .body(publicationService.anotherUsersFeed(publicationDto));
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/subscription")
    public ResponseEntity<List<PublicationDto>> viewingPublicationsBasedOnSubscriptions(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE)
                    .body(publicationService.viewFeedsWhoAreFollowing(user));
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}