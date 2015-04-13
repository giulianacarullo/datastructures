package position;

public class BTNode<E> implements BTPosition<E>{

	//Instance Variables
		private E element;
		private BTPosition<E> left, right, parent;
	
	//Constructor
		/**
		 * Constructs an empty BTNode.
		 */
		public BTNode(){
		}
		
		/**
		 * Constructs a BTNode with the specified values.
		 * @param el the element.
		 * @param parent the parent position.
		 * @param left the left position.
		 * @param right the right position.
		 */
		public BTNode(E el, BTPosition<E> parent,BTPosition<E> left, BTPosition<E> right){
			element = el;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

	//Methods
		/**
		 * Returns the element.
		 * @return the element.
		 */
		public E element() {
			return element;
		}
		
		/**
		 * Returns the left position.
		 * @return the left position.
		 */
		public BTPosition<E> getLeft() {
			return left;
		}

		/**
		 * Returns the parent position.
		 * @return the parent position.
		 */
		public BTPosition<E> getParent() {
			return parent;
		}

		/**
		 * Returns the right position.
		 * @return the right position.
		 */
		public BTPosition<E> getRight() {
			return right;
		}

		/**
		 * Sets the element.
		 * @param el the new element.
		 */
		public void setElement(E el) {
			element = el;
		}

		/**
		 * Sets the left position.
		 * @param pos the new position.
		 */
		public void setLeft(BTPosition<E> pos) {
			left = pos;	
		}
		
		/**
		 * Sets the parent position.
		 * @param pos the new position.
		 */
		public void setParent(BTPosition<E> pos) {
			parent = pos;
		}

		/**
		 * Sets the right position.
		 * @param pos the new position.
		 */
		public void setRight(BTPosition<E> pos) {
			right = pos;		
		}
		
		@Override
		public String toString(){
			if(element == null)
				return null;
			return element.toString();
		}

	
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			BTNode<E> other = (BTNode<E>)otherObject;
			return element.equals(other.element) && left.equals(other.left)
			&& right.equals(other.right) && parent.equals(other.parent);
		}

}
