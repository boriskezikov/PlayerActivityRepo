package com.example.lesson1.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "players_seq")
    @SequenceGenerator(name = "players_seq", allocationSize = 1)
    private Long id;

    private String nickName;

    private boolean terminated;

    private String profileInfo;
}
