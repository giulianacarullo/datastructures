package position;

/**
 * The DNode class implements Position interface. 
 * Additional setter and getter methods are provided.
 * This class can be used in doubly linked-list.
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class DNode<E> implements Position<E> {
	//Instance Variables
		private E el;
		private DNode<E> prev, next; //References to the nodes before and after
	
	
	//Constructors
		/**
		 * Constructs an empty DNode. 
		 */
		public DNode(){
		}
		
		/**
		 * Constructs a DNode with given parameters.
		 * @param el element stored in the Position.
		 * @param prev reference to the node before.
		 * @param next reference to the node after. 
		 */
		
		public DNode(E el, DNode<E> prev, DNode <E> next){
			this.prev = prev;
			this.next = next;
			this.el = el;
		}
	//Methods
		
		/**
		 * Returns the element stored in the Position.
		 * @return  the element stored in the Position.
		 * @throws InvalidPositionException when the position is not valid.
		 */
		
		public E element() {
			if(prev == null || next == null) //because we use header and trailer
				throw new InvalidPositionException("Position is not in a list");
			return el;
		}
		/**
		 * Returns the reference to the previous position.
		 * @return the reference to the previous position.
		 *
		 */
		public DNode<E> getPrev(){
			return prev;
		}
		
		/**
		 * Returns the reference to the next position.
		 * @return the reference to the next position.
		 *
		 */
		public DNode<E> getNext(){
			return next;
		}
		
		/**
		 * Sets the reference to the next position.
		 * @param next the reference to the next position to set.
		 */
		public void setNext(DNode<E> next){
			this.next = next;
		}
		
		/**
		 * Sets the reference to the previous position.
		 * @param prev the reference to the previous position to set.
		 */
		public void setPrev(DNode<E> prev){
			this.prev = prev;
		}
		/**
		 * Sets the element stored in the Position with a new element(el)
		 * given in input.
		 * @param el the new element.
		 */
		public void setElement(E el){
			this.el = el;
		}
		
		@Override
		public String toString(){
			return el.toString();
		}

	
		/**
		 * Returns true if the element stored in the given DNode is
		 * equals to the element of this DNode.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			DNode<E> other = (DNode<E>)otherObject;
			return el.equals(other.el);
		}

}
