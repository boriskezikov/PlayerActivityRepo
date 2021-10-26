package com.example.lesson1.MyException;

public class MyExceptionNotFound extends RuntimeException {
    public MyExceptionNotFound(long id) {
        super("Exception not found, id=" + id);
    }
}
