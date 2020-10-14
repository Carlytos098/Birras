package ar.com.birra.exceptions;

public abstract class ApplicationRecoverableException extends ApplicationException {

    private static final long serialVersionUID = -3129835596735397109L;

    public ApplicationRecoverableException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationRecoverableException(final String message) {
        super(message);
    }

}
