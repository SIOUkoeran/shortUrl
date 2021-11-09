package com.example.shorturl.form;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RequestUrlForm implements Serializable{

    @NotNull(message = "Url을 입력해주세요")
    @URL
    private String url;

    public RequestUrlForm(){
    }

}
