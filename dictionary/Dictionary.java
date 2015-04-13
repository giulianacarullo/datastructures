package dictionary;

import entry.Entry;
import exception.InvalidEntryException;
import exception.InvalidKeyException;


/**
 * Like map, Dictionary stores key-value pairs(entries).
 * Unlike Map, Dictionary can contain duplicate keys; each key can map
 * to more than one value.
 * Two types of Dictionary can be distinguished: ordered and unordered
 * dictionary. 
 * 
 * @author SuperJulietta
 * @param <K> the key type.
 * @param <V> the value type.
 */
public interface Dictionary<K,V> {
	int size();
	boolean isEmpty();
	Entry<K,V> find(K key) throws InvalidKeyException;
	Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException;
	Entry<K,V> insert(K key, V value) throws InvalidKeyException;
	Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException;
	Iterable<Entry<K,V>> entries();

}
