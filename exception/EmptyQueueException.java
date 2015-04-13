package exception;

@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException{
	
	public EmptyQueueException(){
		super("Queue is empty!");
	}
	public EmptyQueueException(String s){
		super(s);
	}

}
