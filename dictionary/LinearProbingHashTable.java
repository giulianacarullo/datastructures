package dictionary;

import java.util.Iterator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import entry.Entry;
import entry.HashEntry;
import exception.InvalidEntryException;
import exception.InvalidKeyException;

/**
 * 
 * The LinearProbingHashTable class implements dictionary.
 * A linear probing policy is used.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key type.
 * @param <V> the value type.
 */


public class LinearProbingHashTable<K,V> implements Dictionary<K, V>{

	/*Instance Variables*/
		protected Entry<K,V> AVAILABLE = new HashEntry<K,V>(null, null);
		protected int numEntries = 0; 
		protected int prime, capacity; // prime factor and bucket array's capacity
		protected Entry<K,V>[] bucket;// bucket array
		protected int scale, shift;   // shift e scale factors

	/*Constructors*/
		
		/** 
		 * Constructs a LinearProbingHashTables with a prime factor 109345121, a 
		 * capacity = 16 with and a load factor = 0.5. 
		 */
		public LinearProbingHashTable(){ 
			this(109345121,16); 
			}
		
		/** 
		 * Constructs a LinearProbingHashTable with a prime factor 
		 * 109345121, a given capacity and a load factor = 0.5 
		 * @param cap the initial capacity.
		 */
		public LinearProbingHashTable(int cap) { 
			this(109345121, cap); 
		}
		
		/** 
		 * Constructs a LinearProbingHashTable with a given prime factor
		 * a given capacity and a load factor = 0.5. 
		 * @param p the prime factor.
		 * @param cap the initial capacity. 
		 */
		@SuppressWarnings("unchecked")
		public LinearProbingHashTable(int p, int cap) {
			prime = p;
			capacity = cap;
			bucket = (Entry<K,V>[]) new Entry[capacity]; // safe cast
			java.util.Random rand = new java.util.Random();
			scale = rand.nextInt(prime-1) + 1; 
			shift = rand.nextInt(prime); 
		}
	
	//Methods
		

		/**
		 * Returns the first entry with the given key if exists; false otherwise.
		 * @param key the key to search.
		 * @return first entry with the given key if exists; false otherwise.
		 * @throws InvalidKeyException if the key is not valid for this LinearProbingHashTable.
		 */
		public Entry<K, V> find(K key) throws InvalidKeyException {
			checkKey(key);
			int i = hashValue(key);
			int j = i;
			do {
				Entry<K,V> e = bucket[i];
				if( e ==null)
					break;
				if(key.equals(e.getKey()))
					return e;
				i = (i+1) % capacity;
			}
			while(i!=j);
			return null;
		}
	
		/**
		 * Returns an iterable collection of the entries with a given key. 
		 * If no entry has the specified key an empty collection will be returned.
		 * @param key the key to search.
		 * @return an iterable collection of the entries with a given key.
		 * If no entry has the specified key an empty collection will be returned.
		 * @throws InvalidKeyException if the key is not valid for this LinearProbingHashTable. 
		 */
		public Iterable<Entry<K, V>> findAllTwo(K key) throws InvalidKeyException {
			checkKey(key);
			PositionList<Entry<K, V>> list = new NodePositionList<Entry<K,V>>();
			Iterator<Entry<K,V>> it = entries().iterator();
			while(it.hasNext()){
				Entry<K, V> e = it.next();
				if(e.getKey().equals(key))
					list.addLast(e);
				else if(e == null)
					break;
			}
			return list;
		} 
		
		/**
		 * Returns an iterable collection of the entries with a given key. 
		 * If no entry has the specified key an empty collection will be returned.
		 * @param key the key to search.
		 * @return an iterable collection of the entries with a given key.
		 * If no entry has the specified key an empty collection will be returned.
		 * @throws InvalidKeyException if the key is not valid for this LinearProbingHashTable. 
		 */
		public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
			checkKey(key);
			PositionList<Entry<K, V>> list = new NodePositionList<Entry<K,V>>();
			int i = hashValue(key);
			int j = i;
			do {
				Entry<K,V> e = bucket[i];
				if( e ==null)
					break;
				if(key.equals(e.getKey()))
					list.addLast(e);
				i = (i+1) % capacity;
			} while(i!=j);
			//return null;
			return list;
		}

		/**
		 * Inserts an entry in this LinearProbingHashTable.
		 * @param key the key to insert.
		 * @param value the value relative to the key to insert.
		 * @return the entry inserted in this LinearProbingHashTable.
		 * @throws InvalidKeyException if the key is not valid for this LinearProbingHashTable.
		 */
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			if(capacity <2*size())
				rehash();
			int i = hashValue(key);
			int j = i;
			do {
				Entry<K,V> e = bucket[i];
				if(e == null || e == AVAILABLE){
					bucket[i] = new HashEntry<K, V>(key,value);
					numEntries++;
					break;
				}
				i = (i+1) % capacity;
			} while(i != j);
			return bucket[i];
		}
		
		/**
		 * Auxiliary method - Inserts the entry in this LinearProbingHashTable.
		 * @param e the entry to insert in this LinearProbingHashTable.
		 */
		private void insertEntry(Entry<K, V> e) {
			K key = e.getKey();
			int i = hashValue(key);
			int j = i;
			do {
				Entry<K,V> entry = bucket[i];
				if(entry == null || entry == AVAILABLE){
					bucket[i] =e;
					break;
				}
				i = (i+1) % capacity;		
			} while(i != j);
		}
	
		/**
		 * Removes the specified entry from this LinearProbingHashTable.
		 * Returns the removed entry, null if this LinearProbingHashTable is empty 
		 * or if the entry does not belong to this LinearProbingHashTable.
		 * @param e the entry to remove.
		 * @return the removed entry, null if this LinearProbingHashTable is empty 
		 * or if the entry does not belong to this LinearProbingHashTable.
		 * @throws InvalidEntryException if the specified entry is not
		 * valid for this LinearProbingHashTable.
		 */
	
		public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
			checkEntry(e);
			K key = e.getKey();
			int i = hashValue(key);
			int j = i;
			do {
				Entry<K,V> entry = bucket[i];
				if(entry == null)
					break;
				if(e == entry){
					bucket[i] = AVAILABLE;
					numEntries--;
					return entry;
				}
				i = (i+1) % capacity;
			} while(i != j);
			return null;
		}
		
		/**
		 * Returns an iterable collection view of the keys contained 
		 * in this dictionary.
		 * @return an iterable collection view of the keys contained 
		 * in this dictionary.
		 */
		public Iterable<K> keys() {
			PositionList<K> keys = new NodePositionList<K>();
			for(int i = 0; i<capacity;i++)
				if(bucket[i] != null && bucket[i] != AVAILABLE)
					keys.addLast(bucket[i].getKey());
			return keys;
		}
		
	
		/**
		 * Returns an iterable collection view of the values contained 
		 * in this dictionary.
		 * @return an iterable collection view of the values contained 
		 * in this dictionary.
		 */
		public Iterable<V> values() {
			PositionList<V> values= new NodePositionList<V>();
			for(int i = 0; i<capacity;i++)
				if(bucket[i] != null && bucket[i] != AVAILABLE)
					values.addLast(bucket[i].getValue());
			return values;
		}
	
		/**
		 * Returns an iterable collection view of the entries contained 
		 * in this dictionary.
		 * @return an iterable collection view of the entries contained 
		 * in this dictionary.
		 */
		public Iterable<Entry<K, V>> entries() {
			PositionList<Entry<K, V>> keys = new NodePositionList<Entry<K, V>>();
			for(int i = 0; i<capacity;i++)
				if(bucket[i] != null && bucket[i] != AVAILABLE)
					keys.addLast(bucket[i]);
			return keys;
		}

		
		/**
		 * When the number of entries in the hash table exceeds the product of 
		 * the load factor and the current capacity, the capacity is roughly 
		 * doubled by calling this method. 
		 */
		//GIVE A LOOK TO MAD!
		@SuppressWarnings("unchecked")
		protected void rehash() {
			capacity = 2*capacity;
			Entry<K,V>[] old = bucket;
			bucket = (Entry<K,V>[]) new Entry[capacity]; // new bucket
			java.util.Random rand = new java.util.Random();
			scale = rand.nextInt(prime-1) + 1;     // new hash scaling factor 
			shift = rand.nextInt(prime);      // new hash shifting factor
			for (int i = 0; i < old.length; i++) {
				Entry<K,V> e = old[i];
				if ((e != null) && (e != AVAILABLE)) // valid entry
					insertEntry(e); 
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
		 * Checks if the specified key is valid.
		 * @param key the key to check.
		 * @throws InvalidKeyException if the key is not valid.
		 */
		protected void checkKey(K key) {
			if(key == null)
				throw new InvalidKeyException();
		}
		
		/**
		 * Checks if the specified entry is valid.
		 * @param e the entry to check.
		 * @throws InvalidEntryException if entry is not valid.
		 */
		protected void checkEntry(Entry<K, V> e) {
			if(e == null)
				throw new InvalidEntryException();		
		}
		

		/**
		 * Returns true if this dictionary contains no key-value mappings.
		 * @return true if this dictionary contains no key-value mappings.  
		 */
		public boolean isEmpty() {
			return (numEntries == 0);
		}
		/**
		 * Returns the number of entries in this LinearProbingHashTable.
		 * @return the number of entries in this LinearProbingHashTable.
		 */
		public int size() {
			return numEntries;
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
