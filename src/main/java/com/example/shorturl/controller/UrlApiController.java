package com.example.shorturl.controller;

import com.example.shorturl.resource.UrlResource;

import com.example.shorturl.exception.BadRequestException;
import com.example.shorturl.exception.UrlException;
import com.example.shorturl.url.Url;
import com.example.shorturl.service.UrlService;
import com.example.shorturl.form.RequestUrlForm;
import com.example.shorturl.form.ResponseUrlForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.hateoas.MediaTypes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
@Slf4j
public class UrlApiController {

    private final UrlService urlService;

    @Autowired
    public UrlApiController(UrlService urlService) {

        this.urlService = urlService;
    }

    @PostMapping("/")
    public ResponseEntity requestUrl(@RequestBody @Valid RequestUrlForm form, BindingResult bindingResult) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        Url url = urlService.saveShortUrl(form.getUrl());
        ResponseUrlForm responseUrlForm = new ResponseUrlForm(url.getShortUrl(), url.getUrl());

        WebMvcLinkBuilder selfLinkBuilder = linkTo(UrlApiController.class).slash(url.getId());
        URI uri =selfLinkBuilder.toUri();
        UrlResource urlResource =new UrlResource(responseUrlForm);
        urlResource.add(linkTo(UrlApiController.class).slash(url.getShortUrl()).withRel("response-url"));

        return  ResponseEntity.created(uri).body(urlResource);
    }



    @GetMapping("/{shortUrl}")
    public void responseUrl(@PathVariable(name = "shortUrl") String url, HttpServletResponse response) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        String findUrl = urlService.findUrl2(url);
        try {
            response.sendRedirect(findUrl);
        } catch (IOException e) {
            throw new BadRequestException();
        }
    }

}
