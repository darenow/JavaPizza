package eu.turuga.javapizza.controllers;

import eu.turuga.javapizza.dtos.ExceptionDTO;
import eu.turuga.javapizza.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleOrderNotFound(OrderNotFoundException e){
        return new ResponseEntity<>(ExceptionDTO.builder().error(e.getMessage()).build(),HttpStatus.NOT_FOUND);
    }
}
