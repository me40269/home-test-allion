package com.allion.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GenericResponse {
    private HttpStatus httpStatusCode;
    private String errorMsg;
    private String successMsg;
    private String id;
    private Class exception;
    private Object response;

    public GenericResponse() {

    }

    public GenericResponse(HttpStatus httpStatusCode, String errorMsg, Class exception) {
        this.httpStatusCode = httpStatusCode;
        this.errorMsg = errorMsg;
        this.exception = exception;
    }



}
