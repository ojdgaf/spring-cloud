package com.ojdgaf.cloud.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not delete the entity")
public class NotDeletedException extends RuntimeException {

    public NotDeletedException() {
        super();
    }

    public NotDeletedException(String message) {
        super(message);
    }

    public NotDeletedException(Throwable cause) {
        super(cause);
    }
}
