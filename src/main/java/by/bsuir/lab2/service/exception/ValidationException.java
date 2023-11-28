package by.bsuir.lab2.service.exception;

public class ValidationException extends Exception {
    public ValidationException() {
        super();
    }

    public ValidationException(String message, Exception e) {
        super(message, e);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Exception e) {
        super(e);
    }
}

