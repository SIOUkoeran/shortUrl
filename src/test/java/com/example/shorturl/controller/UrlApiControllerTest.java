package com.example.shorturl.controller;

import com.example.shorturl.RestDocsConfiguration;
import com.example.shorturl.form.RequestUrlForm;
import com.example.shorturl.repository.UrlRepository;
import com.example.shorturl.service.UrlService;
import com.example.shorturl.url.Url;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
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
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
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

    @Autowired
    MockHttpServletResponse response;

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

//    @Test
//    public void responseUrl() throws Exception{
//        String originalUrl = "https://www.naver.com";
//        RequestUrlForm requestUrlForm = new RequestUrlForm();
//        requestUrlForm.setUrl(originalUrl);
//        Url url = this.urlService.saveUrl(requestUrlForm);
//
//        this.mockMvc.perform(get("/bit.ly/" + String.valueOf(url.getUrl()))
//                .content(MediaTypes.HAL_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }


}