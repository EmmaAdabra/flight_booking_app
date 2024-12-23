package dao;

public class SQLExecutionException extends Exception{
    public SQLExecutionException() {
        super("SQL Execution Error Occurred");
    }

    public SQLExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
