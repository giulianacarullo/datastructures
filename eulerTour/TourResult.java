package eulerTour;

/**
 * Tour result.
 * @author SuperJulietta
 * @version 1.0
 * @param <R> the result type.
 */

public class TourResult<R> {
	//Instance Variables
		private R left;
		private R right;
		private R out;
	
	//Constructor
		/**
		 * Constructs an empty TourResult.
		 */
		public TourResult(){
		}
		
	//Methods
		/**
		 * Returns left operand.
		 * @return the left operand.
		 */
		public R getLeft(){
			return left;
		}
		
		/**
		 * Returns right operand. 
		 * @return the right operand.
		 */
		public R getRight(){
			return right;
		}
		
		/**
		 * Returns the result.
		 * @return the result.
		 */
		public R getOut(){
			return out;
		}
		
		/**
		 * Sets left operand. 
		 * @param left new left value.
		 */
		public void setLeft(R left){
			this.left = left;
		}
		
		/**
		 * Sets right operand. 
		 * @param right new right value.
		 */
		public void setRight(R right){
			this.right = right;
		}
		
		/**
		 * Sets result. 
		 * @param out new result value.
		 */
		public void setOut(R out){
			this.out = out;
		}
}
