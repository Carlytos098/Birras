package ar.com.birra.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationUnrecoverableException {

    private static final long serialVersionUID = 4223422855712397108L;

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
