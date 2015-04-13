package utilities;

/**
 * The Node class it's a node that can be used for linked-list.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class Node<E> {
	//Instance Variables
		private E element;
		private Node<E> next;
	
	//Constructors
		/**
		 * Constructs an empty node.
		 */
		public Node(){
			element = null;
			next = null;
		}
		
		/**
		 * Constructs a node containing a given element. 
		 * @param element the element to save in this Node.
		 */
		public Node(E element){
			this.element = element;
			this.next = null;	
		}
		
		/**
		 * Constructs a node containing a given element and whit a given successor.
		 * @param element the element to save in this Node.
		 * @param next the successor for this Node.
		 */
		public Node(E element, Node<E> next){
			this.element = element;
			this.next = next;
		}
	
		/**
		 * Returns the element stored in this Node.
		 * @return the element stored in this Node.
		 */
		public E getElement(){
			return element;
		}
	
		/**
		 * Returns the successor's position of this Node.
		 * @return the successor's position of this Node.
		 */
		public Node<E> getNext(){
			return next;
		}
	
		/**
		 * Sets the element stored in this Node.
		 * @param element the new value for the 
		 * element stored in this Node.
		 */
		public void setElement(E element){
			this.element = element;
		}
	
		/**
		 * Sets the position of the successor of this Node.
		 * @param next the position of the successor.
		 */
		public void setNext(Node <E> next){
			this.next = next;
		}
		@Override
		public String toString(){
			return "["+element  +" - next:"+next +"]";
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			Node<E> other = (Node<E>)otherObject;
			return element.equals(other.element) && next.equals(other.next);
		}

}
