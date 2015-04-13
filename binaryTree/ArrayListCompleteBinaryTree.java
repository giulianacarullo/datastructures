package binaryTree;

import indexList.ArrayIndexList;
import indexList.IndexList;
import java.util.Iterator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import exception.BoundaryViolationException;
import exception.EmptyTreeException;
import position.InvalidPositionException;
import position.BTPos;
import position.Position;


/**
 * The ArrayListCompleteBinaryTree implements CompleteBinaryTree interface.
 * When an ArrayListCompleteBinaryTree is first created, it contains no items. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class ArrayListCompleteBinaryTree<E> implements CompleteBinaryTree<E>{

	//Instance Variables
		protected IndexList<BTPos<E>> T;
	
	
	//Constructor
		/**
		 * Constructs an empty ArrayListCompleteBinaryTree.
		 */
		public ArrayListCompleteBinaryTree(){
			T = new ArrayIndexList<BTPos<E>>();
			T.add(0, null);
		}
	
	
	//Methods
		private BTPos<E> checkPosition(Position<E> position){
			if(position == null || !(position instanceof BTPos<?> ))
				throw new InvalidPositionException();
			return (BTPos<E>) position;
		}
	
		/**
		 * Tests if a given node has the left child.
		 * If pos is has left child return true; false otherwise.
		 * @param pos the position to check.
		 * @return true if pos has left child, false otherwise.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */

		public boolean hasLeft(Position<E> pos) {
			BTPos<E> node = checkPosition(pos);
			return (2*node.index() <= size());
		}

		/**
		 * Tests if a given node has the left child.
		 * If pos is has right child return true; false otherwise.
		 * @param pos the position to check.
		 * @return true if pos has right child, false otherwise.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */
		public boolean hasRight(Position<E> pos){
			BTPos<E> node = checkPosition(pos);
			return (1+2*node.index() <= size());
		}

		/**
		 * Returns the left child of a given Position(if exists).
		 * @param pos the parent's position.
		 * @return the left child of pos.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */
		@SuppressWarnings("unchecked")
		public Position<E> left(Position<E> pos)  {
			if(!hasLeft(pos))
				throw new BoundaryViolationException();
			BTPos<E> position = (BTPos) pos;
			return (BTPos<E>) T.get(2*position.index());
		}
		
		/**
		 * Returns the right child of a given Position(if exists).
		 * @param pos the parent's position.
		 * @return the right child of pos.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */
		@SuppressWarnings("unchecked")
		public Position<E> right(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
			if(!hasRight(pos))
				throw new BoundaryViolationException();
			BTPos<E> position = (BTPos) pos;
			return (BTPos<E>) T.get(1+2*position.index());
		}

		/**
		 * Tests if a position pos is a leaf. If pos is a leaf return true;
		 * false otherwise.
		 * @param pos the position to check.
		 * @return true if pos is a leaf, false otherwise.
		 * @throws InvalidPositionException if pos is a position not valid.
		 * 
		 */
		public boolean isExternal(Position<E> pos) throws InvalidPositionException {
			if(hasRight(pos) || hasLeft(pos))
				return false;
			return true;
		}

		/**
		 * Tests if a position pos is a leaf. If pos is a leaf return false;
		 * true otherwise.
		 * @param pos the position to check.
		 * @return false if pos is a leaf, true otherwise.
		 * @throws InvalidPositionException if pos is a position not valid.
		 * 
		 */
		public boolean isInternal(Position<E> pos) throws InvalidPositionException {
			return (!isExternal(pos));
		}

		/**
		 * Tests if a position p is the root of this ArrayListCompleteBinaryTree.
		 * If p is the root return true; false otherwise.
		 * @param p the position to check.
		 * @return true if p is the root, false otherwise.
		 * @throws InvalidPositionException if p is a position not valid.
		 */
		public boolean isRoot(Position<E> p) throws InvalidPositionException {
			if(isEmpty())
				return false;
			BTPos<E> node = checkPosition(p);
			//return (node.index()==1);
			return (node == T.get(1));
		}

		
		/**
		 * Returns the root of this ArrayListCompleteBinaryTree.
		 * @return the root of this ArrayListCompleteBinaryTree.
		 * @throws EmptyTreeException if this ArrayListCompleteBinaryTree is empty.
		 */
		public Position<E> root() throws EmptyTreeException {
			if(T.isEmpty())
				throw new EmptyTreeException();
			return T.get(1);
		}
		
		/**
		 * Returns the parent of a given Position p.
		 * @param p the Position to check.
		 * @return the parent's position of p.
		 * @throws InvalidPositionException if p is a position not valid.
		 * @throws BoundaryViolationException when invoked on root.
		 * 
		 */
		public Position<E> parent(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
			BTPos<E> node = checkPosition(p);
			if(isRoot(p))
				throw new BoundaryViolationException();
			return(T.get(node.index()/2));
		}
		
		
		/**
		 * Returns an iterable positions' collection of the children
		 * of a given position p.
		 * @param p the parent position.
		 * @return an iterable positions' collection of the children of p
		 * @throws InvalidPositionException if p is a leaf.
		 */
		public Iterable<Position<E>> children(Position<E> p) throws InvalidPositionException {
			if(isExternal(p))
				throw new InvalidPositionException();
			NodePositionList<Position<E>> positions = new NodePositionList<Position<E>>();
			if(hasLeft(p))
				positions.addLast(left(p));
			if(hasRight(p))
				positions.addLast(right(p));
			return positions;
		
		}
		
		/**
		 * Adds an element as a child.
		 * @param e the element to insert.
		 * @return the Position of the inserted element.
		 */
		public Position<E> add(E e) {
			int i = size() + 1; // size() + 1=T.size()
			BTPos<E> p = new BTPos<E>(e,i);
			T.add(i, p);
			return p;
		 }

		/**
		 * Removes last child in this ArrayListCompleteBinaryTree.
		 * @return the element deleted.
		 * @throws EmptyTreeException if invoked on an empty ArrayListCompleteBinaryTree.
		 */
		public E remove(){
			if(isEmpty()) throw new EmptyTreeException();
			return T.remove(size()).element(); //size()=T.size()-1
		}

		
		/**
		 * Sets the element currently stored in the position taken in input(p) 
		 * with another element el(taken in input). 
		 * @param p the position of the element to set.
		 * @param el the new element to store.
		 * @return the element stored(before modification)in p.
		 * @throws InvalidPositionException if p is a position not valid.
		 */
		public E replace(Position<E> p, E el) throws InvalidPositionException {
			BTPos<E> node = checkPosition(p);
			E old = node.element();
			node.setElement(el);
			return old;
		}

		/**
		 * Tests if this LinkedBinaryTree is empty. 
		 * @return true if and only if this ArrayListCompleteBinaryTree contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return (T.size()== 0);
		}

		/**
		 * Returns the number of elements stored in the ArrayListCompleteBinaryTree.
		 * @return the number of elements stored in the ArrayListCompleteBinaryTree.
		 */
		public int size() {
			return T.size()-1;
		}
		
		/**
		 * Returns an iterable collection of the positions in this ArrayListCompleteBinaryTree.
		 * @return an iterable collection of the positions in this ArrayListCompleteBinaryTree.
		 */
		public Iterable<Position<E>> positions() {
			PositionList<Position<E>> P = new NodePositionList<Position<E>>();
			for(int i = 1; i<T.size(); i++)
			   P.addLast(T.get(i));
			return P;
		}
		
		/**
		 * Returns an iterator of the elements in this ArrayListCompleteBinaryTree.
		 * (in proper sequence).
		 * @return an elements' iterator
		 */
		public Iterator<E> iterator() {
			PositionList<E> list = new NodePositionList<E>();
			for(int i = 1; i<T.size(); i++)
				list.addLast(T.get(i).element());
			return list.iterator();
		}
	
		/**
		 * Returns the Position relative to a given index.
		 * @param i the index
		 * @return the position relative to i.
		 */
		public Position<E> atIndex(int i){
			return T.get(i);
		}
		
		@Override
		public String toString(){
			  String s;
			  s = "[";
			  if (size() > 0) s+= root().element();
			  if (size() > 1){
				  int x = 0;
				  Iterable<Position<E>> itPos = positions();
				  for(Position<E> pos : itPos){
					  if(x!=0)
						  s += " - " + pos.element();
					  else
						  x++;
				  }
			  }
			  return s+"]";
		}

}
