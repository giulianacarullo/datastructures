package utilities;

/**
 * The DLNode class it's a node that can be used for doubly linked-list.
 *
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class DLNode <E>{
	//Instance variables
		private E el;
		private DLNode <E> prev;
		private DLNode <E> next;
	
	//Constructors
		/**
		 * Constructs an empty Node.
		 */
		public DLNode(){
	
		}
		
		/**
		 * Constructs a Node with a given element, successor and predecessor.
		 * @param el the element stored in this Node.
		 * @param prev the predecessor of this Node.
		 * @param next the successor of this Node.
		 */
		public DLNode(E el, DLNode<E> prev, DLNode<E> next){
			this.el = el;
			this.prev = prev;
			this.next = next;
		}
	
	//Methods
		/**
		 * Returns the element stored in this DLNode.
		 * @return the element stored in this DLNode.
		 */
		public E getElement(){
			return el;
		}
	
		/**
		 * Returns the predecessor's position of this DLNode.
		 * @return the predecessor's position of this DLNode.
		 */
		public DLNode<E> getPrev(){
			return prev;
		}
	
		/**
		 * Returns the successor's position of this DLNode.
		 * @return the successor's position of this DLNode.
		 */
		public DLNode<E> getNext(){
			return next;
		}
		
		/**
		 * Sets the element stored in this DLNode.
		 * @param el the new value for the 
		 * element stored in this DLNode.
		 */
		public void setElement(E el){
			this.el = el;
		}
	
		/**
		 * Sets the position of the predecessor of this DLNode.
		 * @param prev the position of the predecessor.
		 */
		public void setPrev(DLNode<E> prev){
			this.prev = prev;
		}
		
		/**
		 * Sets the position of the successor of this DLNode.
		 * @param next the position of the successor.
		 */
		public void setNext(DLNode<E> next){
			this.next = next;
		}
		
		@Override
		public String toString(){
			return "["+el  +" - previous: "+prev+ " next:"+next +"]";
		}

	
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			DLNode<E> other = (DLNode<E>)otherObject;
			return el.equals(other.el) && prev.equals(other.prev)
			&& next.equals(other.next);
		}
}
