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
public class SubscriptionsPerUserDto {
    private Long id;
    @JsonProperty("subscriber_id")
    private User subscriber;
    @JsonProperty("subscription_id")
    private User subscription;
    @JsonProperty("date_added")
    private LocalDateTime eventDate;
}
