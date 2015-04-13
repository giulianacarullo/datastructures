package exception;

@SuppressWarnings("serial")
public class BoundaryViolationException extends RuntimeException {

	public BoundaryViolationException(){
		super();
	}
	
	public BoundaryViolationException(String msg){
		super(msg);
	}

}
