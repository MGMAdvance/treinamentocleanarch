package br.com.treinamento.cleanarch.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import br.com.treinamento.cleanarch.configuration.exception.DataBaseException;
import br.com.treinamento.cleanarch.configuration.exception.entity.ExceptionEntity;

@ControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ExceptionEntity> notFound(DataBaseException e, HttpServerErrorException request){
        ExceptionEntity err = new ExceptionEntity(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}