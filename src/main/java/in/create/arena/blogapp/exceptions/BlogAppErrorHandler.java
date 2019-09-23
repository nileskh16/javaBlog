package in.create.arena.blogapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BlogAppErrorHandler {

    @ExceptionHandler(BlogException.class)
    public ResponseEntity<ErrorResponse> handleBlogException(BlogException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
