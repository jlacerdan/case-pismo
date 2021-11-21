package com.example.demo.advices;

import com.example.demo.exceptions.AccountAlreadyExistsForDocumentException;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.RequestNotValidException;
import com.example.demo.exceptions.OperationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String accountNotFoundHandler(AccountNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AccountAlreadyExistsForDocumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String accountAlreadyExistsForDocumentException(AccountAlreadyExistsForDocumentException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OperationNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String OperationNotFoundException(OperationNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RequestNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String OperationNotFoundException(RequestNotValidException ex) {
        return ex.getMessage();
    }
}
