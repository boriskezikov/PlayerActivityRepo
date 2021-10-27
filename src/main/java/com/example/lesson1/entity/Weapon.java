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
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weapon_sea")
    @SequenceGenerator(name = "weapon_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String detailedName;

    private Integer range;

    private Integer damage;

    private Boolean broken;
}
