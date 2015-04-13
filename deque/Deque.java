package deque;

import exception.EmptyDequeException;

/**
 * The Deque class represents a linear collection that supports element 
 * insertion and removal at both ends. 
 * Methods are provided to insert, remove, and examine the elements at both ends.
 * Additional methods like size and isEmpty are provided. 
 * 
 * @author SuperJulietta
 * @param <E> the element type.
 * 
 */

public interface Deque <E> {

	void addFirst(E el);
	void addLast(E el);
	E getFirst() throws EmptyDequeException;
	E getLast() throws EmptyDequeException;
	E removeFirst() throws EmptyDequeException;
	E removeLast() throws EmptyDequeException;
	int size();
	boolean isEmpty();
	
}
