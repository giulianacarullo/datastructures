package exception;

@SuppressWarnings("serial")
public class EmptySequenceException extends RuntimeException {
	
	public EmptySequenceException(){
		super("Sequence is empty!");
	}
	
	public EmptySequenceException(String msg){
		super(msg);
	}
}
