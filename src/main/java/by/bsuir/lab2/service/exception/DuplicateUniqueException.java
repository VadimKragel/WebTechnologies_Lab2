package by.bsuir.lab2.service.exception;

public class DuplicateUniqueException extends Exception{
    public DuplicateUniqueException() {
        super();
    }

    public DuplicateUniqueException(String message) {
        super(message);
    }

    public DuplicateUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUniqueException(Throwable cause) {
        super(cause);
    }

    protected DuplicateUniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
