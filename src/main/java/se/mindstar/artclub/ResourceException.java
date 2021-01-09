package se.mindstar.artclub;

import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {
    private HttpStatus httpStatus;
    public ResourceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ResourceException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
