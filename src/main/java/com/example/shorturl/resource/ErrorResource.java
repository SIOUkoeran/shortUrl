package com.example.shorturl.resource;

import com.example.shorturl.exhandler.ErrorResult;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class ErrorResource extends EntityModel<ErrorResult> {

    public ErrorResource(ErrorResult errorResult, Link...links) {
        super(errorResult, links);
    }
}
