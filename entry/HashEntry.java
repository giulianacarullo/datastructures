package entry;


/**
 * The HashEntry class represents a MyEntry with a 
 * location inside. So additional methods for location manage 
 * are provided.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */

public class HashEntry<K,V> extends MyEntry<K, V> {

	//Constructor
		/**
		 * Constructs an HashEntry with a give key and a given value.
		 * @param key the key.
		 * @param value the value.
		 */
		public HashEntry(K key, V value) {
			super(key, value);
		}
	//Methods
		
		/**
		 * Sets the value stored in this LocationAwareEntry with 
		 * another value given in input.
		 * @param value the new value.
		 * @return the old value.
		 */
		public V setValue(V value){
			V oldValue = value;
			this.value = value;
			return oldValue;	
		}
		
		@Override
		public String toString(){
			return "("+getKey()+" - "+getValue()+")";
		}
}
