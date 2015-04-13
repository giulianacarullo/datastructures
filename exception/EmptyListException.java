package exception;

@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {
	
	public EmptyListException(){
		super("List is empty!");
	}
	public EmptyListException(String msg){
		super(msg);
	}

}
