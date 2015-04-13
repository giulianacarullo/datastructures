package stack;

import exception.EmptyStackException;


/**
 * The Stack class represents a LIFO (last-in-first-out) stack of objects. 
 * The usual push and pop operations are provided, as well as a method to peek 
 * at the top item on the stack, a method to test for whether the stack is empty
 * and a method that returns the stack's size.
 * 
 * @author SuperJulietta
 * @param <E> the element type.
 * 
 */

public interface Stack <E> {
	void push(E el);
	E pop() throws EmptyStackException;
	E top() throws EmptyStackException;
	boolean isEmpty();
	int size();
}
