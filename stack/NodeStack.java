package stack;

import exception.EmptyStackException;
import utilities.Node;
/**
 * The NodeStack class implements Stack interface with a linked list. 
 * When a stack is first created, it contains no items. 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class NodeStack<E> implements Stack<E>{
	
	//Instance Variables
		private Node<E> top;
		private int size;
	
	//Constructor
		/**
		 * Creates an empty Stack.
		 */
		public NodeStack(){
			top = null;
			size = 0;
		}
	//Methods
		/**
		 * Tests if this stack is empty. 
		 * @return true if and only if this stack contains no items; false otherwise.
		 */
		
		public boolean isEmpty() {
			return (size == 0);
		}

		/**
		 * Returns the object at the top of this stack removing it from the stack. 
		 * @return  the object at the top of this stack.S
		 * @throws EmptyStackException where stack is empty
		 * 
		 */
		
		public E pop()  {
			if(isEmpty())
				throw new EmptyStackException();
			E tmp = top.getElement();
			top = top.getNext();
			size--;
			return tmp;
		}

		/**
		 * Pushes an item onto the top of this stack.
		 * @param el the item to be pushed onto this stack.
		 * 
		 */
		
		@SuppressWarnings("unchecked")
		public void push(E el) {
			Node <E> node = new Node(el, top);
			top = node;
			size++;
		}

		/**
		 * Returns the number of elements stored in the stack.
		 * @return the number of elements stored in the stack.
		 */
		
		public int size() {
			return size;
		}
		
		/**
		 * Returns the object at the top of this stack without removing it from the stack. 
		 * @return the object at the top of this stack.
		 * @throws EmptyStackException where stack is empty
		 * 
		 */
		
		public E top(){
			if(isEmpty())
				throw new EmptyStackException();
			return top.getElement();
		}
}
