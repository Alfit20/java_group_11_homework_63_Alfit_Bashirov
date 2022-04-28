package com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ILikeItDto {
    private Long id;
    @JsonProperty("like_owner")
    private User likeOwner;
    @JsonProperty("publication")
    private PublicationDto publication;
    @JsonProperty("date_time_like")
    private LocalDateTime dateTimeLike;

}