/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class SkincareExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String CONSTRAINT_MESSAGE = "Something went wrong. "
            + "Please ensure the input is valid and try again.";
    private static final String NULL_MESSAGE = "Looks like that doesn't exist.";
    private static final String DATA_ACCESS_MESSAGE = "Cannot find requested object.";
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(SQLIntegrityConstraintViolationException ex, WebRequest request){
        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<Error> handleNullException(NullPointerException ex, WebRequest request){
        Error err = new Error();
        err.setMessage(NULL_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<Error> handleDataAccessException(DataAccessException ex, WebRequest request){
        Error err = new Error();
        err.setMessage(DATA_ACCESS_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
