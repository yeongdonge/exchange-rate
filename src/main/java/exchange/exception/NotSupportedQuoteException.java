package exchange.exception;

public class NotSupportedQuoteException extends RuntimeException {
    public NotSupportedQuoteException(String message) {
        super(message);
    }
}
