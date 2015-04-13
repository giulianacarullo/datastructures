package dictionary;

import entry.Entry;
import entry.MyEntry;
import position.Position;

public class BSTEntry<K,V> extends MyEntry<K, V>{
	//Instance variables
		protected Position<Entry<K,V>> pos;
	
	//Constructors
		/**
		 * Constructs a BSTEntry with a given key and a given value.
		 */
		public BSTEntry(K key, V value) {
			super(key, value);
		}
		
	    /**
	     * Constructs a BSTEntry with a given key, a given value and
	     * the entry's Position.
	     * @param k the key.
	     * @param v the value.
	     * @param p the entry's Position. 
	     */
		public BSTEntry(K k, V v, Position<Entry<K,V>> p) {
			super(k, v);
			pos = p; 
		}
		
	//Method
		/**
		 * Returns the Position.
		 * @return the Position. 
		 */
		public Position<Entry<K,V>> position() { 
			return pos; 
		}
		


}
