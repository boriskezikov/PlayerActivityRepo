package com.example.lesson1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerDTO {

//тест
    private Long id;
    private String nickname;
    private String description;

}
