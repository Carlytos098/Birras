package ar.com.birra.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationUnrecoverableException {

    private static final long serialVersionUID = 4093255964696650178L;

    public UnauthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(final String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}
