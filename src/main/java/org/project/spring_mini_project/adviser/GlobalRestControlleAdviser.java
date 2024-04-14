package org.project.spring_mini_project.adviser;

import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalRestControlleAdviser {

    @ExceptionHandler(NoSuchElementException.class)

    @ResponseStatus(HttpStatus.NOT_FOUND)

    public BaseResponse<?> handleNoSuchElementException(NoSuchElementException ex) {
        return BaseResponse
                .notFound()
                .setMetadata(ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
        );
        return BaseResponse.badRequest().setMetadata(errors);
    }


    //   handle all the exception like postgresqlexception
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handlePSQLException(DataIntegrityViolationException ex) {
        System.out.println("ex = " + ex);
        return BaseResponse
                .badRequest()
                .setMetadata("Email and username must be unique!");
    }
}
