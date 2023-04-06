package coin.exception;

public class AlreadyHasAccountException extends Exception {
	public AlreadyHasAccountException() {
		this("This is AlreadyHasAccountException..");
	}

	public AlreadyHasAccountException(String msg) {
		super(msg);
	}
}
