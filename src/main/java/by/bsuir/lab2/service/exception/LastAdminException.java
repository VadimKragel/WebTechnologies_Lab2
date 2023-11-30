package by.bsuir.lab2.service.exception;

public class LastAdminException extends Exception{
    public LastAdminException() {
        super();
    }

    public LastAdminException(String message) {
        super(message);
    }

    public LastAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public LastAdminException(Throwable cause) {
        super(cause);
    }

    protected LastAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
