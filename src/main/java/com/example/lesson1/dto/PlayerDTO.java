package com.example.lesson1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerDTO {

    private Long id;
    private String nickname;
    private String description;
    private LocalDateTime accountCreated;
    private LocalDateTime accountUpdated;
}
