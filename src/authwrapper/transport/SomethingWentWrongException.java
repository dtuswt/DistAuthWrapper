package authwrapper.transport;

public class SomethingWentWrongException extends Exception {
    public SomethingWentWrongException() {
        super();
    }

    public SomethingWentWrongException(String message) {
        super(message);
    }

    public SomethingWentWrongException(Throwable throwable) {
        super(throwable);
    }

    public SomethingWentWrongException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
