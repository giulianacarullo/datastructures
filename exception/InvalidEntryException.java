package exception;

@SuppressWarnings("serial")
public class InvalidEntryException extends RuntimeException {
	
	public InvalidEntryException(){
		super("Entry is not valid!");
	}
	
	public InvalidEntryException(String msg){
		super(msg);
	}
}
