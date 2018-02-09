package authwrapper.transport;

public class AuthenticationException extends Exception {
    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
