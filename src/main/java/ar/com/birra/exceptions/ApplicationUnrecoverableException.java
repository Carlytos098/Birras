package ar.com.birra.exceptions;

public abstract class ApplicationUnrecoverableException extends ApplicationException {

    private static final long serialVersionUID = 4996681347055582508L;

    public ApplicationUnrecoverableException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationUnrecoverableException(final String message) {
        super(message);
    }

}
