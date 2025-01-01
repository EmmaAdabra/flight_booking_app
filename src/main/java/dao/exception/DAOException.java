package dao.exception;

public class DAOException extends Exception{
    public DAOException() {
        super("SQL Execution Error Occurred");
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
