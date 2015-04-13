package indexList;

import exception.IndexOutOfBoundsException;
	
	/**
	 * The IndexList class represents an ordered collection. 
	 * The user of this interface has precise control over 
	 * where in the list each element is inserted. 
	 * The user can access elements by their integer index 
	 * (position in the list), and search for elements in the list. 
	 * Unlike sets, indexLists typically allow duplicate elements. 
	 * The indexList interface provides four methods for positional 
	 * (indexed) access to list elements.  Additional methods like 
	 * size and isEmpty are provided.
	 *  
	 * @author SuperJulietta
	 * @param <E> the element type.
	 *
	 */

public interface IndexList <E> {
	
	E remove (int i) throws IndexOutOfBoundsException;
	E set(int i, E e) throws IndexOutOfBoundsException;
	E get(int i) throws IndexOutOfBoundsException;
	void add(int i, E e);
	boolean isEmpty();
	int size();

}
