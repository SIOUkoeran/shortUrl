package com.example.shorturl.resource;

import com.example.shorturl.controller.UrlApiController;

import com.example.shorturl.form.ResponseUrlForm;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class UrlResource extends EntityModel<ResponseUrlForm> {

    public UrlResource(ResponseUrlForm url, Link...links){
        super(url, links);
        add(linkTo(UrlApiController.class).withSelfRel());
    }

}
