package org.zerock.mallapi.common.exception;

public class NotFoundException extends GlobalServerException {
    public NotFoundException(String message) {
        super(message);
    }
}
