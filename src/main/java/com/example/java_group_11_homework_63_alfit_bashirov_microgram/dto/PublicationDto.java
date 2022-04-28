package com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.Publication;
import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {
    private Long id;
    @JsonProperty("image")
    private String image;
    @JsonProperty("description")
    private String description;
    @JsonProperty("publication_time")
    private LocalDate publicationTime;
    @JsonProperty("user")
    private User user;

    public static PublicationDto toDto(Publication publication) {
        return PublicationDto.builder()
                .id(publication.getId())
                .image(publication.getImage().toString())
                .description(publication.getDescription())
                .publicationTime(publication.getPublicationTime())
                .user(publication.getUser())
                .build();
    }
}
