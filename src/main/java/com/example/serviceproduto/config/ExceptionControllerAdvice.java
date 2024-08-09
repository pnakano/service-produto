package com.example.serviceproduto.config;

import com.example.serviceproduto.http.data.response.Error;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @Hidden
    @ResponseBody
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error noResult(NoResultException e){
        return new Error("X_100", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error methodArgument(MethodArgumentTypeMismatchException e){
        return new Error("X_200", "Parâmetro Inválido");
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error methodArgument(MethodArgumentNotValidException exception){
        String mensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage() + " | ")
                .collect(Collectors.joining());
        return new Error("X_200", mensagem);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error other(Exception e){
        return new Error("X_300", e.getMessage());
    }

}
