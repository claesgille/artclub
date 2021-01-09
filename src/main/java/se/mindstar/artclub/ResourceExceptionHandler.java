package se.mindstar.artclub;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
    public class ErrorMessage {
        @JsonProperty("code")
        private Integer httpStatus;
        @JsonProperty("message")
        private String errorMessage;

        public ErrorMessage(HttpStatus httpStatus, String errorMessage) {
            this.httpStatus = httpStatus.value();
            this.errorMessage = errorMessage;
        }

        public Integer getHttpStatus() {
            return httpStatus;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ErrorMessage> handleException(ResourceException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorMessage(e.getHttpStatus(), e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage()));
    }
}
