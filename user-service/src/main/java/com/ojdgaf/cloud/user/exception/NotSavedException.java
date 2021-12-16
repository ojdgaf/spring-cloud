package com.ojdgaf.cloud.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not save the entity")
public class NotSavedException extends RuntimeException {

    public NotSavedException() {
        super();
    }

    public NotSavedException(String message) {
        super(message);
    }

    public NotSavedException(Throwable cause) {
        super(cause);
    }
}
