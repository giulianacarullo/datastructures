package binaryTree;

import java.util.Iterator;

import nodeList.NodePositionList;
import nodeList.PositionList;
import exception.BoundaryViolationException;
import exception.EmptyTreeException;
import exception.NonEmptyTreeException;
import position.BTNode;
import position.BTPosition;
import position.InvalidPositionException;
import position.Position;
import tree.LinkedTree;

/**
 * The LinkedBinaryTree implements BinaryTree interface.
 * When a LinkedBinaryTree is first created, it contains no items.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 *  
 * 
 */

public class LinkedBinaryTree<E> implements BinaryTree<E> {

	//Instance Variables
		protected int size;
		protected BTPosition<E> root;
	
	//Constructor
		/**
		 * Constructs an empty LinkedBinaryTree
		 */
		public LinkedBinaryTree(){
			root = null;
			size = 0;
		}
		
	//Methods
		
		/**
		 * Tests if a given node has the left child.
		 * If pos is has left child return true; false otherwise.
		 * @param pos the position to check.
		 * @return true if pos has left child, false otherwise.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */
		public boolean hasLeft(Position<E> pos) throws InvalidPositionException {
			BTNode<E> node = checkPosition(pos);
			if (node.getLeft() == null)
				return false;
			return true;
		}
		
		/**
		 * Tests if a given node has the left child.
		 * If pos is has right child return true; false otherwise.
		 * @param pos the position to check.
		 * @return true if pos has right child, false otherwise.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */
		public boolean hasRight(Position<E> pos) throws InvalidPositionException {
			BTNode<E> node = checkPosition(pos);
			if (node.getRight() == null)
				return false;
			return true;
		}
		
		/**
		 * Returns the left child of a given Position(if exists).
		 * @param pos the parent's position.
		 * @return the left child of pos.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */
		public BTPosition<E> left(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
			BTNode<E> node = checkPosition(pos);
			if(node.getLeft() == null)
				throw new BoundaryViolationException();
			return node.getLeft();
		}
		
		/**
		 * Returns the right child of a given Position(if exists).
		 * @param pos the parent's position.
		 * @return the right child of pos.
		 * @throws InvalidPositionException if pos is a position not valid.
		 */
		public BTPosition<E> right(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
			BTNode<E> node = checkPosition(pos);
			if(node.getRight() == null)
				throw new BoundaryViolationException();
			return node.getRight();
		}

		
		/**
		 * Auxiliary Method - check if position is valid for this
		 * LinkedBinaryTree. 
		 * @throws InvalidPositionException if position is not valid.
		 */
		private BTNode<E> checkPosition(Position<E> pos) {
			if(pos == null)
				throw new InvalidPositionException();
			try{
				return (BTNode<E>)pos;
			}
			catch(ClassCastException e){
				throw new InvalidPositionException();
			}
		}

		/**
		 * Tests if a position p is a leaf. If p is a leaf return true;
		 * false otherwise.
		 * @param p the position to check.
		 * @return true if p is a leaf, false otherwise.
		 * @throws InvalidPositionException if p is a position not valid.
		 * 
		 */
		public boolean isExternal(Position<E> p) throws InvalidPositionException {
			BTNode<E> node = checkPosition(p);
			if(node.getLeft() == null && node.getRight() == null)
				return true;
			return false;
		}
		
		/**
		 * Tests if a position p is a leaf. If p is a leaf return false;
		 * true otherwise.
		 * @param p the position to check.
		 * @return false if p is a leaf, true otherwise.
		 * @throws InvalidPositionException if p is a position not valid.
		 * 
		 */
		public boolean isInternal(Position<E> p) throws InvalidPositionException {
			return (!isExternal(p));
		}


		/**
		 * Tests if a position p is the root of this LinkedTree.
		 * If p is the root return true; false otherwise.
		 * @param p the position to check.
		 * @return true if p is the root, false otherwise.
		 * @throws InvalidPositionException if p is a position not valid.
		 */
		public boolean isRoot(Position<E> p) throws InvalidPositionException {
			BTNode<E> node = checkPosition(p);
			if (node == root)
				return true;
			return false;
		}
		
		/**
		 * Returns the root of this LinkedTree.
		 * @return the root of this LinkedTree
		 * @throws EmptyTreeException if this LinkedTree is empty.
		 */
		public Position<E> root() throws EmptyTreeException {
			if(root == null)
				throw new EmptyTreeException();
			return root;
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
			BTNode<E> node = checkPosition(p);
			if(node == root)
				throw new BoundaryViolationException();
			return node.getParent();
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
		 * Sets the element currently stored in the position taken in input(p) 
		 * with another element el(taken in input). 
		 * @param p the position of the element to set.
		 * @param el the new element to store.
		 * @return the element stored(before modification)in p.
		 * @throws InvalidPositionException if p is a position not valid.
		 */
		public E replace(Position<E> p, E el) throws InvalidPositionException {
			BTNode<E> node = checkPosition(p);
			E tmp = node.element();
			node.setElement(el);
			return tmp;
		}

		/**
		 * Tests if this LinkedBinaryTree is empty. 
		 * @return true if and only if this LinkedBinaryTree contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return (size == 0);
		}
		
		/**
		 * Returns the number of elements stored in the LinkedBinaryTree.
		 * @return the number of elements stored in the LinkedBinaryTree.
		 */
		public int size() {
			return size;
		}
		

		/**
		 * Returns an iterator of the elements in this LinkedBinaryTree
		 * (in proper sequence).
		 * @return an elements' iterator
		 */
		@SuppressWarnings("unchecked")
		public Iterator iterator() {
			Iterable<Position<E>> positions = positions();
			PositionList<E> el = new NodePositionList<E>();
			for(Position<E> pos: positions)
				el.addLast(pos.element());
			return el.iterator();
		}

		
		/**
		 * Returns an iterable collection of the positions in this LinkedBinaryTree.
		 * @return an iterable collection of the positions in this LinkedBinaryTree.
		 */
		public Iterable<Position<E>> positions() {
			PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
			if(size != 0)
				preorderPositions(root(), positions);
			return positions;
		}
		
		private void preorderPositions(Position<E> v, PositionList<Position<E>> pos) {
			pos.addLast(v);
			if(hasLeft(v)) preorderPositions(left(v), pos);
			if(hasRight(v)) preorderPositions(right(v), pos);
		
		}
		
		
	
		/**
		 * Inserts an element as a left child of a given Position.
		 * @param v the parent's Position.
		 * @param e the element to add as left child.
		 * @return the position of the added element.
		 * @throws InvalidPositionException if v is a position not valid.
		 */
		@SuppressWarnings("unchecked")
		public Position<E> insertLeft(Position<E> v, E e){
		BTNode node = checkPosition(v);
			if(hasLeft(node))
				throw new InvalidPositionException();
			BTPosition<E> leaf = createNode(e, node, null, null);
			node.setLeft(leaf);
			size++;
			return leaf;
		}
	
		/**
		 * Inserts an element as a right child of a given Position.
		 * @param v the parent's Position.
		 * @param e the element to add as right child.
		 * @return the position of the added element.
		 * @throws InvalidPositionException if v is a position not valid.
		 */
		@SuppressWarnings("unchecked")
		public Position<E> insertRight(Position<E> v, E e){
			BTNode node = checkPosition(v);
			if(hasRight(node))
				throw new InvalidPositionException();
			BTPosition<E> leaf = createNode(e, node, null, null);
			node.setRight(leaf);
			size++;
			return leaf;
		}
		/**
		 * Inserts an element as root.
		 * @param el the element to add as root.
		 * @return the position of the added element.
		 */
		public Position<E> addRoot(E el){
			if(!isEmpty())
				throw new NonEmptyTreeException();
			size = 1;
			root = createNode(el, null, null, null);
			return root;
		}
		
		/**
		 * Auxiliary Method - Create a node.
		 */
		private BTPosition<E> createNode(E e, BTNode<E> parent, BTNode<E> left, BTNode<E> right) {
			BTNode<E> node = new BTNode<E>(e, parent, left, right );
			return node;
		}
		
		
	//Method used in BinarySearchTree
		
		/**
		 * Adds left child and right child to a given Position.
		 * @param pos the Position to expand.
		 * @param l the left child
		 * @param r the right child
		 * @throws InvalidPositionException if p is a position not valid
		 * and if pos is not a leaf.
		 */
		protected void expandExternal(Position<E> pos, E l, E r) {
			if (!isExternal(pos))
			  throw new InvalidPositionException();
			insertLeft(pos, l);
			insertRight(pos, r);
		}
		
		/**
		 * Returns the sibling of a given node.
		 * @param node the node.
		 * @return the sibling of a given node(if exists).
		 * @throws InvalidPositionException if node is a position not valid.
		 */
		protected Position<E> sibling(Position<E> node){
			if(isRoot(node))
				return null;
			else {
				Position<E> parent = parent(node);
				BTPosition<E> sibling = left(parent);
				if(sibling == node)
				  return right(parent);
				return sibling;
			}
		}

		@Override
		public String toString(){
			  String s;
			  s = "[";
			  if (size() > 0) s+= root.element();
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
		
		
		/*
		 * In alcuni casi non funzia
		 */
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			LinkedTree<E> other = (LinkedTree<E>)otherObject;
			if(size() != other.size())
				return false;
			Iterator<Position<E>> itPos = positions().iterator();
			Iterator<Position<E>> itPos2 = other.positions().iterator();
			while(itPos.hasNext()){
				if(!itPos.next().element().equals(itPos2.next().element()))
					return false;
			}
			return true;
		}
}
