package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import utilities.Node;

/**
 * The LinkedIterator class extends Iterator.  
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class LinkedIterator<E> implements Iterator<E>{

	//Instance Variables
		private Node <E> head;
		private int size;
	
	//Constructor
		/**
		 * Constructs a LinkedIterator for a given collection.
		 */
		@SuppressWarnings("unchecked")
		public LinkedIterator(E[] el){
			head = null;
			size = el.length;
			for(int i = size-1; i>=0; i--){
				Node <E> tmp = new Node(el[i], head);
				head = tmp;
			}
		
		}
	
	//Methods
		
		/**
		 * Tests if this Position has a successor Position. 
		 * @return true if and only if this Position has a 
		 * successor Position; false otherwise.
		 */
		public boolean hasNext() {
			return (size > 0);
		}
		
		/**
		 * Return the element stored in the current position and 
		 * advance of one position.
		 * @return the element stored in the current position.
		 * @throws NoSuchElementException when a successor for the current
		 * position doesn't exist.
		 */
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E toReturn = head.getElement();
			head = head.getNext();
			size--;
			return toReturn;
		}
		
		/**
		 * Remove method not supported.
		 * @throws UnsupportedOperationException when invoked.
		 */
		public void remove() {	
			throw new UnsupportedOperationException("remove");
		}

}
