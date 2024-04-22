package utils.Exceptions;

public class CurrencyException extends RuntimeException{
    public CurrencyException() {
    }

    public CurrencyException(String message) {
        super(message);
    }

    public CurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyException(Throwable cause) {
        super(cause);
    }
}
