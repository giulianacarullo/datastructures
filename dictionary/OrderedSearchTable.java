package dictionary;

import indexList.ArrayIndexList;
import indexList.IndexList;
import java.util.Comparator;
import comparator.DefaultComparator;
import nodeList.NodePositionList;
import nodeList.PositionList;



import entry.Entry;
import entry.MyEntry;
import exception.InvalidEntryException;
/*This class implements a dictionary from an ordered arrayList*/
import exception.InvalidKeyException;

/**
 * This class implements Dictionary interface with an {@link ArrayIndexList}
 * of entries.
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key type.
 * @param <V> the value type.
 */
public class OrderedSearchTable<K,V> implements Dictionary<K, V> {
	//Instance Variables
		private Comparator<K> comp;
		private IndexList<Entry<K,V>> list;
	
	//Constructors
		
		/**
		 * Constructs an OrderedSearchTable with a {@link DefaultComparator}.
		 */
		public OrderedSearchTable(){
			comp = new DefaultComparator<K>();
			list = new ArrayIndexList<Entry<K,V>>();
		}
		
		/**
		 * Constructs an OrderedSearchTable with a given Comparator.
		 * @param comp the comparator to use for this OrderedSearchTable.
		 */
		public OrderedSearchTable(Comparator<K> comp){
			this.comp = comp;
			list = new ArrayIndexList<Entry<K,V>>();
		}
	
	//Methods
		
		/**
		 * Returns the first entry with the given key if exists; false otherwise.
		 * @param key the key to search.
		 * @return first entry with the given key if exists; false otherwise.
		 * @throws InvalidKeyException if the key is not valid for this OrderedSearchTable.
		 */
		public Entry<K, V> find(K key) throws InvalidKeyException {
			checkKey(key);
			for(int i = 0; i<list.size(); i++)
				if(comp.compare(key, list.get(i).getKey()) == 0) 
					return list.get(i);	
			return null;
		}

		/**
		 * Returns an iterable collection of the entries with a given key. 
		 * If no entry has the specified key an empty collection will be returned.
		 * @param key the key to search.
		 * @return an iterable collection of the entries with a given key.
		 * If no entry has the specified key an empty collection will be returned.
		 * @throws InvalidKeyException if the key is not valid for this OrderedSearchTable. 
		 */
		public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
			checkKey(key);
			PositionList<Entry<K, V>> all = new NodePositionList<Entry<K,V>>();
			for(int i = 0; i<list.size(); i++)
				if(comp.compare(key, list.get(i).getKey()) == 0) 
					all.addLast(list.get(i));
			return all;
		}

		/**
		 * Inserts an entry in this OrderedSearchTable.
		 * @param key the key to insert.
		 * @param value the value relative to the key to insert.
		 * @return the entry inserted in this OrderedSearchTable.
		 * @throws InvalidKeyException if the key is not valid for this OrderedSearchTable.
		 */
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			Entry<K, V> entry = new MyEntry<K, V>(key,value);
			insertEntry(entry);
			return entry;
		}

		/**
		 * Auxiliary method - Inserts the entry in this OrderedSearchTable.
		 * @param entry the entry to insert in this OrderedSearchTable.
		 */
		private void insertEntry(Entry<K, V> entry) {
			if(isEmpty())
				list.add(0, entry);
			else if(comp.compare(entry.getKey(), list.get(size()-1).getKey())>0)
				list.add(size(), entry);
			else {
				for(int i = 0; i<size(); i++)
					if(comp.compare(entry.getKey(), list.get(i).getKey())<0){
						list.add(i, entry);
						break;
					}
			}
		}

		/**
		 * Removes the specified entry from this OrderedSearchTable.
		 * Returns the removed entry, null if this OrderedSearchTable is empty 
		 * or if the entry does not belong to this OrderedSearchTable.
		 * @param entry the entry to remove.
		 * @return the removed entry, null if this OrderedSearchTable is empty 
		 * or if the entry does not belong to this OrderedSearchTable.
		 * @throws InvalidEntryException if the specified entry is not
		 * valid for this OrderedSearchTable.
		 */
		public Entry<K, V> remove(Entry<K, V> entry) throws InvalidEntryException {
			checkEntry(entry);
			for(int i = 0; i<list.size(); i++)
				if(comp.compare(entry.getKey(), list.get(i).getKey()) == 0) {
					list.remove(i);
					return entry;
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
			PositionList<K> entries = new NodePositionList<K>();
			for(int i = 0; i<list.size(); i++) 
				entries.addLast(list.get(i).getKey());
			return entries;
		}
		
		/**
		 * Returns an iterable collection view of the values contained 
		 * in this dictionary.
		 * @return an iterable collection view of the values contained 
		 * in this dictionary.
		 */
		public Iterable<V> values() {
			PositionList<V> entries = new NodePositionList<V>();
			for(int i = 0; i<list.size(); i++) 
				entries.addLast(list.get(i).getValue());
			return entries;
		}

		/**
		 * Returns an iterable collection view of the entries contained 
		 * in this dictionary.
		 * @return an iterable collection view of the entries contained 
		 * in this dictionary.
		 */
		public Iterable<Entry<K, V>> entries() {
			PositionList<Entry<K, V>> entries = new NodePositionList<Entry<K, V>>();
			for(int i = 0; i<list.size(); i++) 
				entries.addLast(list.get(i));
			return entries;
		}

		/**
		 * Returns true if this dictionary contains no key-value mappings.
		 * @return true if this dictionary contains no key-value mappings.  
		 */
		public boolean isEmpty() {
			return list.isEmpty();
		}

		/**
		 * Returns the number of entries in this OrderedSearchTable.
		 * @return the number of entries in this OrderedSearchTable.
		 */
		public int size() {
			return list.size();
		}

		/**
		 * Checks if the specified key is valid.
		 * @param key the key to check.
		 * @throws InvalidKeyException if the key is not valid.
		 */
		private void checkKey(K key) {
			if(key == null)
				throw new exception.InvalidKeyException();
		}
		
		/**
		 * Checks if the specified entry is valid.
		 * @param e the entry to check.
		 * @throws InvalidEntryException if entry is not valid.
		 */
		private void checkEntry(Entry<K, V> e) {
			if(e == null)
				throw new InvalidEntryException();
		}

		@Override
		public String toString(){
			return list.toString();
		}

}
