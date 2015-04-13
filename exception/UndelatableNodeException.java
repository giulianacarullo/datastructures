package exception;

@SuppressWarnings("serial")
public class UndelatableNodeException extends RuntimeException{
	
	public UndelatableNodeException(){
		super("Node is undelatable!");
	}
	
	public UndelatableNodeException(String msg){
		super(msg);
	}
}
