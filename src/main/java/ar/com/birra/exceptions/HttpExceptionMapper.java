package ar.com.birra.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletionException;

@RestControllerAdvice
public class HttpExceptionMapper {
    private static final Logger LOG = LoggerFactory.getLogger(HttpExceptionMapper.class);

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<ApplicationError> handleException(final ApplicationException exception, final HttpServletRequest request) {
        if (exception.getStatus().is5xxServerError()) {
            LOG.error(exception.getMessage(), exception);
        } else {
            //LOG.warn(exception.getMessage(), exception);
        }
        return ResponseEntity.status(exception.getStatus()).body(new ApplicationError(exception));
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    protected ResponseEntity<ApplicationError> handleMissingParameterException(final MissingServletRequestParameterException exception,
                                                                               final HttpServletRequest request) {
        return handleException(new BadRequestException(exception.getMessage(), exception), request);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    protected ResponseEntity<ApplicationError> handleNotReadableException(final HttpMessageNotReadableException exception, final HttpServletRequest request) {
        return handleException(new BadRequestException("The body message is malformed or it has invalid values.", exception), request);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApplicationError> handleNotValidException(final MethodArgumentNotValidException exception, final HttpServletRequest request) {
        final String errorMsg = exception.getBindingResult().getFieldErrors().stream() //
                .findFirst() //
                .map(f -> f.getField() + " " + f.getDefaultMessage()) //
                .orElse("The body message is malformed.");
        return handleException(new BadRequestException(errorMsg, exception), request);
    }

    @ExceptionHandler(value = {CompletionException.class})
    protected ResponseEntity<ApplicationError> handleCompletionException(final CompletionException exception, final HttpServletRequest request) {
        if (exception.getCause() instanceof ApplicationException) {
            return handleException((ApplicationException) exception.getCause(), request);
        }
        return handleUnknownException(exception, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ApplicationError> handleUnknownException(final Exception exception, final HttpServletRequest request) {
        return handleException(new UnexpectedException("Unexpected Exception: " + exception.getMessage(), exception), request);
    }

    public static class ApplicationError {
        private final String message;
        private final String error;
        private final Integer status;

        public ApplicationError(final ApplicationException exception) {
            this.message = exception.getMessage();
            this.error = exception.getStatus().getReasonPhrase();
            this.status = exception.getStatus().value();
        }

        public String getMessage() {
            return this.message;
        }

        public String getError() {
            return this.error;
        }

        public Integer getStatus() {
            return this.status;
        }
    }
}
