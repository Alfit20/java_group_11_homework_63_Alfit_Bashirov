package com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto;

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
public class CommentDto {
    private Long id;
    @JsonProperty("comment_text")
    private String commentText;
    @JsonProperty("date_added")
    private LocalDate dateTimeComment;
    @JsonProperty("author_id")
    private User author;
    @JsonProperty("publication_id")
    private PublicationDto publication;
}