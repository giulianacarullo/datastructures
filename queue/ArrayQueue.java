package queue;

import exception.EmptyQueueException;

/**
 * The ArrayQueue class implements Queue interface with an array. 
 * When a queue is first created, it contains no items. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class ArrayQueue<E> implements Queue<E>{

	//Instance Variables
		private E[] queue;
		private int front;
		private int rear;
		private int size;
		public static final int CAPACITY = 2;
		
	//Constructors
		/**
		 * Creates an empty Queue.
		 */
		public ArrayQueue(){
			this(CAPACITY);
		}
		
		/**
		 * Creates an empty Queue that can store n elements.
		 * @param n the size of the stack
		 */
		
		@SuppressWarnings("unchecked")
		public ArrayQueue(int n){
			queue = (E[]) new Object[n];
			front = 0;
			rear = 0;
			size = n;
		}
		
		/**
		 * Returns the object at the front of this queue removing it from the queue. 
		 * @return  the object at the front of this queue.
		 * @throws EmptyQueueException where queue is empty
		 * 
		 */
		
		public E dequeue() {
			if(isEmpty())
				throw new EmptyQueueException();
			E tmp = queue[front];
			queue[front] = null;
			front = (front+1) % size;
			return tmp;
		}
		
		/**
		 * Inserts an item onto the front of this queue. When the queue become full
		 * this queue is changed with another queue(with double-size)containing 
		 * all old elements and the newest one.
		 * @param el the item to be inserted onto this queue.
		 * 
		 */
		@SuppressWarnings("unchecked")
		public void enqueue(E el) {
			if(size() == size-1){
				E[] tmp = (E[]) new Object[2*size];
				int i = 0;
				int f = front;
				while(f != rear){
					tmp[i] = queue[f];
					i++; 
					f = (f+1)%size;
				}
				front = 0;
				size *= 2;
				queue=tmp;
			}
			queue[rear] = el;
			rear = (rear+1) % size;
			
		}
		
		/**
		 * Returns the object at the front of this queue. 
		 * @return  the object at the front of this queue.
		 * @throws EmptyQueueException where queue is empty
		 * 
		 */
		
		public E front(){
			if(isEmpty())
				throw new EmptyQueueException();
			return queue[front];
		}
		
		/**
		 * Tests if this queue is empty. 
		 * @return true if and only if queue stack contains no items; false otherwise.
		 */
		
		public boolean isEmpty() {
			return (front == rear);
		}
		
		/**
		 * Returns the number of elements stored in the queue.
		 * @return the number of elements stored in the queue.
		 */
		public int size() {
			return (size - front + rear)%size;
		}
		
		@Override
		public String toString() {
			  String s;
			  s = "[";
			  int index = front;
			  if (size() > 0) s+= queue[index];
			  if (size() > 1)
			   for (int i=1; i<= size()-1; i++){
				  index = (index+1)%size;
				   s += " - " + queue[index];
			   }
			  return s+"]";
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			ArrayQueue<E> other = (ArrayQueue<E>)otherObject;
			if(size() != other.size())
				return false;
			for(int i = 0; i<size(); i++)
				if(!queue[i].equals(other.queue[i]))
					return false;
			return true;
		}

}
