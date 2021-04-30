package logic;

public class UnknownCommandException extends IllegalArgumentException {
    public UnknownCommandException(String msg) {
        super(msg);
    }
}
