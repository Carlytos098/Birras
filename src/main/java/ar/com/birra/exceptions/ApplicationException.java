package ar.com.birra.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -5235199889213654208L;

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();

}