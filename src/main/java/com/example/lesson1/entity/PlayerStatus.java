package com.example.lesson1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayerStatus {

    active(true),
    inactive(false);

    private boolean val;

}
