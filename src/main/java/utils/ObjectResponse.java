package utils;

public class ObjectResponse<T> extends Response {
    private T objectData;

    public ObjectResponse(boolean status, String message, T objectData) {
        super(status, message);
        this.objectData = objectData;
    }

    public ObjectResponse() {
    }

    public void createResponse(ObjectResponse response, boolean status, String msg, T obj){
        response.setStatus(status);
        response.setMessage(msg);
        response.setObjectData(obj);
    }

    public T getObjectData(){
        return objectData;
    }

    public void setObjectData(T data){
        this.objectData = data;
    }
}
