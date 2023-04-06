package coin.exception;

public class NotEfficientException extends Exception {
	public NotEfficientException() {
		this("This is AlreadyHasAccountException..");
	}

	public NotEfficientException(String msg) {
		super(msg);
	}
}
