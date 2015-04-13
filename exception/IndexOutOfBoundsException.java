package exception;

@SuppressWarnings("serial")
public class IndexOutOfBoundsException  extends RuntimeException{
	
	public IndexOutOfBoundsException(){
		super("Out of bounds!");
	}
	
	public IndexOutOfBoundsException(String s){
		super(s);
	}
}
