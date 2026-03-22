package exception;

// custom exception — will throw this when amount is 0 or negative
// or if category is blank etc.
public class InvalidTransactionException extends Exception {

    public InvalidTransactionException(String msg) {
        super(msg);
    }
}