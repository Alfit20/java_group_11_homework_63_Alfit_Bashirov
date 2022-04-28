package com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("login")
    private String login;
    @NotNull
    @JsonProperty("email")
    private String email;
    @NotNull
    @JsonProperty("password")
    private String password;

    @JsonProperty("count_publications")
    private Integer countPublications;

    // Количество подписчиков
    @JsonProperty("count_subscribers")
    private Integer countSubscribers;

    // Количество подписок
    @JsonProperty("count_subscribes")
    private Integer countSubscribes;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .countPublications(user.getCountPublications())
                .countSubscribers(user.getCountSubscribers())
                .countSubscribes(user.getCountSubscribes())
                .build();
    }
}
