package com.example.lesson1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerDTO {


    private Long id;
    private String nickname;
    private String profileInfo;
    private LocalDate accountCreated;
    private LocalDate statusChanged;

}
