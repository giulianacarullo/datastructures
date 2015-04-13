package queue;

import exception.EmptyQueueException;
import utilities.Node;

/**
 * The NodeQueue class implements Queue interface with a linked list. 
 * When a queue is first created, it contains no items. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> The element type.
 * 
 */

public class NodeQueue<E> implements Queue<E>{
	//Instance Variables
		private Node<E>front;
		private Node<E>rear;
		private int size;
	
	//Constructor
		
		/**
		 * Creates an empty Queue.
		 */
		
		public NodeQueue(){
			front = null;
			rear = null;
			size = 0;
		}
		
	//Methods
		/**
		 * Returns the object at the front of this queue removing it from the queue. 
		 * @return  the object at the front of this queue.
		 * @throws EmptyQueueException where queue is empty
		 * 
		 */
		public E dequeue() {
			if(isEmpty())
				throw new EmptyQueueException();
			E tmp = front.getElement();
			front = front.getNext();
			size--;
			if(size()==0)
				rear = null;
			return tmp;
		}
		
		/**
		 * Inserts an item onto the front of this queue.
		 * @param el the item to be inserted onto this queue.
		 * 
		 */
		public void enqueue(E el) {
			Node <E> node = new Node<E>();
			node.setElement(el);
			node.setNext(null);
			if(isEmpty())
				front = node;
			else
				rear.setNext(node);
			rear = node;
			size++;
		}
		
		/**
		 * Returns the object at the front of this queue. 
		 * @return  the object at the front of this queue.
		 * @throws EmptyQueueException where queue is empty
		 * 
		 */
		
		public E front() {
			if(isEmpty())
				throw new EmptyQueueException();
			return front.getElement();
		}
		
		/**
		 * Tests if this queue is empty. 
		 * @return true if and only if queue stack contains no items; false otherwise.
		 */
		
		public boolean isEmpty() {
			return (size == 0);
		}
		
		/**
		 * Returns the number of elements stored in the queue.
		 * @return the number of elements stored in the queue.
		 */
		
		public int size() {
			return size;
		}
		
		@Override
		public String toString() {
			  String s;
			  s = "[";
			  if (size() > 0) s+= front.getElement();
			  if (size() > 1){
				  Node<E> tmp = front.getNext();
				  while(tmp != rear){
					  s += " - " + tmp.getElement();
					  tmp = tmp.getNext();
				  }
				  s += " - " + rear.getElement();
			  }
			  return s+"]";
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			NodeQueue<E> other = (NodeQueue<E>)otherObject;
			if(size() != other.size())
				return false;
			 Node<E> tmp1 = front;
			 Node<E> tmp2= other.front; 
			 while(tmp1 != rear){
				  if(!tmp1.getElement().equals(tmp2.getElement()))
					return false;
				  tmp1 = tmp1.getNext();
				  tmp2 = tmp2.getNext();
			}
			return true;
		}

}
