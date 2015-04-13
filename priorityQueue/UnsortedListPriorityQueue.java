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
 * The UnsortedListPriorityQueue class implements PriorityQueue with
 * a PositionList. Here entry are saved in FIFO order(not ordered in 
 * relation to the key value).
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */
public class UnsortedListPriorityQueue<K,V> implements PriorityQueue<K, V>{

	//Instance Variables
		Comparator<K> c;
		PositionList<Entry<K, V>> entries;
		
	//Constructors
		
		/**
		 * Constructs an empty UnsortedListPriorityQueue.
		 */
		public UnsortedListPriorityQueue() {
			entries = new NodePositionList<Entry<K,V>>();
			c = new DefaultComparator<K>();
		}
		
		/**
		 * Constructs an empty UnsortedListPriorityQueue with a 
		 * given Comparator.
		 * @param c the Comparator.
		 */
		public UnsortedListPriorityQueue(Comparator<K> c) {
			this.c = c;
			entries = new NodePositionList<Entry<K,V>>();
		}
	
	//Methods
		
		/**
		 * Inserts an entry in this UnsortedListPriorityQueue.
		 * @param key the key.
		 * @param value the value.
		 * @return the entry created.
		 * @throws InvalidKeyException if key is not valid.
		 */
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			Entry<K, V> entry = new MyEntry<K, V>(key,value);
			entries.addLast(entry);
			return entry;
		}
	
		/**
		 * Returns the entry with the minimum key without removing it.
		 * @return the entry with the minimum key.
		 * @throws EmptyPriorityQueueException if this UnsortedListPriorityQueue
		 * is empty.
		 */
		public Entry<K, V> min() throws EmptyPriorityQueueException {
			if(isEmpty())
				throw new EmptyPriorityQueueException();
			Entry<K,V> min = entries.first().element();
			Position<Entry<K,V>> current = entries.first();
			current = entries.next(current);
			//while(current != null){
			for(int i = 1; i<entries.size(); i++){
				if(c.compare(min.getKey(), current.element().getKey())>0)
					min = current.element();
				try{
					current = entries.next(current);
				}
				catch(Exception e){}
			}
			return min;
		}

		/**
		 * Returns the entry with the minimum key removing it.
		 * @return the entry with the minimum key.
		 * @throws EmptyPriorityQueueException if this UnsortedListPriorityQueue
		 * is empty.
		 */
		public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
			if(isEmpty())
				throw new EmptyPriorityQueueException();
			if(size() == 1)
				return entries.remove(entries.first());
			Position<Entry<K,V>> min = entries.first();
			Position<Entry<K,V>> current = entries.first();
			current = entries.next(current);
			for(int i = 1; i<entries.size(); i++){
				if(c.compare(min.element().getKey(), current.element().getKey())>0)
					min = current;
					try{
						current = entries.next(current);
					}
					catch(Exception e){}
			}
			Entry<K, V> tmp = min.element();
			entries.remove(min);
			return tmp;
		}

		/**
		 * Tests if this LinkedBinaryTree is empty. 
		 * @return true if and only if this ArrayListCompleteBinaryTree contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return size()==0;
		}
	
		/**
		 * Returns the number of elements stored in the ArrayListCompleteBinaryTree.
		 * @return the number of elements stored in the ArrayListCompleteBinaryTree.
		 */
		public int size() {
			return entries.size();
		}
		
		/**
		 * Tests if the is valid for this UnsortedListPriorityQueue.
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
