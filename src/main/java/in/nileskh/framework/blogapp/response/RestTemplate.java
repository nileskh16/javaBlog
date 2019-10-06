package in.nileskh.framework.blogapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestTemplate<T> {

    public ResponseEntity<T> response(T responseBody, HttpStatus httpStatus) {
        return new ResponseEntity<T>(responseBody, httpStatus);
    }
}
