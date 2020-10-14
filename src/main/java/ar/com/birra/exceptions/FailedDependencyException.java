package ar.com.birra.exceptions;

import org.springframework.http.HttpStatus;

public class FailedDependencyException extends ApplicationUnrecoverableException {
    public FailedDependencyException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FAILED_DEPENDENCY;
    }
}
