package coin.exception;

public class DuplicateIDException extends Exception {
	public DuplicateIDException() {
		this("This is DuplicateException..");
	}

	public DuplicateIDException(String msg) {
		super(msg);
	}
}
