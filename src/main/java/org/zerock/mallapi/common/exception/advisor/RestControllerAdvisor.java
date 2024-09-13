package org.zerock.mallapi.common.exception.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.zerock.mallapi.common.exception.NotFoundException;
import org.zerock.mallapi.presentation.ApiResponse;

@Slf4j(topic = "Rest Controller Exception")
@RestControllerAdvice(basePackages = "org.zerock.mallapi.presentation.api")
public class RestControllerAdvisor extends ResponseEntityExceptionHandler {

    private final static String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";
    private final static String BAD_REQUEST_MESSAGE = "Bad request";
    private final static String BAD_CREDENTIALS_MESSAGE = "Bad credentials";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NotFoundException e) {
        return createResponseEntity(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    private <T> ResponseEntity<T> createResponseEntity(Exception e, HttpStatusCode statusCode, String message) {
        @SuppressWarnings("unchecked")
        T response = (T) ApiResponse.error(message);
        return ResponseEntity.status(statusCode).body(response);
    }
}
