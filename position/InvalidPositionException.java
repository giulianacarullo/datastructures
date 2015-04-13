package position;

@SuppressWarnings("serial")
public class InvalidPositionException extends RuntimeException{
	
	public InvalidPositionException(){
		super("Invalid position!");
	}
	public InvalidPositionException(String s){
		super(s);
	}

}
