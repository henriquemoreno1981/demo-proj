package br.com.psmcompany.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MyRestException extends RuntimeException {
    public MyRestException() {
        super();
    }

    public MyRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRestException(String message) {
        super(message);
    }

    public MyRestException(Throwable cause) {
        super(cause);
    }
}
