package map;

import nodeList.NodePositionList;
import nodeList.PositionList;
import entry.Entry;
import entry.MyEntry;
import exception.InvalidKeyException;
import position.*;

/**
 * The ListMap class implements Map interface with a {@link NodePositionList}.
 * (This is a non ordered implementation). 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */
public class ListMap<K,V> implements Map<K,V>{
	//Instance Variables
		protected PositionList<Entry<K,V>> map;
	
	//Constructor
		
		/**
		 * Constructs an empty ListMap.
		 */
		public ListMap(){
			map = new NodePositionList<Entry<K,V>>();
		}
	
	//Methods
		
		/**
		 * Returns the value to which this map maps the specified key. 
		 * Returns null if the map contains no mapping for this key.
		 * A return value of null does not necessarily indicate that the map 
		 * contains no mapping for the key; it's also possible that the map 
		 * explicitly maps the key to null.
		 * @param key - key whose associated value is to be returned. 
		 * @return the value to which this map maps the specified key, or null if the map contains no mapping for this key. 
		 * @throws InvalidKeyException if the key is not valid.
		 */
		
		public V get(K key) throws InvalidKeyException {
			checkKey(key);
			if(!isEmpty())
				for(Position<Entry<K,V>> pos :map.positions()){ 
					Entry<K,V> entry = pos.element();
					if(entry.getKey().equals(key))
						return entry.getValue();
				}
			return null;
		}
		
		/**
		 * Associates the specified value with the specified key in this map. 
		 * If the map previously contained a mapping for this key, the old 
		 * value is replaced by the specified value.
		 * 
		 * @param key - key with which the specified value is to be associated.
		 * @param value - value to be associated with the specified key. 
		 * @return previous value associated with specified key, or null  if 
		 * there was no mapping for key. A null return can also indicate that 
		 * the map previously associated null  with the specified key, if the 
		 * implementation supports null values. 
		 * @throws InvalidKeyException if key is not valid.
		 */
		public V put(K key, V value) throws InvalidKeyException {
			checkKey(key);
			Entry<K,V> newEntry = new MyEntry<K,V>(key, value);
			if(isEmpty())
				map.addFirst(newEntry);
			else{
				for(Position<Entry<K,V>> pos :  map.positions()){
					V oldValue = null;
					if(pos.element().getKey().equals(key)){
						oldValue = pos.element().getValue();
						map.set(pos, newEntry);
						return oldValue;
					}
				}
				map.addLast(newEntry);
			}
			return null;
		}

		/**
		 * Removes the mapping for this key from this map if it is present.
		 * Returns the value to which the map previously associated the key, 
		 * or null if the map contained no mapping for this key. 
		 * (A null return can also indicate that the map previously associated 
		 * null with the specified key if the implementation supports null values.) 
		 * @param key - key whose mapping is to be removed from the map.
		 * @return previous value associated with specified key, or null  if 
		 * there was no mapping for key. 
		 * @throws InvalidKeyException if the key is not valid.
		 */
		public V remove(K key) throws InvalidKeyException {
			checkKey(key);
			for(Position<Entry<K, V>> pos : map.positions()){
				if(pos.element().getKey().equals(key)){
					V value = pos.element().getValue();
					map.remove(pos);
					return value;
				}
			}
			return null;
		}

		/**
		 * Returns an iterable collection view of the keys contained 
		 * in this map.
		 * @return a iterable collection view of the keys contained 
		 * in this map.
		 */
		public Iterable<K> keys() {
			PositionList<K> list = new NodePositionList<K>();
			for(Position<Entry<K, V>> pos: map.positions())
				list.addLast(pos.element().getKey());
			return list;
		}

		/**
		 * Returns an iterable collection view of the values contained 
		 * in this map.
		 * @return an iterable collection view of the values contained 
		 * in this map.
		 */
		public Iterable<V> values() {
			PositionList<V> list = new NodePositionList<V>();
			for(Position<Entry<K, V>> pos: ((NodePositionList<Entry<K, V>>) map).positions()) //Modificato
				list.addLast(pos.element().getValue());
			return list;
		}
		
		/**
		 * Returns an iterable collection view of the entries contained 
		 * in this map.
		 * @return an iterable collection view of the entries contained 
		 * in this map.
		 */
		public Iterable<Entry<K, V>> entries() {
			PositionList<Entry<K,V>> list = new NodePositionList<Entry<K, V>>();
			for(Position<Entry<K, V>> pos: map.positions())
				list.addLast(pos.element());
			return list;
		}
		
		/**
		 * Returns true if this map contains no key-value mappings.
		 * @return true if this map contains no key-value mappings.  
		 */
		public boolean isEmpty() {
			return map.isEmpty();
		}
		
		/**
		 * Returns the number of key-value mappings in this map.
		 * @return the number of key-value mappings in this map.
		 */
		public int size() {
			return map.size();
		}

		
		/**
		 * Checks if a key is valid for this map.
		 * @param key the key to check.
		 */
		private void checkKey(K key) {
			if(key == null)
				throw new InvalidKeyException();	
		}
		
		@Override
		public String toString(){
			return map.toString();
		}
		
}
