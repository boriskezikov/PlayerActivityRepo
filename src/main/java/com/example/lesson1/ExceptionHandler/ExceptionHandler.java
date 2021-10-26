package com.example.lesson1.ExceptionHandler;

import com.example.lesson1.ExceptionBody.ExceptionBody;
import com.example.lesson1.MyException.MyExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({MyExceptionNotFound.class, EntityNotFoundException.class})
    protected ResponseEntity<Object> entityNotFoundException(RuntimeException ex, WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody("Entity not found", ex.getMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.NOT_FOUND);
    }

}
