package dictionary;

import nodeList.NodePositionList;
import nodeList.PositionList;
import entry.Entry;
import exception.InvalidEntryException;
import exception.InvalidKeyException;

/**
 * 
 * The ChainingHashTable class implements dictionary with an 
 * array of {@link LogFile}. A chaining policy is used.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key type.
 * @param <V> the value type.
 */


public class ChainingHashTable<K,V> implements Dictionary<K, V> {
	/*Instance Variables*/
		private int numElem = 0;
		private int capacity;
		private LogFile<K,V>[] bucket;
		private long scale, shift;

	//Constructors
		
		/** 
		 * Constructs a ChainingHashTables with a prime factor 109345121 
		 * and a capacity = 16. 
		 */
		public ChainingHashTable(){
			this(16);
		}
		
	
		/** 
		 * Constructs a ChainingHashTable with a prime factor 
		 * 109345121 and a given capacity.
		 * @param cap the initial capacity. 
		 */
		@SuppressWarnings("unchecked")
		public ChainingHashTable(int cap){
			capacity = cap;
			bucket = (LogFile<K,V>[])new LogFile[capacity];
			for(int i = 0; i<capacity; i++)
				bucket[i] = new LogFile<K,V>();
			java.util.Random rand = new java.util.Random();
			scale = rand.nextInt(capacity-1)+1; //+1 so scale != 0 anywhere
			shift = rand.nextInt(capacity);
		}
		
	//Methods

		/**
		 * Returns the first entry with the given key if exists; false otherwise.
		 * @param key the key to search.
		 * @return first entry with the given key if exists; false otherwise.
		 * @throws InvalidKeyException if the key is not valid for this ChainingHashTable.
		 */
		public Entry<K, V> find(K key) throws InvalidKeyException {
			checkKey(key);
			int index = hashValue(key);
			return bucket[index].find(key);
		}

		/**
		 * Returns an iterable collection of the entries with a given key. 
		 * If no entry has the specified key an empty collection will be returned.
		 * @param key the key to search.
		 * @return an iterable collection of the entries with a given key.
		 * If no entry has the specified key an empty collection will be returned.
		 * @throws InvalidKeyException if the key is not valid for this ChainingHashTable. 
		 */
		public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
			checkKey(key);
			int index = hashValue(key);
			return bucket[index].findAll(key);
		}

		/**
		 * Inserts an entry in this ChainingHashTable.
		 * @param key the key to insert.
		 * @param value the value relative to the key to insert.
		 * @return the entry inserted in this ChainigHashTable.
		 * @throws InvalidKeyException if the key is not valid for this ChainingHashTable.
		 */
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			int index = hashValue(key);
			numElem++;
			return bucket[index].insert(key, value);		
		}


		/**
		 * Removes the specified entry from this ChainingHashTable.
		 * Returns the removed entry, null if this ChainingHashTable is empty 
		 * or if the entry does not belong to this ChainingHashTable.
		 * @param e the entry to remove.
		 * @return the removed entry, null if this ChainingHashTable is empty 
		 * or if the entry does not belong to this ChainingHashTable.
		 * @throws InvalidEntryException if the specified entry is not
		 * valid for this ChainingHashTable.
		 */
		public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
			int index = hashValue(e.getKey());
			Entry<K,V> entry = bucket[index].remove(e);
			numElem--;
			return entry;
		}

	
		/**
		 * Returns an iterable collection view of the keys contained 
		 * in this dictionary.
		 * @return an iterable collection view of the keys contained 
		 * in this dictionary.
		 */
		public Iterable<K> keys() {
			PositionList<K> list = new NodePositionList<K>();
			for(int i = 0; i<capacity; i++) {
				PositionList<Entry<K, V>> entries = (PositionList<Entry<K, V>>) bucket[i].entries();
				list.addLast(entries.first().element().getKey());
				for(int j = 0; j<entries.size(); j++)
					list.addLast(entries.next(entries.first()).element().getKey());
			}
			return list;
		}
	

		/**
		 * Returns an iterable collection view of the values contained 
		 * in this dictionary.
		 * @return an iterable collection view of the values contained 
		 * in this dictionary.
		 */
		public Iterable<V> values() {
			PositionList<V> list = new NodePositionList<V>();
			for(int i = 0; i<capacity; i++) {
				PositionList<Entry<K, V>> entries = (PositionList<Entry<K, V>>) bucket[i].entries();
				list.addLast(entries.first().element().getValue());
				for(int j = 0; j<entries.size(); j++)
					list.addLast(entries.next(entries.first()).element().getValue());
			}
			return list;
		}

		/**
		 * Returns an iterable collection view of the entries contained 
		 * in this dictionary.
		 * @return an iterable collection view of the entries contained 
		 * in this dictionary.
		 */
		public Iterable<Entry<K, V>> entries() {
			PositionList<Entry<K, V>> list = new NodePositionList<Entry<K, V>>();
			for(int i = 0; i<capacity; i++) {
				try{
					PositionList<Entry<K, V>> entries = (PositionList<Entry<K, V>>) bucket[i].entries();
					list.addLast(entries.first().element());
					for(int j = 0; j<entries.size(); j++)
						list.addLast(entries.next(entries.first()).element());
				}
				catch(Exception e){}
				
			}
			return list;
		}
	
		/**
		 * Returns the hash value(MAD)of a given key. 
		 * @param key the key.
		 * @return the hash value of the key.
		 */
		public int hashValue(K key){
			return (int)Math.abs(key.hashCode()*scale + shift) % capacity;
		}


		/**
		 * Returns true if this dictionary contains no key-value mappings.
		 * @return true if this dictionary contains no key-value mappings.  
		 */
		public boolean isEmpty() {
			return (numElem == 0);
		}
		
		/**
		 * Returns the number of entries in this ChainingHashTable.
		 * @return the number of entries in this ChainingHashTable.
		 */
		public int size() {
			return numElem;
		}
		

		/**
		 * Checks if the specified key is valid.
		 * @param key the key to check.
		 * @throws InvalidKeyException if the key is not valid.
		 */
		private void checkKey(K key) {
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
					if(bucket[i] != null && !bucket[i].isEmpty()){
						s+= bucket[i];
						j = i;
						break;
					}
			}	
			int i;
			if (size() > 1)
				for (i =j+1; i< bucket.length; i++)
					if(bucket[i] != null && !bucket[i].isEmpty())
						s += " - " + bucket[i];
			return s+"]";
		}

}
