package eulerTour;

import position.Position;
import binaryTree.BinaryTree;

/**
 * The EulerTour class for a generic tree traversal.
 * Overriding one or more method(s) the user can create a particular 
 * traversal(preorder-inorder-postorder).
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * @param <R> the result type.
 *
 */

public abstract class EulerTour<E,R> {
	//Instance Variables
		protected BinaryTree<E> tree;
	
	//Methods
		public abstract R execute(BinaryTree<E> tree);
	
		protected void init(BinaryTree<E> tree){
			this.tree = tree;
		}
		/**
		 * Template method 
		 * @param v the start position.
		 * @return the visit result.
		 */
		public R eulerTour(Position<E> v){
			TourResult<R> r = new TourResult<R>();
			visitLeft(v,r); //preorder
			if(tree.hasLeft(v))
				r.setLeft(eulerTour(tree.left(v)));
			visitBelow(v,r); //inorder
			if(tree.hasRight(v))
				r.setRight(eulerTour(tree.right(v)));
			visitRight(v,r); //postorder
			return r.getOut();
			
		}
	//Auxiliary methods
		/**
		 * Invoked for left traversals
		 * @param v the start position
		 * @param r the result.
		 */
		private void visitLeft(Position<E> v, TourResult<R> r) {}
		
		/**
		 * Invoked for below traversals
		 * @param v the start position
		 * @param r the result.
		 */
		private void visitBelow(Position<E> v, TourResult<R> r) {}
		
		/**
		 * Invoked for right traversals
		 * @param v the start position
		 * @param r the result.
		 */
		private void visitRight(Position<E> v, TourResult<R> r) {}
}
