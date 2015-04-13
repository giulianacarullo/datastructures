package map;

import entry.Entry;
import exception.InvalidKeyException;

/**
 * An object that maps keys to values. A map cannot contain duplicate keys; 
 * each key can map to at most one value.
 * The Map interface provides three collection views, 
 * which allow a map's contents to be viewed as a set of keys, 
 * collection of values, or set of key-value mappings.  
 * Typically methods put, get and remove are provided, together 
 * additional methods like isEmpty and size. 
 * 
 * @author SuperJulietta
 * @param <K> the key.
 * @param <V> the value.
 */

public interface Map<K,V> {
	int size();
	boolean isEmpty();
	V put(K key, V value) throws InvalidKeyException;
	V get(K key) throws InvalidKeyException;
	V remove(K key) throws InvalidKeyException;
	Iterable<K> keys();
	Iterable<V> values();
	Iterable <Entry<K,V>>entries();


}
