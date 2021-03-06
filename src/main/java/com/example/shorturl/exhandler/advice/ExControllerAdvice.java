package com.example.shorturl.exhandler.advice;

import com.example.shorturl.resource.ErrorResource;
import com.example.shorturl.resource.UrlResource;
import com.example.shorturl.exception.BadRequestException;
import com.example.shorturl.exception.UrlException;
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
        ErrorResult errorResult = new ErrorResult("URL_NOT_FOUND", "URL을 찾지 못했습니다.");

        ErrorResource errorResource = new ErrorResource(errorResult);
        errorResource.add(linkTo(ExControllerAdvice.class).slash("bit.ly").withRel("request-url"));

        return new ResponseEntity(errorResource, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequestException(BadRequestException e){
        log.error("[exceptionHandler]  ex = {}", e);
        ErrorResult errorResult = new ErrorResult("BAD_URL", "URL형식을 다시 확인해주세요");

        ErrorResource errorResource = new ErrorResource(errorResult);
        errorResource.add(linkTo(ExControllerAdvice.class).slash("bit.ly").withRel("request-url"));

        return new ResponseEntity(errorResource, HttpStatus.BAD_REQUEST);
    }

}
