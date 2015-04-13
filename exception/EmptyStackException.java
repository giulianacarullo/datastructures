package exception;

@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException{
	
	public EmptyStackException(){
		super("Stack is empty!");
	}
	
	public EmptyStackException(String s){
		super(s);
	}

}
