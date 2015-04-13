package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import nodeList.PositionList;
import position.Position;

/**
 * The ElementIterator class extends Iterator.
 * This class create an iterator for PositionList.  
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class ElementIterator<E> implements Iterator<E> {
	//Instance Variables
		private PositionList<E> list;
		private Position<E> cur;
	
	//Constructor
		
		/**
		 * Constructs an ElementIterator for a given PositionList.
		 * @param l the list of which we want an iterator.
		 */
		
		public ElementIterator(PositionList<E> l){
			list = l;
			cur =(list.isEmpty())?null : list.first();
		}
		
	//Methods
		/**
		 * Tests if this Position has a successor Position. 
		 * @return true if and only if this Position has a 
		 * successor Position; false otherwise.
		 */
		public boolean hasNext() {
			return (cur != null);
		}
		/**
		 * Return the element stored in the current position and 
		 * advance of one position.
		 * @return the element stored in the current position.
		 * @throws NoSuchElementException when a successor for the current
		 * position doesn't exist.
		 */
		public E next() {
			if(cur == null)
				throw new NoSuchElementException();
			E toReturn = cur.element();
			if(cur == list.last())
				cur = null;
			else
				cur = list.next(cur);
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
