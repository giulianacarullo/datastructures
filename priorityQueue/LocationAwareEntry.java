package priorityQueue;

import entry.Entry;
import entry.MyEntry;
import position.Position;


/**
 * The LocationAwareEntry class represents a MyEntry with a 
 * location inside. So additional methods for location manage 
 * are provided.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */

class LocationAwareEntry<K,V> extends MyEntry<K,V> {
	
	//Instance Variables
		protected Position <Entry<K,V>> loc;
		
	//Constructors
		
		/**
		 * Constructs a LocationAwareEntry with a given key and a 
		 * given value.
		 * @param k the key.
		 * @param v the value.
		 */
		public LocationAwareEntry(K k, V v) {
			super(k, v);
		}
		/**
		 * Constructs a LocationAwareEntry with a given key, value
		 * and position.
		 * @param k the key.
		 * @param v the value.
		 * @param pos the Position in the data structure.
		 */
		public LocationAwareEntry(K k, V v, Position<Entry<K, V>> pos) {
			super(k, v);
			loc = pos;
		}
	//Methods
		
		/**
		 * Returns the location stored in this LocationAwareEntry.
		 * @return the location stored in this LocationAwareEntry.
		 */
		protected Position < Entry<K,V>> location() {
	    return loc;
	  }
		
		/**
		 * Sets the location stored in this LocationAwareEntry with 
		 * another position given in input.
		 * @param pos the new location.
		 * @return the old position.
		 */
		protected Position < Entry<K,V> >setLocation(Position< Entry<K,V>> pos) {
			Position<Entry<K,V>> oldLoc = pos;
			loc = pos;
		return oldLoc;

	  }
	  
		/**
		 * Sets the key stored in this LocationAwareEntry with 
		 * another key given in input.
		 * @param k the new key.
		 * @return the old key.
		 */
		protected K setKey(K k) {
			K oldKey = key;
			key = k;
			return oldKey;
		}
		
		/**
		 * Sets the value stored in this LocationAwareEntry with 
		 * another value given in input.
		 * @param v the new value.
		 * @return the old value.
		 */
		protected V setValue(V v) {
			V oldValue = value;
			value = v;
			return oldValue; 
		}
		
		@Override
		public String toString(){
			return super.toString();
		}

}
