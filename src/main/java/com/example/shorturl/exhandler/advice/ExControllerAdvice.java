package com.example.shorturl.exhandler.advice;

import com.example.shorturl.exception.*;
import com.example.shorturl.resource.ErrorResource;
import com.example.shorturl.resource.UrlResource;
import com.example.shorturl.exhandler.ErrorResult;
import com.example.shorturl.form.ResponseUrlForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestControllerAdvice
@Slf4j
public class ExControllerAdvice {


    @ExceptionHandler(UrlException.class)
    public ResponseEntity urlException(UrlException e){
        log.error("[exceptionHandler]  ex = {}", e);
        ErrorResult errorResult = new ErrorResult(ErrorCode.URL_NOT_FOUND.getErrorCode(), ErrorCode.URL_NOT_FOUND.getErrorMessage());

        ErrorResource errorResource = new ErrorResource(errorResult);
        errorResource.add(linkTo(ExControllerAdvice.class).slash("bit.ly").withRel("request-url"));

        return new ResponseEntity(errorResource, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequestException(BadRequestException e){
        log.error("[exceptionHandler]  ex = {}", e);
        ErrorResult errorResult = new ErrorResult(ErrorCode.BAD_REQUEST_URL.getErrorCode(), ErrorCode.BAD_REQUEST_URL.getErrorMessage());

        ErrorResource errorResource = new ErrorResource(errorResult);
        errorResource.add(linkTo(ExControllerAdvice.class).slash("bit.ly").withRel("request-url"));

        return new ResponseEntity(errorResource, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalTickStateException.class)
    public ResponseEntity tickStateException(IllegalTickStateException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult(ErrorCode.IllegalTickState.getErrorCode(),
                ErrorCode.IllegalTickState.getErrorMessage());
        ErrorResource errorResource = new ErrorResource(errorResult);
        errorResource.add(linkTo(ExControllerAdvice.class).slash("/v3").withRel("request-url"));
        return new ResponseEntity(errorResource, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalTimeStampStateException.class)
    public ResponseEntity timestampException(IllegalTimeStampStateException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult(ErrorCode.IllegalTimestampState.getErrorCode(),
                ErrorCode.IllegalTimestampState.getErrorMessage());
        ErrorResource errorResource = new ErrorResource(errorResult);
        errorResource.add(linkTo(ExControllerAdvice.class).slash("/v3").withRel("request-url"));
        return new ResponseEntity(errorResource, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity urlNotFoundException(UrlNotFoundException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult(ErrorCode.URL_NOT_FOUND.getErrorCode(),
                ErrorCode.URL_NOT_FOUND.getErrorMessage());
        ErrorResource errorResource = new ErrorResource(errorResult);
        errorResource.add(linkTo(ExControllerAdvice.class).slash("/v3").withRel("request-create-short url"));
        return new ResponseEntity(errorResource, HttpStatus.BAD_REQUEST);
    }
}
