package com.jaba.vgl.exceptions;

import com.jaba.vgl.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    private final static Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

        //Returned to the client
        ApiException payload = new ApiException(
                e.getMessage(),
                e.getCause(),
                HttpStatus.BAD_REQUEST,
                DateUtil.toISO8601(LocalDateTime.now())
        );

        logger.error("Error thrown with status code: " + payload.getHttpStatusCode());

        return new ResponseEntity<>(
                payload,
                payload.getHttpStatus()
        );
    }

    @ExceptionHandler(value = {
            GameNotFoundException.class,
            GameDetailsNotFoundException.class,
            ReviewNotFoundException.class,
    })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException e) {

        //Returned to the client
        ApiException payload = new ApiException(
                e.getMessage(),
                e.getCause(),
                HttpStatus.NOT_FOUND,
                DateUtil.toISO8601(LocalDateTime.now())
        );

        logger.error("Error thrown with status code: " + payload.getHttpStatusCode());

        return new ResponseEntity<>(
                payload,
                payload.getHttpStatus()
        );
    }
}
