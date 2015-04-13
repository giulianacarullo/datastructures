package stack;

import exception.EmptyStackException;

/**
 * The ArrayStack class implements Stack interface with an array. 
 * When a stack is first created, it contains no items. 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class ArrayStack<E> implements Stack<E>{

	//Instance Variables
		private E[] stack;
		private int top;
		public static final int CAPACITY = 2;

	
	//Constructors
		/**
		 * Creates an empty Stack.
		 */
		public ArrayStack(){
			this(CAPACITY);
		}
		
		/**
		 * Creates an empty Stack that can store n elements.
		 * @param n the size of the stack
		 */
		@SuppressWarnings("unchecked")
		public ArrayStack(int n){
			stack = (E[]) new Object[n];
			top = -1;
		}
	
	//Methods
		
		/**
		 * Tests if this stack is empty. 
		 * @return true if and only if this stack contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return (top<0);
		}
		
		/**
		 * Returns the object at the top of this stack removing it from the stack. 
		 * @return  the object at the top of this stack.S
		 * @throws EmptyStackException where stack is empty
		 * 
		 */
		public E pop() {
			if(isEmpty())
				throw new EmptyStackException();
			E tmp = stack[top];
			stack[top] = null;
			top--;
			return tmp;
		}
		
		/**
		 * Pushes an item onto the top of this stack. When the stack become full
		 * this stack is changed with another stack(with double-size)containing 
		 * all old elements and the newest one.
		 * @param el the item to be pushed onto this stack.
		 * 
		 */
		
		@SuppressWarnings("unchecked")
		public void push(E el) {
			if(stack.length == size()){		
				E[] tmp = (E[]) new Object[2*stack.length];
				int i = 0;
				for(i = 0; i<=top; i++)
					tmp[i] = stack[i];
				stack = tmp;
			}
			top++;
			stack[top] = el;
		}
		
		/**
		 * Returns the number of elements stored in the stack.
		 * @return the number of elements stored in the stack.
		 */
		
		public int size() {
			return top+1;
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
			return stack[top];
		}
		
		@Override
		public String toString() {
			  String s;
			  s = "[";
			  if (size() > 0) s+= stack[0];
			  if (size() > 1)
			   for (int i=1; i<= size()-1; i++)
			    s += " - " + stack[i];
			  return s+"]";
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			ArrayStack<E> other = (ArrayStack<E>)otherObject;
			if(size() != other.size())
				return false;
			for(int i = 0; i<size(); i++)
				if(!stack[i].equals(other.stack[i]))
					return false;
			return true;
		}


}
