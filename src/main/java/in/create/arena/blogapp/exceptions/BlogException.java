package in.create.arena.blogapp.exceptions;

import lombok.Getter;

@Getter
public class BlogException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    public BlogException() {
        super();
    }

    public BlogException(String message) {
        super(message);
        this.message = message;
    }
}
