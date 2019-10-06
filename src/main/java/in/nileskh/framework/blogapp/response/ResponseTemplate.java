package in.nileskh.framework.blogapp.response;

public class ResponseTemplate {
    private String message;
    private Integer statusCode;
    private Object data;

    public ResponseTemplate(String message, Integer statusCode, Object data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public ResponseTemplate() {

    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
