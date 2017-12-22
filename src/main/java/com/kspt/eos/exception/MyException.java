package com.kspt.eos.exception;

import com.kspt.eos.logic.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MyException extends RuntimeException {

    public MyException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String name) {
            super("could not find object with name: " + name);
        }
    }

    public MyException(int errorCode) {
        super(ErrorCodes.getError(errorCode));
    }
}
