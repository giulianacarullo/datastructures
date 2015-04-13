package map;

@SuppressWarnings("serial")
public class InvalidKeyException extends RuntimeException {
	public InvalidKeyException(){
		super("Invalid key!");
	}
}
