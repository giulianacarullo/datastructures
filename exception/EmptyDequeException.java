package exception;

@SuppressWarnings("serial")
public class EmptyDequeException extends RuntimeException{
	
	public EmptyDequeException(){
		super("Deque is empty!");
	}
	public EmptyDequeException(String s){
		super(s);
	}

}
