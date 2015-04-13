package deque;


import exception.EmptyDequeException;
import utilities.DLNode;


/**
 * The NodeDeque class implements deque interface with a double-linked list. 
 * When a deque is first created, it contains no items. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class NodeDeque<E> implements Deque<E>{
	//Instance Variables
		private DLNode<E> header;
		private DLNode<E> trailer;
		private int  size;
	
	//Constructor
		/**
		 * Creates an empty deque.
		 */
		
		public NodeDeque(){
			header = new DLNode<E>();
			trailer = new DLNode<E>();
			header.setNext(trailer);
			trailer.setPrev(header);
			size = 0;
		}
	//Methods
		
		/**
		 * Inserts the specified element at the front of this deque
		 * @param el the item to be inserted at the front of this deque.
		 */
		
		public void addFirst(E el) {
			DLNode<E> tmp = new DLNode<E>(el, header, header.getNext());
			header.getNext().setPrev(tmp);
			header.setNext(tmp);
			size++;
		}
		
		/**
		 * Inserts the specified element at the end of this deque
		 * @param el the item to be inserted at the end of this deque.
		 */
		
		public void addLast(E el) {
			DLNode<E> tmp = new DLNode<E>(el, trailer.getPrev(), trailer);
			trailer.getPrev().setNext(tmp);
			trailer.setPrev(tmp);
			size++;	
		}
		/**
		 * Retrieves, but does not remove, the first element of this deque.
		 * @return the head of this deque.
		 * @throws EmptyDequeException where deque is empty.
		 */
		public E getFirst() {
			if(isEmpty())
				throw new EmptyDequeException();
			return header.getNext().getElement();
		}
		
		/**
		 * Retrieves, but does not remove, the last element of this deque.
		 * @return the tail of this deque.
		 * @throws EmptyDequeException where deque is empty
		 */
		
		public E getLast(){
			if(isEmpty())
				throw new EmptyDequeException();
			return trailer.getPrev().getElement();
		}
		
		/**
		 * Tests if this deque is empty. 
		 * @return true if and only if this deque contains no items; false otherwise.
		 */
		
		public boolean isEmpty() {
			if(header.getNext() == trailer)
				return true;
			return false;
		}
		
		/**
		 * Retrieves and removes the first element of this deque.
		 * @return the head of this deque.
		 * @throws EmptyDequeException where deque is empty.
		 */
		
		public E removeFirst() {
			if(isEmpty())
				throw new EmptyDequeException();
			DLNode<E> tmp = header.getNext();
			header.setNext(tmp.getNext());
			tmp.getNext().setPrev(header);
			size--;
			return tmp.getElement();
		}
		
		/**
		 * Retrieves and removes the last element of this deque.
		 * @return the tail of this deque.
		 * @throws EmptyDequeException where deque is empty.
		 */
		public E removeLast() {
			if(isEmpty())
				throw new EmptyDequeException();
			DLNode<E> tmp = trailer.getPrev();
			trailer.setPrev(tmp.getPrev());
			tmp.getPrev().setNext(trailer);
			size--;
			return tmp.getElement();
		}
		
		/**
		 * Returns the number of elements stored in the deque.
		 * @return the number of elements stored in the deque.
		 */
		public int size() {
			return size;
		}
		
		public String toString() {
			  String s;
			  s = "[";
			  if (size() > 0) s+= header.getNext().getElement();
			  if (size() > 1){
				  DLNode<E> tmp = (header.getNext()).getNext();
				  while(tmp != trailer){
					  s += " - " + tmp.getElement();
					  tmp = tmp.getNext();
				  }
			  }
			  return s+"]";
		}


}
