package dictionary;

import java.util.Iterator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import entry.Entry;
import entry.MyEntry;
import exception.InvalidEntryException;
import exception.InvalidKeyException;
import position.Position;


/**
 * This class implements Dictionary interface with a {@link PositionList}
 * of entries.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key type.
 * @param <V> the key value.
 */

public class LogFile<K,V> implements Dictionary<K,V> {
	//Instance Variables
		private PositionList<Entry<K, V>> logFile;
		int numEl = 0;
	
	//Constructor
		/**
		 * Constructs an empty LogFile.
		 */
		public LogFile(){
			logFile = new NodePositionList<Entry<K,V>>();
		}
		
	//Methods

		/**
		 * Returns the first entry with the given key if exists; false otherwise.
		 * @param key the key to search.
		 * @return first entry with the given key if exists; false otherwise.
		 * @throws InvalidKeyException if the key is not valid for this LogFile.
		 */
		public Entry<K, V> find(K key) throws InvalidKeyException {
			checkKey(key);
			Iterator<Entry<K, V>> it = logFile.iterator();
			while(it.hasNext()){
				Entry<K,V> entry = it.next();
				if(entry.getKey().equals(key))
					return entry;
			}
			return null;
		}

		/**
		 * Returns an iterable collection of the entries with a given key. 
		 * If no entry has the specified key an empty collection will be returned.
		 * @param key the key to search.
		 * @return an iterable collection of the entries with a given key.
		 * If no entry has the specified key an empty collection will be returned.
		 * @throws InvalidKeyException if the key is not valid for this LogFile. 
		 */
		public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
			checkKey(key);
			PositionList<Entry<K, V>> list = new NodePositionList<Entry<K,V>>();
			Iterator<Entry<K, V>> it = logFile.iterator();
			while(it.hasNext()){
				Entry<K,V> entry = it.next();
				if(entry.getKey().equals(key))
					list.addLast(entry);
			}
			return list;
		}
		
		/**
		 * Inserts an entry in this LogFile.
		 * @param key the key to insert.
		 * @param value the value relative to the key to insert.
		 * @return the entry inserted in this LogFile.
		 * @throws InvalidKeyException if the key is not valid for this LogFile.
		 */
		@SuppressWarnings("unchecked")
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			Entry <K,V> entry = new MyEntry(key, value);
			logFile.addLast(entry);
			return entry;
		}
		
		/**
		 * Removes the specified entry from this LogFile.
		 * Returns the removed entry, null if this LogFile is empty 
		 * or if the entry does not belong to this LogFile.
		 * @param e the entry to remove.
		 * @return the removed entry, null if this LogFile is empty 
		 * or if the entry does not belong to this LogFile.
		 * @throws InvalidEntryException if the specified entry is not
		 * valid for this LogFile.
		 */
		public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
			checkEntry(e);
			if(logFile.isEmpty())
				return null;
			for(Position<Entry<K,V>> currentP : logFile.positions()){
				Entry<K,V>  current = currentP.element();
				if(current.equals(e)){
					numEl--;
					return logFile.remove(currentP);
				}
			}
			return null;
		}

		/**
		 * Returns an iterable collection view of the keys contained 
		 * in this dictionary.
		 * @return an iterable collection view of the keys contained 
		 * in this dictionary.
		 */
		public Iterable<K> keys() {
			PositionList<K> list = new NodePositionList<K>();
			for(Position<Entry<K, V>> pos : logFile.positions())
				list.addLast(pos.element().getKey());
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
			for(Position<Entry<K, V>> pos : logFile.positions())
				list.addLast(pos.element().getValue());
			return list;
		}
		
		/**
		 * Returns an iterable collection view of the entries contained 
		 * in this dictionary.
		 * @return an iterable collection view of the entries contained 
		 * in this dictionary.
		 */
		public Iterable<Entry<K, V>> entries() {
			PositionList<Entry<K, V>> list = new NodePositionList<Entry<K,V>>();
			for(Position<Entry<K, V>> pos : logFile.positions())
				list.addLast(pos.element());
			return list;
		}
		
		/**
		 * Returns true if this dictionary contains no key-value mappings.
		 * @return true if this dictionary contains no key-value mappings.  
		 */
		public boolean isEmpty() {
			return logFile.isEmpty();
		}

		/**
		 * Returns the number of entries in this LogFile.
		 * @return the number of entries in this LogFile.
		 */
		public int size() {
			return logFile.size();
		}

		/**
		 * Checks if the specified key is valid.
		 * @param key the key to check.
		 * @throws InvalidKeyException if the key is not valid.
		 */
		private void checkKey(K key) throws InvalidKeyException {
			if(key == null)
				throw new InvalidKeyException();
		}

		/**
		 * Checks if the specified entry is valid.
		 * @param e the entry to check.
		 * @throws InvalidEntryException if entry is not valid.
		 */
		@SuppressWarnings("unchecked")
		private void checkEntry(Entry<K,V> e) throws InvalidEntryException {
			if(e == null || !(e instanceof MyEntry))
				throw new InvalidEntryException();
		}

		@Override
		public String toString(){
			return logFile.toString();
		}
}
