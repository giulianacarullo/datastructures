package eulerTour;


/** Class for an operator of an arithmetic expression */

public class ExpressionOperator extends ExpressionTerm {
	  //Instance Variables
	  protected Integer firstOperand, secondOperand;
	  //Constructor
	  public void setOperands(Integer x, Integer y) {
	    firstOperand = x;
	    secondOperand = y;
	  }
	}
