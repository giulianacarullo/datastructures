package priorityQueue;

import entry.Entry;
import exception.EmptyPriorityQueueException;
import exception.InvalidKeyException;

/**
 * The PriorityQueue interface includes all "standard methods" used
 * in a PriorityQueue ADT. Additional methods like isEmpty() and
 * size() are provided.
 * 
 * @author SuperJulietta
 * @param <K> the key.
 * @param <V> the value.
 */
public interface PriorityQueue<K, V> {
		Entry<K,V> min() throws EmptyPriorityQueueException;
		Entry<K, V> insert(K key, V value) throws InvalidKeyException;
		Entry<K, V> removeMin() throws EmptyPriorityQueueException;
		boolean isEmpty();
		int size();
}
