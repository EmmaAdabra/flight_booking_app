package utils;

public class Response {
    private boolean status;
    private String message;

    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response() {
    }

    public void createResponse(Response response, boolean status, String msg){
        response.setStatus(status);
        response.setMessage(msg);
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
