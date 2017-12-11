package cd.go.authorization.guest.executor;

public class NoSuchRequestHandler extends RuntimeException {
    public NoSuchRequestHandler(String message) {
        super(message);
    }
}
