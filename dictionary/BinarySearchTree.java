package dictionary;


import java.util.Comparator;
import comparator.DefaultComparator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import entry.Entry;
import exception.InvalidEntryException;
import exception.InvalidKeyException;
import position.BTNode;
import position.InvalidPositionException;
import position.Position;
import binaryTree.LinkedBinaryTree;

/**
 * The BinarySearchTree class implements dictionary with 
 * a {@link LinkedBinaryTree}. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <K> the key type.
 * @param <V> the value type.
 */

public class BinarySearchTree<K,V> extends LinkedBinaryTree<Entry<K, V>> implements Dictionary<K, V>{
	/*Instance Variables*/ 
		private Comparator<K> comparator;
		private int numEntries = 0;
	 
	 /*Constructors*/
		
		/**
		 * Constructs an empty BinarySearchTree that use a {@link DefaultComparator}.
		 */
		public BinarySearchTree() {
			comparator = new DefaultComparator<K>();
			addRoot(null);
		}
	
		/**
		 * Constructs an empty BinarySearchTree with a given Comparator.
		 * @param comp the comparator to use for this BinarySearchTrees.
		 */
		public BinarySearchTree(Comparator<K> comp) {
			comparator = comp;
			addRoot(null);
		}
	
	//Methods
		
		/**
		 * Finds the entry relative to the given key in this BinarySearchTree
		 * and returns it.
		 * @param key the key to search.
		 * @return the entry relative to the searched key.
		 * @throws InvalidKeyException if the specified key is not valid.
		 */
		public Entry<K, V> find(K key) throws InvalidKeyException {
			checkKey(key);
			return treeSearch(key, root).element();
		}
		
		/**
		 * Finds  all entries with the specified key in this BinarySearchTree
		 * and returns them.
		 * @param key the key to search.
		 * @return an iterable collection of all the entries with the given key.
		 * @throws InvalidKeyException if the specified key is not valid.
		 */
		public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
			checkKey(key);
			PositionList<Entry<K, V>> list = new NodePositionList<Entry<K,V>>();
			addAll(list, root, key);
			return list;
		}

		/**Auxiliary method used by find, remove e insert methods.
		 * Searches a key into this BinarySearchTree. 
		 * @param key the key to search.
		 * @param pos  the start position.
		 * @return the position at which the entry with the given key is stored.
		 */
		protected Position<Entry<K,V>> treeSearch(K key, Position<Entry<K,V>>
		pos) {
			if (isExternal(pos)) return pos;
			else {
				K curKey = key(pos);
				int comp = comparator.compare(key, curKey);
				if (comp < 0)
					return treeSearch(key, left(pos));
				else if (comp > 0)
					return treeSearch(key, right(pos));
				return pos;
			}
		}

	
		/**
		 * Inserts an entry with a given key and a given value into this
		 * BinarySearchTree. 
		 * @param key the key to insert.
		 * @param value the value to insert.
		 * @return the entry added to this BinarySearchTree. 
		 * @throws InvalidKeyException if the specified key is not valid.
		 */
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			checkKey(key);
			Position<Entry<K, V>> pos = treeSearch(key, root);
			while (!isExternal(pos))
				pos = treeSearch(key, left(pos));
			Entry<K,V> entry = new BSTEntry<K, V>(key, value, pos);
			return insertAtExternal(pos, entry);
		}

		/**
		 * Auxiliary method - Adds an entry ad a leaf.
		 * @param pos the position at which add the entry. 
		 * @param entry the entry to add.
		 * @return the inserted entry.
		 */
		protected Entry<K, V> insertAtExternal(Position<Entry<K, V>> pos, Entry<K, V> entry) {
			expandExternal(pos,null,null);
			replace(pos, entry);
			numEntries++;
			return entry;
		}


		/**
		 * Removes the specified entry from this BinarySearchTree.
		 * @param ent the entry to remove.
		 * @return the removed entry.
		 * @throws InvalidEntryException if the specified entry is not
		 * valid.
		 */
		public Entry<K, V> remove(Entry<K, V> ent) throws InvalidEntryException {
			checkEntry(ent);
			Position<Entry<K,V>> remPos = ((BSTEntry<K,V>) ent).position();
			Entry<K,V> toReturn = ent;
			//caso 1: uno dei due figli è una foglia
			if (isExternal(left(remPos)))  
				remPos = left(remPos); 
			else if (isExternal(right(remPos))) 
				remPos = right(remPos);
			else {
				Position<Entry<K,V>> swapPos = remPos;
				// search for ent's successor
				remPos = right(swapPos);
				do
					remPos = left(remPos);
			    while (isInternal(remPos)); //si ferma quando remPos e` una foglia
			    	replaceEntry(swapPos, parent(remPos).element());
			  }

			  removeExternal(remPos);
			  return toReturn;
		}

	
		/**
		 * Auxiliary method - Removes an external position. 
		 * @param v the external position "to remove".
		 * @throws InvalidPositionException if the specified position is
		 *  not external
		 */
		@SuppressWarnings("unchecked")
		private void removeExternal(Position<Entry<K, V>> v) {
			if (!isExternal(v)) 
				throw new InvalidPositionException();
				BTNode p = (BTNode) parent(v);
				BTNode s = (BTNode) sibling(v);
				if (isRoot(p)) { //se il padre di v è la radice
				 	 s.setParent(null); 
				 	 root = s; //allora il fratello di v diventa radice
				 } 
				 //altrimenti il fratello di v diventa figlio del padre(?)
				else { 
					BTNode g = (BTNode) parent(p);
				    if (p == left(g)) 
				    	g.setLeft(s);
				    else g.setRight(s);
				    	s.setParent(g); 
				  }
				 numEntries -= 1;
		}

		/**
		 * Returns an iterable collection view of the keys contained 
		 * in this dictionary.
		 * @return an iterable collection view of the keys contained 
		 * in this dictionary.
		 */
		public Iterable<K> keys() {
			PositionList<K> list = new NodePositionList<K>();
			for(Position<Entry<K, V>> pos :super.positions())
				if(pos.element()!=null)
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
			for(Position<Entry<K, V>> pos :super.positions())
				if(pos.element() != null)
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
			for(Position<Entry<K, V>> pos :super.positions())
				if(pos.element()!=null)
					list.addLast(pos.element());
			return list;
		}

		/**
		 *Returns the key stored into the specified Position.
		 *@param position the position of the wanted key.
		 *@return the key stored into the specified Position.
		 */
		protected K key(Position<Entry<K,V>> position){
			return position.element().getKey();
		   
		}
		

		/**
		 *Returns the value stored into the specified Position.
		 *@param position the position of the wanted value.
		 *@return the value stored into the specified Position.
		 */
		protected V value(Position<Entry<K,V>> position){
			return position.element().getValue();	
		}
	 
	 

		/**
		 *Returns the entry stored into the specified Position.
		 *@param position the position of the wanted entry.
		 *@return the entry stored into the specified Position.
		 */
		protected Entry<K,V> entry(Position<Entry<K,V>> position){
			return position.element();
		}
	 
		/**
		 * Replaces the entry of a given Position with another entry.
		 * @param pos the position of the entry to replace.
		 * @param entry the new entry.
		 * @throws InvalidEntryException if the specified entry is not valid.
		 * 
		 */
		protected void replaceEntry(Position <Entry<K,V>> pos, Entry<K,V> entry){
			checkEntry(entry);
			((BSTEntry<K, V>) entry).pos = pos;
			replace(pos, entry);
		}
	 
	 
		/**
		 * Adds all the specified entries in this BinarySearchTree at a 
		 * specified Position.
		 * @param list the list of entries to add.
		 * @param node
		 * @param key
		 */
		protected void addAll(PositionList<Entry<K,V>> list, Position<Entry<K,V>> node, K key){
			if(isExternal(node))
				return;
			Position<Entry<K, V>> pos = treeSearch(key, node);
			if(!isExternal(pos)){ //found an element
				addAll(list, left(pos), key);
				list.addLast(pos.element());
			 addAll(list, right(pos), key);
			}
		}

	 
	//Checking entry
		@SuppressWarnings("unchecked")
		/**
		 * Checks if the specified entry is valid.
		 * @param entry the entry to check.
		 * @throws InvalidEntryException if the entry is not valid.
		 */
		protected void checkEntry(Entry<K,V> entry) throws InvalidEntryException {
		  if(entry == null || !(entry instanceof BSTEntry))
		    throw new InvalidEntryException();
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
		
	 //Successor
		
		/**
		 * Returns the successor of a given entry.
		 * @param entry the entry of which we want the successor.
		 * @return the entry's successor.
		 */
		public Entry<K,V> successor(Entry<K,V> entry){
			checkEntry(entry);
			Position<Entry<K,V>> pos = ((BSTEntry<K, V>)entry).pos; //altrimenti position()
			//Position<Entry<K, V>> parent = parent(currentNode);
			if(hasRight(pos) && isInternal(pos))
				pos = right(pos);
			while(hasLeft(pos) && isInternal(pos))
				pos = left(pos);
		  
			while(!isRoot(pos) && right(parent(pos))==pos)
				pos=parent(pos);
			if(pos!=root())
				return parent(pos).element();
			return null; //se l'entry è il massimo
		}
	 
		
		/**
		 * Returns the predecessor of a given entry.
		 * @param entry the entry of which we want the predecessor.
		 * @return the entry's predecessor.
		 */
		public Entry<K,V> predecessor(Entry<K, V> entry){
			checkEntry(entry);
			Position<Entry<K, V>>currentNode = ((BSTEntry<K, V>)entry).pos;
			Position<Entry<K, V>> parent = parent(currentNode);
			currentNode = left(parent);
			while(hasRight(currentNode))
				currentNode = right(currentNode);
			return parent(currentNode).element(); //poiché le ultime foglie sono vuote
		}
		
		/**
		 * Returns the number of entries in this LogFile.
		 * @return the number of entries in this LogFile.
		 */
		 public int size(){
			 return numEntries;
		 }
		
		 /**
		 * Returns true if this dictionary contains no key-value mappings.
		 * @return true if this dictionary contains no key-value mappings.  
		 */
		public boolean isEmpty(){
			return numEntries==0;
		}
		
		@Override
		public String toString(){ //inorder - sequenza in ordine crescente
			  String s;
			  s = "[";
			  if (size() > 0 && root.element()!=null) s+= root.element();
			  if (size() > 1){
				  int x = 0;
				  Iterable<Position<Entry<K,V>>> itPos = positions();
				  for(Position<Entry<K, V>> pos : itPos){
					  if(x!=0 && pos.element()!=null)
						  s += " - " + pos.element();
					  else
						  x++;
				  }
			  }
			  return s+"]";
		}
		

}