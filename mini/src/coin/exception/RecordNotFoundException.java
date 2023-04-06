package coin.exception;

public class RecordNotFoundException extends Exception {
	public RecordNotFoundException() {
		this("This is RecordNotFoundException..");
	}

	public RecordNotFoundException(String msg) {
		super(msg);
	}
}
