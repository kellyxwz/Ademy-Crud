package com.example.academy.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerGlobal {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){

        String erro = ("Recurso não encontrado");
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse err = new ErrorResponse(
                Instant.now(),
                erro,
                status.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e, HttpServletRequest request){

        ErrorResponse error = new ErrorResponse(
                Instant.now(),
                "Erro interno do Servidor",
                500,
                request.getRequestURI()
        );

        return ResponseEntity.status(500).body(error);
    }



}
