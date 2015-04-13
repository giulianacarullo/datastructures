package queue;

import exception.EmptyQueueException;


/**
 * The Queue class represents a FIFO (first-in-first-out) set of objects. 
 * The usual enqueue and dequeue operations are provided, as well as a method to peek 
 * at the front item on the queue, a method to test for whether the queue is empty
 * and a method that returns the queue's size.
 * 
 * @author SuperJulietta
 * @param <E> the element type.
 * 
 */

public interface Queue <E>{
	void enqueue(E el);
	E dequeue() throws EmptyQueueException;
	E front() throws EmptyQueueException;
	boolean isEmpty();
	int size();
}
