package priorityQueue;

import java.util.Comparator;
import comparator.DefaultComparator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import entry.Entry;
import entry.MyEntry;
import exception.EmptyPriorityQueueException;
import exception.InvalidKeyException;

import position.Position;


/**
 * The SortedListPriorityQueue class implements PriorityQueue with
 * a PositionList. Here entry are ordered in relation to the key value.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */

public class SortedListPriorityQueue<K, V> implements PriorityQueue<K, V> {
	//Instance Variables
		Comparator<K> c;
		PositionList<Entry<K, V>> entries;
		
	//Constructors
		
		/**
		 * Constructs an empty SortedListPriorityQueue.
		 */
		public SortedListPriorityQueue() {
			entries = new NodePositionList<Entry<K,V>>();
			c = new DefaultComparator<K>();
		}
		
		/**
		 * Constructs an empty SortedListPriorityQueue with a 
		 * given Comparator.
		 * @param c the Comparator.
		 */
		public SortedListPriorityQueue(Comparator<K> c) {
			this.c = c;
			entries = new NodePositionList<Entry<K,V>>();
		}
	
	//Methods
	
		/**
		 * Inserts an entry in this SortedListPriorityQueue.
		 * @param key the key.
		 * @param value the value.
		 * @return the entry created.
		 * @throws InvalidKeyException if key is not valid.
		 */
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			Entry<K, V> entry = new MyEntry<K, V>(key,value);
			insertEntry(entry);
			return entry;
		}

		/**
		 * Auxiliary method - Used for ordered insertion in this 
		 * SortedListPriorityQueue.
		 * @param entry the entry to insert.
		 */
		private void insertEntry(Entry<K, V> entry) {
			if(isEmpty())
				entries.addFirst(entry);
			else if(c.compare(entry.getKey(), entries.last().element().getKey())>0)
				entries.addLast(entry);
			else {
				Position<Entry<K,V>> current = entries.first();
				while(c.compare(entry.getKey(), current.element().getKey())>0)
					current = entries.next(current);
				entries.addBefore(current, entry);
			}
		}

		/**
		 * Returns the entry with the minimum key without removing it.
		 * @return the entry with the minimum key.
		 * @throws EmptyPriorityQueueException if this SortedListPriorityQueue
		 * is empty.
		 */
		public Entry<K, V> min() {
			if(isEmpty())
				throw new EmptyPriorityQueueException();
			return entries.first().element();
		}
		
		/**
		 * Returns the entry with the minimum key removing it.
		 * @return the entry with the minimum key.
		 * @throws EmptyPriorityQueueException if this SortedListPriorityQueue
		 * is empty.
		 */
		public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
			if(isEmpty())
				throw new EmptyPriorityQueueException();
			return entries.remove(entries.first());
		}
		
		/**
		 * Tests if this SortedListPriorityQueue is empty. 
		 * @return true if and only if this SortedListPriorityQueue 
		 * contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return entries.isEmpty();
		}

		/**
		 * Returns the number of elements stored in the SortedListPriorityQueue.
		 * @return the number of elements stored in the SortedListPriorityQueue.
		 */
		public int size() {
			return entries.size();
		}
		
		/**
		 * Tests if the is valid for this SortedListPriorityQueue.
		 * @param key the key to check.
		 * @return true if the key is valid; false otherwise.
		 */
		private boolean checkKey(K key) {
			boolean result;
			try {
				result = ((c.compare(key, key) == 0));
			}
			catch(ClassCastException e){
				throw new InvalidKeyException();}
			return result;
		}
		
		@Override
		public String toString(){
			  return entries.toString();
		}
}
