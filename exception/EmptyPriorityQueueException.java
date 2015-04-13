package exception;

@SuppressWarnings("serial")
public class EmptyPriorityQueueException extends RuntimeException {
	
	public EmptyPriorityQueueException() {
		super("Priority Queue is empty!");
	}
	public EmptyPriorityQueueException(String s){
			super(s);
	}
}
