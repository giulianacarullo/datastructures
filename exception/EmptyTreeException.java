package exception;

@SuppressWarnings("serial")
public class EmptyTreeException extends RuntimeException {

	public EmptyTreeException(){
		super("EmptyTreeException");
	}
	
	public EmptyTreeException(String msg){
		super(msg);
	}
}
