package nodeList;

import iterator.ElementIterator;
import java.util.Iterator;
import exception.BoundaryViolationException;
import exception.EmptyListException;
import position.DNode;
import position.InvalidPositionException;
import position.Position;



/**
 * The NodePositionList class implements PositionList interface with an array. 
 * When an ArrayIndexList is first created, it contains no items. 
 * The size, isEmpty, get, set operations run in constant time while the 
 * add operation runs in amortized constant time, that is, adding n 
 * elements requires O(n) time. The remove operations runs
 * in linear time (roughly speaking).
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class NodePositionList<E> implements PositionList<E> {
	
	//Instance variables
		protected DNode<E> header, trailer;
		protected int num;
	//Constructor
		/**
		 * Constructs an empty NodePositionList. 
		 */
		public NodePositionList(){
			num = 0;
			header = new DNode<E>();
			trailer = new DNode<E>(null, header, null);
			header.setNext(trailer);
		}
	//Methods
		
		/**
		 * Inserts the specified element at the position after the specified 
		 * position in this NodePositionList. 
		 * @param pos position after which the specified element is to be inserted.
		 * @param el element to be inserted. 
		 * @return the position of the element inserted.
		 * @throws InvalidPositionException if the index given in input is not 
		 * valid.
		 * 
		 */
		
		public Position<E> addAfter(Position<E> pos, E el) throws InvalidPositionException {
			DNode <E> node = checkPosition(pos);
			num++;
			DNode<E> newNode = new DNode<E>(el, node, node.getNext());
			node.getNext().setPrev(newNode);
			node.setNext(newNode);
			return newNode;
		}
		
		/**
		 * Inserts the specified element at the position before the specified 
		 * position in this NodePositionList. 
		 * @param pos position before which the specified element is to be inserted.
		 * @param el element to be inserted. 
		 * @return the position of the element inserted.
		 * @throws InvalidPositionException if the index given in input is not 
		 * valid.
		 * 
		 */
		public Position<E> addBefore(Position<E> pos, E el) throws InvalidPositionException {
			DNode <E> node = checkPosition(pos);
			num++;
			DNode<E> newNode = new DNode<E>(el, node.getPrev(), node);
			node.getPrev().setNext(newNode);
			node.setPrev(newNode);
			return newNode;
		}

		/**
		 * Inserts the specified element at the first position in 
		 * this NodePositionList. 
		 * @param el element to be inserted.
		 * 
		 */
	//public Position<E> addFirst(E el) {
		public void addFirst(E el) {
			DNode<E> node = new DNode<E>(el, header, header.getNext());
			header.getNext().setPrev(node);
			header.setNext(node);
			num++;
			//return node;
		}

		/**
		 * Inserts the specified element at the end of this NodePositionList. 
		 * @param el element to be inserted. 
		 * 
		 */
	//public Position<E> addLast(E el) {
		public void addLast(E el) {
			DNode<E> node = new DNode<E>(el, trailer.getPrev(), trailer);
			trailer.getPrev().setNext(node);
			trailer.setPrev(node);
			num++;
			//return null;
		}

		/**
		 * Returns the position at the begin of this NodePositionList.  
		 * @return the position at the begin of this NodePositionList.
		 * @throws EmptyListException where NodePositionList is empty.
		 * 
		 */
		
		public Position<E> first() throws EmptyListException {
			if(isEmpty())
				throw new EmptyListException();
			return header.getNext();
		}
		
		/**
		 * Returns the position at the end of this NodePositionList.  
		 * @return the position at the end of this NodePositionList.
		 * @throws EmptyListException where NodePositionList is empty.
		 * 
		 */
		
		public Position<E> last() {
			if(isEmpty())
				throw new EmptyListException();
			return trailer.getPrev();
		}
		
		/**
		 * Returns position after the specified position taken in input (pos).  
		 * @param pos the position before the position to return. (? xD)
		 * @return the position after pos.
		 * @throws InvalidPositionException when Position is not valid.
		 * @throws BoundaryViolationException when we try to accede to a 
		 * position out of this NodePositionList.
		 * 
		 */
		
		public Position<E> next(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
			DNode<E> node = checkPosition(pos);
			DNode<E> next = node.getNext();
			if(next == trailer)
				throw new BoundaryViolationException();
			return next;
		}
		
		/**
		 * Returns position before the specified position taken in input (pos).  
		 * @param pos the position after the position to return. (? xD)
		 * @return the position before pos.
		 * @throws InvalidPositionException when Position is not valid.
		 * @throws BoundaryViolationException when we try to accede to a 
		 * position out of this NodePositionList.
		 * 
		 */
		
 		public Position<E> prev(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
			DNode<E> node = checkPosition(pos);
			DNode<E> prev = node.getPrev();
			if(prev == header)
				throw new BoundaryViolationException();
			return prev;
		}

		/**
		 * Removes the position taken in input(pos) from this NodePositionList
		 * and returns the element stored(before removal)in this position.
		 * @param pos the position to remove.
		 * @return the element stored(before removal) in pos.
		 * @throws InvalidPositionException when pos is not a valid position.
		 */

		public E remove(Position<E> pos) throws InvalidPositionException {
			DNode<E> node = checkPosition(pos);
			E tmp = node.element();
			node.getPrev().setNext(node.getNext());
			node.getNext().setPrev(node.getPrev());
			node = null;
			num--;
			return tmp;
		}
		
		/**
		 * Sets the element currently stored in the position taken in input(pos) 
		 * with another element el(taken in input). 
		 * @param pos the position of the element to set.
		 * @param el the new element to store.
		 * @return the element stored(before modification)in pos.
		 */
		
		public E set(Position<E> pos, E el) {
			DNode<E> node = checkPosition(pos);
			E tmp = node.element();
			node.setElement(el);
			return tmp;
		}
		
		/**
		 * Returns an iterator of the elements in this NodePositionList 
		 * (in proper sequence).
		 * @return an elements' iterator
		 */
		
		public Iterator<E> iterator() {
			return new ElementIterator<E>(this);
		}
		
		/**
		 * Returns an iterable collection of the positions in this NodePositionList.
		 * @return an iterable collection of the positions in this NodePositionList.
		 */
		
		public Iterable<Position<E>> positions() {
			PositionList <Position<E>> toReturn = new NodePositionList<Position<E>>();
			Position<E> current = first();
			if(!isEmpty()){
				for(int i = 0; i<size()-1;i++){
					toReturn.addLast(current);
					current = next(current);
				}
				toReturn.addLast(last());
			}
			return toReturn;
		}

		/** Checks if position is valid for this list and converts it to DNode 
		 * @param position the position to check  
		 * @return the position casted to DNode
		 * @throws InvalidPositionException where the position is not valid
		 */
		
		private DNode<E> checkPosition(Position <E> position){
			if(position == null)
				throw new InvalidPositionException("Null position");
			if(position == header || position == trailer)
				throw new InvalidPositionException("Header and trailer are not valid position");
			try{
				DNode<E> tmp = (DNode<E>) position;
				if(tmp.getPrev() == null || tmp.getNext() == null)
					throw new InvalidPositionException("Position of another PositionList");
				return tmp;
			}
			catch(ClassCastException e){
				throw new InvalidPositionException("Wrong type");
			}
		}
		
		/**
		 * Tests if this NodePositionList is empty. 
		 * @return true if and only if this NodePositionList contains no items; 
		 * false otherwise.
		 */
		public boolean isEmpty() {
			return (header.getNext() == trailer);
		}
		/**
		 * Returns the number of elements stored in this NodePositionList.
		 * @return the number of elements stored in this NodePositionList.
		 */
		public int size() {
			return num;
		}
		
		@Override
		public String toString() {
			  String s;
			  s = "[";
			  if (size() > 0) s+= header.getNext().element();
			  if (size() > 1){
				  DNode<E> tmp = header.getNext().getNext();
				  while(tmp != trailer){
					  s += " - " + tmp.element();
					  tmp = tmp.getNext();
				  }
			  }
			  return s+"]";
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			NodePositionList<E> other = (NodePositionList<E>)otherObject;
			if(size() != other.size())
				return false;
			 DNode<E> tmp1 = header.getNext();
			 DNode<E> tmp2= other.header.getNext(); 
			 while(tmp1 != trailer){
				  if(!tmp1.element().equals(tmp2.element()))
					return false;
				  tmp1 = tmp1.getNext();
				  tmp2 = tmp2.getNext();
			}
			return true;
		}
}
