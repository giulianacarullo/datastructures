package map;

import nodeList.NodePositionList;
import nodeList.PositionList;
import entry.Entry;
import entry.HashEntry;
import exception.InvalidKeyException;

/**
 * Hash table based implementation of the Map interface. This 
 * implementation provides all of the optional Map operations, 
 * and permits null values but not null keys.
 * This implementation provides constant-time performance for the 
 * basic operations (get and put), assuming the hash function disperses 
 * the elements properly among the buckets. Iteration over collection 
 * views requires time proportional to the "capacity" of the HashTableMap 
 * instance (the number of buckets) plus its size (the number of 
 * key-value mappings). Thus, it's very important not to set the 
 * initial capacity too high if iteration performance is important.
 * When the number of entries in the hash table exceeds the product 
 * of the load factor and the current capacity, the capacity is 
 * roughly doubled by calling the rehash method. 
 * 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key type.
 * @param <V> the value type.
 */
public class HashTableMap<K,V> implements Map<K,V> {
	/*Instance Variables*/
		private Entry<K,V> AVAILABLE = new HashEntry<K,V>(null, null);
		private int numEntries = 0; 
		private int prime, capacity; // prime factor and bucket array's capacity
		private Entry<K,V>[] bucket;// bucket array
		private int scale, shift;   // shift e scale factors

	/*Constructors*/
		
		/** 
		 * Constructs an hash table with a prime factor 109345121, a 
		 * capacity = 16 with and a load factor = 0.5. 
		 * */
		public HashTableMap(){ 
			this(109345121,16); 
		}
		
		/** 
		 * Constructs an hash table with a prime factor 109345121, a 
		 * given capacity and a load factor = 0.5. 
		 * */
		public HashTableMap(int cap) { 
			this(109345121, cap); 
		}
		
		/** 
		 * Constructs an hash table with a given prime factor,a 
		 * given capacity and a load factor = 0.5. 
		 */
		
		@SuppressWarnings("unchecked")
		public HashTableMap(int p, int cap) {
			prime = p;
			capacity = cap;
			bucket = (Entry<K,V>[]) new Entry[capacity]; // safe cast
			java.util.Random rand = new java.util.Random();
			scale = rand.nextInt(prime-1) + 1; 
			shift = rand.nextInt(prime); 
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
			if(isEmpty())
				return null;
			int i = findEntry(key);
			if(i < 0)
				return null;
			return bucket[i].getValue();
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
		 * the map previously associated null with the specified key, if the 
		 * implementation supports null values. 
		 * @throws InvalidKeyException if key is not valid.
		 */
		
		public V put(K key, V value) throws InvalidKeyException {
			int i = findEntry(key);
			if(i >= 0)
				return ((HashEntry<K, V>)bucket[i]).setValue(value);
			if (numEntries >= capacity/2) {
				rehash(); // rehashing(load factor must be <= 0.5)
				i = findEntry(key);
			}
			bucket[-i-1] = new HashEntry<K,V>(key, value);
			numEntries++;
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
			int i = findEntry(key);   // searching key
			if (i < 0) return null;   // nothing to remove
			V toReturn = bucket[i].getValue();
			bucket[i] = AVAILABLE;        // entry became available
			numEntries--;
			return toReturn;
		}

		/**
		 * Returns an iterable collection view of the keys contained 
		 * in this map.
		 * @return an iterable collection view of the keys contained 
		 * in this map.
		 */
		public Iterable<K> keys() {
			PositionList<K> list = new NodePositionList<K>();
			for(int i = 0; i<capacity; i++)
				if(bucket[i] != null && bucket[i] != AVAILABLE)
					list.addLast(bucket[i].getKey());
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
			for(int i = 0; i<capacity; i++)
				if(bucket[i] != null && bucket[i] != AVAILABLE)
					list.addLast(bucket[i].getValue());
			return list;
		}

		/**
		 * Returns an iterable collection view of the entries contained 
		 * in this map.
		 * @return an iterable collection view of the entries contained 
		 * in this map.
		 */
		public Iterable<Entry<K, V>> entries() {
			PositionList<Entry<K, V>> list = new NodePositionList<Entry<K, V>>();
			for(int i = 0; i<capacity; i++)
				if(bucket[i] != null && bucket[i] != AVAILABLE)
					list.addLast(bucket[i]);
			return list;
		}
		
		/**
		 * When the number of entries in the hash table exceeds the product of 
		 * the load factor and the current capacity, the capacity is roughly 
		 * doubled by calling this method. 
		 */
		@SuppressWarnings("unchecked")
		protected void rehash() {
			capacity = 2*capacity;
			Entry<K,V>[] old = bucket;
			bucket = (Entry<K,V>[]) new Entry[capacity]; //new bucket
			java.util.Random rand = new java.util.Random();
			scale = rand.nextInt(prime-1) + 1;     // new hash scaling factor
			shift = rand.nextInt(prime);      // new hash shifting factor
			for (int i = 0; i < old.length; i++) {
				Entry<K,V> e = old[i];
				if ((e != null) && (e != AVAILABLE)) { // valid entry
					int j = - 1 - findEntry(e.getKey());
					bucket[j] = e;
				}
			}
		}

		/**
		 * Returns the hash value(MAD)of a given key. 
		 * @param key the key.
		 * @return the hash value of the key.
		 */
		public int hashValue(K key){
			return (Math.abs(key.hashCode()*scale + shift )%prime) % capacity;
		}	
	
		/**
		 * Returns the index of the entry in the bucket array with a given 
		 * key. If the key exists the value returned will be >= 0. Less than 0,
		 * otherwise.  
		 * 
		 * @param key the key to search.
		 * @return the index of the entry(more than 0 if it exists. less than 0
		 * otherwise).
		 */
		protected int findEntry(K key){
			int avail = -1;
			checkKey(key);
			int i = hashValue(key);
			int j = i;
			do {
				Entry<K,V> entry = bucket[i];
				if(entry == null){
					if(avail <0)
						avail = i; //key is not in the table
					break;
				}
				if(entry.getKey()!=null)
					if(entry.getKey().equals(key))
						return i;
				if(entry == AVAILABLE)
					if(avail<0)
						avail = i; //available entry
				
				i = (i+1) % capacity;
			} while(i != j);
			return -(avail+1); //first empty or available entry
		}

		/**
		 * Returns true if this map contains no key-value mappings.
		 * @return true if this map contains no key-value mappings.  
		 */
		public boolean isEmpty() {
			return (numEntries == 0);
		}
		
		/**
		 * Returns the number of key-value mappings in this map.
		 * @return the number of key-value mappings in this map.
		 */
		public int size() {
			return numEntries;
		}
		
		/**
		 * Checks if a key is valid for this map.
		 * @param key the key to check.
		 */
		protected void checkKey(K key) { //private?
			if(key == null)
				throw new InvalidKeyException();
		}
		@Override
		public String toString(){
			String s;
			s ="[";
			int j=0;
			if (size() > 0){
				for(int i=0;i<bucket.length;i++)
					if(bucket[i] != null  && bucket[i] != AVAILABLE){
						s+= bucket[i];
						j = i;
						break;
					}
			}	
			int i;
			if (size() > 1)
				for (i =j+1; i< bucket.length; i++)
					if(bucket[i] != null && bucket[i] != AVAILABLE)
						s += " - " + bucket[i];
			return s+"]";
		}
}
