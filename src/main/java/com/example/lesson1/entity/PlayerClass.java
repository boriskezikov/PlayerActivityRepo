package com.example.lesson1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerClass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_class_seq")
    @SequenceGenerator(name = "player_class_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;


}
