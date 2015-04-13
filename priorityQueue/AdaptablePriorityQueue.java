package priorityQueue;

import entry.Entry;
import exception.InvalidKeyException;

/**
 * The AdaptablePriorityQueue interface includes all "standard methods" used
 * in a PriorityQueue ADT. Additional methods like isEmpty() and
 * size() are provided.
 * 
 * @author SuperJulietta
 * @param <K> the key.
 * @param <V> the value.
 */
public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K,V> {
	Entry<K,V> remove(Entry<K,V> entry);
	K replaceKey(Entry<K,V> entry, K key) throws InvalidKeyException;
	V replaceValue(Entry<K,V> entry, V value);
}
