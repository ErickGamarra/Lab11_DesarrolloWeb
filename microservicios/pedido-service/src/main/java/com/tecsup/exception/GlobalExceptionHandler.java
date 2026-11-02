package com.tecsup.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErrores(
            MethodArgumentNotValidException ex) {


        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errores.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );

                });


        return ResponseEntity.badRequest()
                .body(errores);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> manejarNoEncontrado(
            ResourceNotFoundException ex){


        Map<String,String> error = new HashMap<>();

        error.put("mensaje", ex.getMessage());
        error.put("codigo","404");


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Map<String,String>> manejarServicioCaido(
            ResourceAccessException ex){


        Map<String,String> error = new HashMap<>();

        error.put("mensaje", "No se pudo conectar con el otro microservicio");
        error.put("codigo","500");


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
