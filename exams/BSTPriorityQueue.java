package exams;

import java.util.Comparator;

import nodeList.NodePositionList;
import nodeList.PositionList;

import dictionary.BinarySearchTree;
import entry.Entry;
import exception.EmptyPriorityQueueException;
import exception.InvalidKeyException;
import position.Position;
import priorityQueue.PriorityQueue;

public class BSTPriorityQueue<K,V> implements PriorityQueue<K, V>{

	//Instance Variables
		protected BinarySearchTree<K, V> bst;
	
	//Constructor
		public BSTPriorityQueue(){
			bst = new BinarySearchTree<K, V>();
		}
		
		public BSTPriorityQueue(Comparator<K> comp){
			bst = new BinarySearchTree<K, V>(comp);
		}

		
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		return bst.insert(key, value);
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		PositionList<Entry<K, V>> list = new NodePositionList<Entry<K,V>>();
		inorder(bst.root(), list);
		return list.first().element();
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		return bst.remove(min());
	}

	@Override
	public int size() {
		return bst.size();
	}
	
	
	private void inorder(Position<Entry<K,V>> v, PositionList<Entry<K,V>> pos) {
		
		if(bst.hasLeft(v)) inorder(bst.left(v), pos);
		if(v.element() != null)
		pos.addLast(v.element());
		if(bst.hasRight(v)) inorder(bst.right(v), pos);
		bst.entries();
	}
	

}
