package exception;

@SuppressWarnings("serial")
public class InvalidKeyException extends RuntimeException {
	
	public InvalidKeyException() {
		super("Invalid Key!");
	}
	
	public InvalidKeyException(String s){
		super(s);
	}
}
