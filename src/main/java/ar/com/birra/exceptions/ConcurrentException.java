package ar.com.birra.exceptions;

import org.springframework.http.HttpStatus;

public class ConcurrentException extends ApplicationRecoverableException {

    private static final long serialVersionUID = -7333309815240653844L;

    public ConcurrentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConcurrentException(final String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
