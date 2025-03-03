package com.company.topnews.exceptionHandler;


import com.company.topnews.util.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e) {
        ApiResponse<?> response = new ApiResponse<>(400,e.getMessage(),null);
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<String> errors = new LinkedList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException e) {
        ApiResponse<?> response = new ApiResponse<>(400,e.getMessage(),null);
        e.printStackTrace();
        return ResponseEntity.badRequest().body(response);
    }

}
