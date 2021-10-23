package com.example.lesson1.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerEntity {

    @Id
    private Long id;

    private String nickName;

    private boolean terminated;

    private String profileInfo;

}
