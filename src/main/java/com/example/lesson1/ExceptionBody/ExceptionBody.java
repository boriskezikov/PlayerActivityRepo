package com.example.lesson1.ExceptionBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionBody {

    private String message;
    private String debugMessage;

}
