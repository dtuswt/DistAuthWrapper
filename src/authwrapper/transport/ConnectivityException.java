package authwrapper.transport;

public class ConnectivityException extends Exception {
    public ConnectivityException() {
        super();
    }

    public ConnectivityException(String message) {
        super(message);
    }

    public ConnectivityException(Throwable throwable) {
        super(throwable);
    }

    public ConnectivityException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
