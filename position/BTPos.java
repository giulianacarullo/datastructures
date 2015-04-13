package position;

/**
 * Used in ArrayListCompleteBinaryTree
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 */
public class BTPos<E> implements Position<E>{
	//Instance Variable
		private E el;
		private int index;
	
	//Constructor
		/**
		 * Constructs a BTPos with a given element and a given index.
		 * @param el the element to save in BTPos.
		 * @param index the position's index.
		 */
		public BTPos(E el, int index) {
			this.el = el;
			this.index = index;
		}
		/**
		 * Returns the element.
		 * @return the element. 
		 */
		public E element() {
			return el;
		}
		/**
		 * Returns the index.
		 * @return the index.
		 */
		public int index() { 
			return index; 
		}
	
		/**
		 * Sets the element with a given element.
		 * @param elt the new value for element in BTPos.
		 * @return the element stored in BTPos(before modification).
		 */
		public E setElement(E elt) {
			E temp = el;
			el = elt;
			return temp; 
		}
		@Override
		public String toString(){
			return el.toString();
		}
	
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			BTPos<E> other = (BTPos<E>)otherObject;
			return el.equals(other.el) && index == other.index;
		}

	
}
