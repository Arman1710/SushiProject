package kz.sushi.exception;

public class WrongInputDataException extends Exception {
    public WrongInputDataException(String message) {
        super(message);
    }

    public WrongInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongInputDataException(Throwable cause) {
        super(cause);
    }

    public WrongInputDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WrongInputDataException() {
    }
}
