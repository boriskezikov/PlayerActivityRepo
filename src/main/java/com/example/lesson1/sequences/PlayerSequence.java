package com.example.lesson1.sequences;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerSequence {

    private AtomicLong currentID;

}
