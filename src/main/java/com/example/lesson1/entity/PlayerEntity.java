package com.example.lesson1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_entity_id_seq")
    @SequenceGenerator(name = "player_entity_id_seq", allocationSize = 1, sequenceName = "PLAYER_ENTITY_ID_SEQ")
    private Long id;

    private String nickName;

    private boolean terminated;

    private String profileInfo;

    private LocalDate accountCreated;

    private LocalDate statusChanged;

}
