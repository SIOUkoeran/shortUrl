package com.example.shorturl.controller;

import com.example.shorturl.RestDocsConfiguration;
import com.example.shorturl.form.RequestUrlForm;
import com.example.shorturl.repository.UrlRepository;
import com.example.shorturl.service.UrlService;
import com.example.shorturl.url.Url;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
class UrlApiControllerTest {

    @Autowired
    UrlRepository repository;

    @Autowired
    UrlService urlService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        this.repository.deleteAll();
    }

    @Test
    public void createShortUrl() throws Exception{
        RequestUrlForm requestUrlForm = new RequestUrlForm();
        requestUrlForm.setUrl("https://www.naver.com");

        mockMvc.perform(post("/bit.ly/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(requestUrlForm)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("url").exists())
                .andExpect(jsonPath("shortUrl").exists())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andDo(document("create-url",
                        links(
                                linkWithRel("self").description("self link"),
                                linkWithRel("response-url").description("redirect original url")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("url").description("url to change")
                        ),
                        responseFields(
                                fieldWithPath("url").description("url to change"),
                                fieldWithPath("shortUrl").description("shortened url"),
                                fieldWithPath("_links.self.href").description("link to self"),
                                fieldWithPath("_links.response-url.href").description("response-link")
                        )
                ));
    }

    @Test
    public void responseUrl() throws Exception{

        this.mockMvc.perform(get("/bit.ly/notFound"))
                .andDo(print())
                .andExpect(jsonPath("code").exists())
                .andExpect(jsonPath("message").exists())
                .andExpect(status().isNotFound())
                .andDo(document("error-notFound",
                        links(
                                linkWithRel("request-url").description("request-url")
                        ),
                        responseFields(
                                fieldWithPath("code").description("error code"),
                                fieldWithPath("message").description("error message"),
                                fieldWithPath("_links.request-url.href").description("return request url")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ))
                );
    }
    @Test
    public void InvalidUrl() throws Exception{

        RequestUrlForm requestUrlForm = new RequestUrlForm();
        requestUrlForm.setUrl("www.naver.com");

        mockMvc.perform(post("/bit.ly")
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(requestUrlForm)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").exists())
                .andExpect(jsonPath("message").exists())
                .andDo(document("error-invalidError",
                        links(
                                linkWithRel("request-url").description("request-url")
                        ),
                        responseFields(
                                fieldWithPath("code").description("error code"),
                                fieldWithPath("message").description("error message"),
                                fieldWithPath("_links.request-url.href").description("return request-url")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ))
                );
    }

    @Test
    @DisplayName("엄호화 적용 url 저장")
    public void SaveUrl() throws Exception{
        RequestUrlForm requestUrlForm = new RequestUrlForm();
        requestUrlForm.setUrl("https://www.naver.com");

        mockMvc.perform(post("/")
                .accept(MediaTypes.HAL_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestUrlForm)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("shortUrl").exists())
                .andExpect(jsonPath("url").exists())
                .andDo(document("create-encrypted-url",
                        links(
                                linkWithRel("self").description("self link"),
                                linkWithRel("response-url").description("redirect-url")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("contentHeader")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("contentHeader")
                        ),
                        requestFields(
                                fieldWithPath("url").description("request Original Url")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("shortUrl").description("savedUrl"),
                                fieldWithPath("url").description("original Url")
                        ))
                )
        ;
    }

    @Test
    @DisplayName("Url 리다렉션")
    void redirectUrl() throws Exception {
        RequestUrlForm requestUrlForm = new RequestUrlForm();
        requestUrlForm.setUrl("https://www.naver.com");
        Url url = this.urlService.saveShortUrl(requestUrlForm.getUrl());
        this.mockMvc.perform(get("/{shortUrl}", url.getShortUrl())
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());

    }

}