package com.example.java_group_11_homework_63_alfit_bashirov_microgram.entity;

import com.example.java_group_11_homework_63_alfit_bashirov_microgram.dto.CustomAuthorityDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_table")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    private String email;
    private String password;

    // Количество публикаций
    @Column(name = "count_publication")
    private Integer countPublications;

    // Количество подписчиков
    @Column(name = "count_subscribers")
    private Integer countSubscribers;

    // Количество подписок
    @Column(name = "count_subscribes")
    private Integer countSubscribes;

    public User(String name, String login, String email, String password, Integer countPublications, Integer countSubscribers, Integer countSubscribes) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.countPublications = countPublications;
        this.countSubscribers = countSubscribers;
        this.countSubscribes = countSubscribes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("FULL"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
