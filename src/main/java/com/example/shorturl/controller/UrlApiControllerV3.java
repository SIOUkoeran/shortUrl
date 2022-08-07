package com.example.shorturl.controller;


import com.example.shorturl.exception.BadRequestException;
import com.example.shorturl.exception.ErrorCode;
import com.example.shorturl.exception.SendRedirectException;
import com.example.shorturl.form.RequestUrlForm;
import com.example.shorturl.form.ResponseUrlForm;
import com.example.shorturl.idgenerator.IdGeneratorSnowflake;
import com.example.shorturl.resource.UrlResource;
import com.example.shorturl.service.UrlService;
import com.example.shorturl.url.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Slf4j
@RequestMapping("/v3")
public class UrlApiControllerV3 {

    private final UrlService urlService;

    public UrlApiControllerV3(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/")
    public ResponseEntity createShortUrl(@RequestBody @Valid RequestUrlForm requestUrlForm,
                                         BindingResult bindingResult) throws RuntimeException{
        if (bindingResult.hasErrors())
            throw new BadRequestException(ErrorCode.BAD_REQUEST_URL.getErrorMessage());
        Url url = this.urlService.saveShortUrlV3(requestUrlForm.getUrl());
        ResponseUrlForm responseUrlForm = new ResponseUrlForm(url.getShortUrl(), url.getUrl());

        WebMvcLinkBuilder selfLinkBuilder = linkTo(UrlApiControllerV3.class).slash(url.getId());
        URI uri =selfLinkBuilder.toUri();
        UrlResource urlResource =new UrlResource(responseUrlForm);
        urlResource.add(linkTo(UrlApiController.class).slash(url.getShortUrl()).withRel("response-url"));

        return  ResponseEntity.created(uri).body(urlResource);
    }

    @GetMapping("/{shortUrl}")
    public void responseUrlV3(@PathVariable("shortUrl") String url, HttpServletResponse httpResponse){
        Url findUrl = this.urlService.findUrlByShortUrlV3(url);
        try{
            httpResponse.sendRedirect(findUrl.getUrl());
        } catch (IOException e) {
            throw  new SendRedirectException(ErrorCode.SEND_REDIRECT);
        }

    }
}
