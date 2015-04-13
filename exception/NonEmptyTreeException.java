package exception;

@SuppressWarnings("serial")
public class NonEmptyTreeException extends RuntimeException {
	
	public NonEmptyTreeException(){
		super("Tree isn't empty!");
	}
	
	public NonEmptyTreeException(String msg){
		super(msg);
	}
}
