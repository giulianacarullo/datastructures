package position;

/**
 * The ArrayPosition class extends Position. 
 * ArrayPosition stores the element and the relative index.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */


public class ArrayPosition<E> implements Position<E>{
	//Instance Variables
		private int index;
		private E el;
		
	//Constructors
		/**
		 * Constructs an empty ArrayPosition. 
		 */
		public ArrayPosition(){
		}
		
		/**
		 * Constructs an ArrayPosition with a given element and 
		 * a given index.
		 * @param index the index.
		 * @param el the element.
		 */
		public ArrayPosition(int index, E el){
			this.index = index;
			this.el = el;
		}
	//Methods
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
		public int getIndex(){
			return index;
		}
	
		/**
		 * Sets the element.
		 * @param e the new element.
		 * @return the element previously stored in this Position.
		 */
		public E setElement(E e){
			E tmp = el;
			el = e;
			return tmp;
		}
	
		/**
		 * Sets the index.
		 * @param i the new index.
		 * @return the index previously stored in this Position.
		 */
		public int setIndex(int i){
			int tmp = index;
			index = i;
			return tmp;
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
			ArrayPosition<E> other = (ArrayPosition<E>)otherObject;
			return el.equals(other.el) && index == other.index;
		}

}
