package apirestful.iawebbackend.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Excepciones por defecto cuando el estado es Forbidden
     * @param ex
     * @return
     */
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(Exception ex) {

        RestError re = new RestError(HttpStatus.FORBIDDEN.toString(),
                "Authentication failed");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(re);
    }

    /**
     * Excepciones por defecto cuando el estado es Bad Request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<RestError> handleException(MissingRequestHeaderException ex) {
        logger.error("Error due to: " + ex.getMessage());

        RestError errorResponse = new RestError(HttpStatus.BAD_REQUEST.toString(),ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Excepciones por defecto para los códigos de error:
     * HttpRequestMethodNotSupportedException -> 405
     * HttpMediaTypeNotSupportedException     -> 415
     * NoHandlerFoundException                -> 404
     * @param ex
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestError errorResponse = new RestError(Integer.toString(status.value()), ex.getMessage());
        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }
}