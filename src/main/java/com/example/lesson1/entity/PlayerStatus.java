package com.example.lesson1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerStatus {

    ACTIVE(true),
    INACTIVE(false);

    private boolean val;
}
