package com.example.lesson1.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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



    // orpahnRemoval - delete all childs that loose relation with parent
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Weapon> weapons;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}
