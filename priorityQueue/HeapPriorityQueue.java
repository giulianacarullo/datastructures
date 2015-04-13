package priorityQueue;


import java.util.Comparator;
import comparator.DefaultComparator;
import entry.Entry;
import entry.MyEntry;
import exception.EmptyPriorityQueueException;
import exception.InvalidKeyException;
import binaryTree.ArrayListCompleteBinaryTree;
import binaryTree.CompleteBinaryTree;
import position.Position;

/**
 * 
 * The HeapPriorityQueue represents an unbounded PriorityQueue based on
 * a priority heap.This queue orders elements according to an order
 * specified at construction time, which is specified either 
 * according to their natural order (see {@link DefaultComparator}), 
 * or according to a {@link Comparator}, depending on which 
 * constructor is used. A constructor that create an HeapPriorityQueue 
 * in linear time is also provided. A PriorityQueue does not permit 
 * null keys.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key.
 * @param <V> the value.
 */
public class HeapPriorityQueue<K,V> implements PriorityQueue<K, V>{
	//Instance Variables
		protected CompleteBinaryTree<Entry<K,V>> heap;
		private Comparator<K> comp;
	
	//Constructors
		/**
		 * Creates an HeapPriorityQueue that orders its elements according 
		 * to their natural ordering (using {@link DefaultComparator}).
		 */
		public HeapPriorityQueue(){
			heap = new ArrayListCompleteBinaryTree<Entry<K,V>>();
			comp = new DefaultComparator<K>();
		}
		
		/**
		 * Creates an HeapPriorityQueue that orders its elements according 
		 * to the specified comparator.
		 * @param comp the comparator to use for this HeapPriorityQueue.
		 */
		public HeapPriorityQueue(Comparator<K> comp){
			heap = new ArrayListCompleteBinaryTree<Entry<K,V>>();
			this.comp = comp;
		}
	
		/**
		 * Creates an HeapPriorityQueue that orders its elements according 
		 * to the specified comparator in linear time.
		 * @param v the list of key to insert.
		 * @param k the list of value to insert(relative to value v).
		 * @param comp the comparator to use for this HeapPriorityQueue.
		 */
		@SuppressWarnings("unchecked")
		public HeapPriorityQueue(V[] v, K[] k, Comparator<K> comp){
			this(comp);
			for(int i = 0; i<v.length; i++){
				Entry<K,V> entry = new MyEntry(k[i], v[i]);
				heap.add(entry);
			}
			for(int i = size()/2; i>=1; i--)
				downHeap(((ArrayListCompleteBinaryTree<Entry<K,V>>)heap).atIndex(i));
		}
	
	
	//Methods
		
		/**
		 * Inserts an entry in this HeapPriorityQueue. 
		 * If a violation of the heap-order property succeed the 
		 * up-heap method is invoked to restore this property.
		 * @param key the key.
		 * @param value the value.
		 * @return the entry created.
		 * @throws InvalidKeyException if key is not valid.
		 */
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			Entry<K,V> entry = new MyEntry<K,V>(key, value);
			upHeap(heap.add(entry));
			return entry;
		}
	
	
		/**
		 * Returns the entry with the minimum key without removing it.
		 * @return the entry with the minimum key.
		 * @throws EmptyPriorityQueueException if this HeapPriorityQueue
		 * is empty.
		 */
		public Entry<K, V> min() throws EmptyPriorityQueueException {
			if(isEmpty())
				throw new EmptyPriorityQueueException();
			Entry<K, V> min = heap.root().element();
			return min;
		}
	
		/**
		 * Returns the entry with the minimum key removing it.
		 * We know that an entry with the smallest key is stored 
		 * at the root r of the heap. However, unless r is the only 
		 * internal node of the heap, we cannot simply delete r. 
		 * Besides, the heap-order property must be restored. 
		 * So downHeap method is invoked. 
		 * @return the entry with the minimum key.
		 * @throws EmptyPriorityQueueException if this HeapPriorityQueue
		 * is empty.
		 */
		public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
			if(isEmpty())
				throw new EmptyPriorityQueueException();
			Entry<K, V> min = heap.root().element();
			if(size() == 1) 
				heap.remove();
			else {
				heap.replace(heap.root(), heap.remove());
				downHeap(heap.root());
			}
			return min;
		}
	
		
		
		/**
		 * In a heap T, for every node v other than the root, the key
		 * stored at v is greater than or equal to the key stored at v's 
		 * parent. Let be z the parent of v. If key(z)> key(v) heap-order
		 * property must be restored.
		 * @param entry the entry to move up of one level.
		 */
		protected void upHeap(Position<Entry<K, V>> entry) {
			while(!heap.isRoot(entry)){
				Position<Entry<K, V>> tmp = heap.parent(entry);
				if(comp.compare(tmp.element().getKey(), entry.element().getKey())<=0)
					break;
				swap(tmp, entry); //???
				entry = tmp;
			}
		}
		
		/**
		 * In a heap T, for every node v other than the root, the key
		 * stored at v is greater than or equal to the key stored at v's 
		 * parent. Let be z the parent of v. If key(z)> key(v) heap-order
		 * property must be restored.
		 * @param entry the entry to move up of one level.
		 */
		protected void downHeap(Position<Entry<K, V>> entry) {
			while(heap.isInternal(entry)){
				Position<Entry<K, V>> s;
				if(!heap.hasRight(entry))
					s = heap.left(entry); 
				else if(comp.compare(heap.left(entry).element().getKey(), heap.right(entry).element().getKey())<0) //?
					s = heap.left(entry); 
				else
					s = heap.right(entry); 
				if(comp.compare(s.element().getKey(), entry.element().getKey())<0) {
					swap(entry, s);
					entry = s;
				}
				else break;
			}
	
		}
		
		
		/**
		 * Checks if a give key is valid.
		 * @param key the key to check. 
		 * @throws InvalidKeyException if the key is not valid.
		 */
		protected void checkKey(K key) {
			if(key == null)
				throw new InvalidKeyException();
		}
		
		/**
		 * Tests if this HeapPriorityQueue is empty. 
		 * @return true if and only if this HeapPriorityQueue 
		 * contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return (size() == 0);
		}
		
		/**
		 * Returns the number of elements stored in the HeapPriorityQueue.
		 * @return the number of elements stored in the HeapPriorityQueue.
		 */
		public int size() {
			return heap.size();
		}
	
		
		/**
		 * Auxiliary method -  swap two entry.
		 * @param x the position of the first entry to swap.
		 * @param y the position of the second entry to swap.
		 */
		protected void swap(Position<Entry<K, V>> x, Position<Entry<K, V>> y) {
			Entry<K, V> tmp = x.element();
			heap.replace(x, y.element());
			heap.replace(y, tmp);
		}
		
		@Override
		public String toString(){
			return heap.toString();
		}
}
