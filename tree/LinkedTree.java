package tree;

import java.util.Iterator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import exception.BoundaryViolationException;
import exception.EmptyTreeException;
import exception.NonEmptyTreeException;
import exception.UndelatableNodeException;
import position.InvalidPositionException;
import position.Position;
import position.TreePosition;


/**
 * The LinkedTree implements Tree interface.
 * When a LinkedTree is first created, it contains no items. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class LinkedTree<E> implements  Tree<E>{
	//Instance Variables
		protected TreePosition<E> root;
		protected int size;
	
	//Constructor
		/**
		 * Constructs an empty LinkedTree.
		 */
		public LinkedTree(){
			root = null;
			size = 0;
		}
	
	//Methods
		
		/**
		 * Checks if a position is valid for this Tree
		 * @param pos the position to check.
		 * @throws InvalidPositionException if position is not valid
		 * for this Tree.
		 * 
		 */
		private TreePosition<E> checkPosition(Position<E> pos){
			if(pos == null)
				throw new InvalidPositionException("Null position");
			try{
				TreePosition<E> position = (TreePosition<E>) pos;
				return position;
			}
			catch(ClassCastException e){
				throw new InvalidPositionException("Wrong type");
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
		public boolean isExternal(Position<E> p){
			TreePosition<E> pos = checkPosition(p);
			return (pos.getChildren() == null || pos.getChildren().isEmpty());
		}

		/**
		 * Tests if a position p is a leaf. If p is a leaf return false;
		 * true otherwise.
		 * @param p the position to check.
		 * @return false if p is a leaf, true otherwise.
		 * @throws InvalidPositionException if p is a position not valid.
		 * 
		 */
		public boolean isInternal(Position<E> p) {
			return (!isExternal(p));
		}

		/**
		 * Tests if a position p is the root of this LinkedTree.
		 * If p is the root return true; false otherwise.
		 * @param p the position to check.
		 * @return true if p is the root, false otherwise.
		 * @throws InvalidPositionException if p is a position not valid.
		 */
		public boolean isRoot(Position<E> p)  {
			TreePosition<E> pos = checkPosition(p);
			if(pos == root)
				return true;
			return false;
		}
		
		/**
		 * Returns the root of this LinkedTree.
		 * @return the root of this LinkedTree
		 * @throws EmptyTreeException if this LinkedTree is empty.
		 */
		public Position<E> root() {
			if(isEmpty())
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
		public Position<E> parent(Position<E> p)  {
			TreePosition<E> pos = checkPosition(p);
			if(isRoot(p))
				throw new  BoundaryViolationException();
			return pos.getParent();
		}
		
		
		/**
		 * Returns an iterable positions' collection of the children
		 * of a given position p.
		 * @param p the parent position.
		 * @return an iterable positions' collection of the children of p
		 * @throws InvalidPositionException if p is a leaf.
		 */
		public Iterable<Position<E>> children(Position<E> p) {
			TreePosition<E> pos = checkPosition(p);
			if(isExternal(p))
				throw new InvalidPositionException("Is a leaf");
			return pos.getChildren();
		}
		
		/**
		 * Sets the element currently stored in the position taken in input(p) 
		 * with another element el(taken in input). 
		 * @param p the position of the element to set.
		 * @param el the new element to store.
		 * @return the element stored(before modification)in p.
		 * @throws InvalidPositionException if p is a position not valid.
		 */
		public E replace(Position<E> p, E el){
			TreePosition<E> pos = checkPosition(p);
			E tmp = pos.element();
			pos.setElement(el);
			return tmp;
		}

		/**
		 * Tests if this LinkedTree is empty. 
		 * @return true if and only if this LinkedTree contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return (root == null);
		}
		
		/**
		 * Returns the number of elements stored in the LinkedTree.
		 * @return the number of elements stored in the LinkedTree.
		 */
		public int size() {
			return size;
		}

		/**
		 * Returns an iterator of the elements in this LinkedTree
		 * (in proper sequence).
		 * @return an elements' iterator
		 */
		public Iterator<E> iterator() {
			Iterable<Position<E>> positions = positions();
			PositionList<E> el = new NodePositionList<E>();
			for(Position<E> pos: positions)
				el.addLast(pos.element());
			return el.iterator();
		}
	
		private void preorderPositions(Position<E> v, PositionList<Position<E>> pos){
			pos.addLast(v);
			if(isExternal(v))
				return;
			Iterator<Position<E>> itc = children(v).iterator();
			while(itc.hasNext())
				preorderPositions(itc.next(), pos);
		}
		
		/**
		 * Returns an iterable collection of the positions in this LinkedTree.
		 * @return an iterable collection of the positions in this LinkedTree.
		 */
		public Iterable<Position<E>> positions(){
			PositionList<Position<E>> pos = new NodePositionList<Position<E>>();
			if(size != 0)
				preorderPositions(root(), pos);
			return pos;
		}
	
	//Additional methods
		
		/**
		 * Adds a root in the LinkedTree.
		 * @param el the element to insert a root.
		 * @return the Position of the element inserted.
		 * @throws NonEmptyTreeException if this LinkedTree is not empty. 
		 */
		public Position<E> addRoot(E el) {
			if(!isEmpty())
				throw new NonEmptyTreeException();
			size = 1;
			root = createNode(el,null,null);
			return root; 	
		}

		private TreePosition<E> createNode(E el, TreePosition<E> parent, PositionList<Position<E>> children) {
			return ((TreePosition<E>) new position.TreeNode<E>(el, parent, children));
		}
		
		/**
		 * Adds a child of a certain node in this LinkedTree.
		 * @param el The element to add as a child.
		 * @param node the parent's Position of the new child.
		 * @return The position of the added child.
		 * @throws InvalidPositionException if the parent's position 
		 * is not a valid Position for this LinkedTree.
		 */
		public Position<E> addChild(E el, Position<E> node){
			TreePosition<E> parent = checkPosition(node);
			Position<E> newChild = createNode(el, (TreePosition<E>) node,null);
			if(isExternal(node)){
				PositionList<Position<E>> children = new NodePositionList<Position<E>>();
				children.addLast(newChild);
				parent.setChildren(children);		
			}
			else
				((PositionList<Position<E>>) children(parent)).addLast(newChild);
			size++;
			return newChild;
		}
	
		/**
		 * Removes the root from this LinkedTree.
		 * @return the element stored(before removal)in the root
		 * @throws EmptyTreeException if invoked on an empty LinkedTree.
		 * @throws UndelatableNodeException if invoked on a LinkedTree that 
		 * does not contain only the root.
		 */
		public E removeRoot(){
			if(isEmpty())
				throw  new EmptyTreeException();
			E tmp = null;
			if(size == 1){
				tmp = root.element();
				root = null;
				size = 0;
				return tmp;
			}
			else
				throw new UndelatableNodeException();	
		}
	/**
	 * Removes the first leaf of a given node.
	 * @param node The parent's position.
	 * @return the element that was stored in the removed child.
	 * @throws InvalidPositionException if node is not a valid Position
	 * for this LinkedTree.
	 * @throws InvalidPositionException if node is a leaf.
	 * @throws UndelatableNodeException if the child of node is not a leaf.
	 */
		public E removeExternalChild(Position<E> node) {
			TreePosition<E> n = checkPosition(node);
			if(isExternal(node))
				throw new InvalidPositionException("Is a leaf");
			PositionList <Position<E>> childs = n.getChildren();
			if(!isExternal(childs.first().element()))
				throw new UndelatableNodeException();
			size--;
			return (childs.remove(childs.first())).element();
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
