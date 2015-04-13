package eulerTour;



/** Class for a variable of an arithmetic expression. */

public class ExpressionVariable extends ExpressionTerm{
	//Instance Variables
	protected Integer var;
	
	//Constructor
	public ExpressionVariable(Integer var){
		this.var = var;
	}
	
	//Methods
	public void setVariable(Integer var){
		this.var = var;
	}
	
	public Integer getVariable(){
		return var;
	}
	public String toString(){
		return var.toString();
	}
}
