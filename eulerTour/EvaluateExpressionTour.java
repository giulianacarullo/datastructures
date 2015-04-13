package eulerTour;

import position.Position;
import binaryTree.BinaryTree;

/** Compute the value of an arithmetic expression tree. */
public class EvaluateExpressionTour extends EulerTour<ExpressionTerm, Integer> {
	
  public Integer execute(BinaryTree<ExpressionTerm> T) {
    init(T); // calls method of superclass
    return eulerTour(tree.root());  // returns the value of the expression
  }
  
  protected void visitRight(Position<ExpressionTerm> v, TourResult<Integer> r) {
    ExpressionTerm term = v.element();
    if(tree.isInternal(v)) {
      ExpressionOperator op = (ExpressionOperator) term;
      op.setOperands(r.getLeft(), r.getRight());
    }
    r.setOut(term.getValue());
  }
}
