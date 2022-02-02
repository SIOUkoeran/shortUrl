package com.example.shorturl.controller;


import com.example.shorturl.exception.BadRequestException;
import com.example.shorturl.form.RequestUrlForm;
import com.example.shorturl.form.ResponseUrlForm;
import com.example.shorturl.resource.UrlResource;
import com.example.shorturl.service.UrlService;
import com.example.shorturl.url.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UrlApiControllerV2 {

    private final UrlService urlService;

    @PostMapping("/v2")
    public ResponseEntity createEncryptedUrl(@RequestBody  @Valid RequestUrlForm requestUrlForm, BindingResult bindingResult) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        if (bindingResult.hasErrors())
            throw new BadRequestException("잘못된 url 형식");
        Url url = this.urlService.saveShortUrl(requestUrlForm.getUrl());
        ResponseUrlForm responseUrlForm = new ResponseUrlForm(url.getShortUrl(), url.getUrl());

        WebMvcLinkBuilder selfLinkBuilder = linkTo(UrlApiControllerV2.class).slash(url.getId());
        URI uri =selfLinkBuilder.toUri();
        UrlResource urlResource =new UrlResource(responseUrlForm);
        urlResource.add(linkTo(UrlApiController.class).slash(url.getShortUrl()).withRel("response-url"));

        return  ResponseEntity.created(uri).body(urlResource);
    }
}
