package com.jaba.vgl.exceptions;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;


public @Data class ApiException {
    @JsonProperty("message")
    private final String message;

    @JsonProperty("throwable")
    private final Throwable throwable;

    @JsonProperty("http_status")
    private final HttpStatus httpStatus;

    @JsonProperty("http_status_code")
    private final int httpStatusCode;

    @JsonProperty("date")
    private final Date date;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, Date date) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatus.value();
        this.date = date;
    }
}

