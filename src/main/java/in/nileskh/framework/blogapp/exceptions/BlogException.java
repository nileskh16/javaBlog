package in.nileskh.framework.blogapp.exceptions;

public class BlogException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public BlogException(String message) {
		super(message);
		this.message = message;
	}
	
	public BlogException() {
		super();
	}
	
	public String getMessage() {
		return message;
	}
}
