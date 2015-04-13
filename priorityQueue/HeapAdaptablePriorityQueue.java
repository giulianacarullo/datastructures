package priorityQueue;

import java.util.Comparator;

import comparator.DefaultComparator;

import entry.Entry;
import exception.InvalidEntryException;
import exception.InvalidKeyException;
import position.Position;


/**
 * 
 * The HeapAdaptablePriorityQueue represents an unbounded HeapAdaptablePriorityQueue.
 * This class extends {@link HeapPriorityQueue} providing additional 
 * methods that would be useful in some situations.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */

public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V> implements AdaptablePriorityQueue<K, V>{
	//Constructors
		
		/**
		 * Creates an HeapAdaptablePriorityQueue that orders its elements according 
		 * to their natural ordering (using {@link DefaultComparator}).
		 */
		public HeapAdaptablePriorityQueue(){
			super();
		}
	
		/**
		 * Creates an HeapAdaptablePriorityQueue that orders its elements according 
		 * to the specified comparator.
		 * @param comp the comparator to use for this HeapAdaptablePriorityQueue.
		 */
		public HeapAdaptablePriorityQueue(Comparator<K> comp){
			super(comp);
		}
		
		/**
		 * Creates an HeapAdaptablePriorityQueue that orders its elements according 
		 * to the specified comparator in linear time.
		 * @param v the list of key to insert.
		 * @param k the list of value to insert(relative to value v).
		 * @param comp the comparator to use for this HeapAdaptablePriorityQueue.
		 */
		public HeapAdaptablePriorityQueue(V[] v, K[] k, Comparator<K> comp){
			super(v,k,comp);
		}
	
	/*Methods*/
		
		/**
		 * Inserts an entry in this HeapPriorityQueue. 
		 * If a violation of the heap-order property succeed the 
		 * up-heap method is invoked to restore this property.
		 * @param k the key.
		 * @param v the value.
		 * @return the entry created.
		 * @throws InvalidKeyException if key is not valid.
		 */
		public Entry<K,V> insert (K k, V v) throws InvalidKeyException {
			checkKey(k);
			LocationAwareEntry<K,V> entry = new
			LocationAwareEntry<K,V>(k,v);
			Position <Entry<K,V>> z = heap.add(entry);
			entry.setLocation(z);
			upHeap(z);
			return entry;
		}
		
		
		
		/**
		 * Removes a given entry from this HeapAdaptablePriorityQueue.
		 * @param entry the entry to remove.
		 * @return the entry removed.
		 */
		public Entry<K, V> remove(Entry<K,V> entry) {
			LocationAwareEntry<K,V> ee = checkEntry(entry);
			Position < Entry<K,V>>p = ee.location();
			if(size() == 1)
				return (Entry<K,V>) heap.remove();
			replaceEntry(p,(LocationAwareEntry<K,V>)heap.remove());
			upHeap(p);
			downHeap(p);
			ee.setLocation(null);
			return ee;
		}

		/**
		 * Sets the key of a given entry with another key.
		 * @param entry the entry to modify.
		 * @param key the new key.
		 * @return the key previously stored in the entry.
		 * @throws InvalidKeyException if the given key is not valid.
		 * 
		 */
		public K replaceKey(Entry<K, V> entry, K key) throws InvalidKeyException {
			checkKey(key);
			LocationAwareEntry<K,V> ee = checkEntry(entry);
			K oldKey = ee.setKey(key);
			upHeap(ee.location());			
			downHeap(ee.location());
			return oldKey;
		}

		/**
		 * Sets the value of a given entry with another value.
		 * @param entry the entry to modify.
		 * @param value the new value.
		 * @return the value previously stored in the entry.
		 * @throws InvalidKeyException if the given key is not valid.
		 * 
		 */
		public V replaceValue(Entry<K, V> entry, V value) throws InvalidKeyException {
			LocationAwareEntry<K,V> ee = checkEntry(entry);
			return ee.setValue(value);
		}
	
		/**
		 * Checks if a given entry is valid for this HeapAdaptablePriorityQueue.
		 * @param entry the entry to check.
		 * @return the entry casted to LocationAwareEntry.
		 */
		private LocationAwareEntry<K, V> checkEntry(Entry<K,V> entry) {
			if(entry == null)
				throw new InvalidEntryException();
			try {
				return (LocationAwareEntry<K, V>) entry;
			}
			catch(ClassCastException e){
				throw new InvalidEntryException();
			}
		}

		/**
		 * Auxiliary method - Sets the entry of a given Position with 
		 * another entry.
		 * @param p the position to modify.
		 * @param entry the new entry.
		 * @return the old(????) Position.
		 */
		private Position<Entry<K, V>> replaceEntry(Position<Entry<K, V>> p, LocationAwareEntry<K, V> entry) {
			heap.replace(p, entry);
			return entry.setLocation(p);		
		}
		
	    protected void swap(Position<Entry<K,V>> u,Position<Entry<K,V>> v)
	    {
	    		super.swap(u,v);
	    		LocationAwareEntry<K, V> locU = (LocationAwareEntry<K, V>)u.element();
	    		locU.setLocation(u);
	    		LocationAwareEntry<K, V> locV = (LocationAwareEntry<K, V>)v.element();
	    		locV.setLocation(v);
	    }
}
