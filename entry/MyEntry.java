package entry;

/**
 * The MyEntry class implements Entry interface.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */
public class MyEntry<K, V> implements Entry<K, V> {
	//Instance Variables
		protected K key;
		protected V value;
	
	//Constructor
		
		/**
		 * Constructs an entry with a given key and a given value.
		 * @param key the key.
		 * @param value the value.
		 */
		public MyEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	/**
	 * Returns the key stored in this MyEntry.
	 * @return the key stored in this MyEntry.
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * Returns the value stored in this MyEntry.
	 * @return the value stored in this MyEntry. 
	 */
	public V getValue() {
		return value;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object anObject){
		if(anObject == null) 
			return false;
		if(getClass() != anObject.getClass()) return false;
		HashEntry<K,V> entry = (HashEntry<K,V>) anObject;
		return( key == entry.key && value == entry.value);
	}
	
	
	@Override
	public String toString(){
		return "("+key+" - "+value+")";
	}
}
