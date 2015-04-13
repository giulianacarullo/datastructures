package sequence;

import nodeList.NodePositionList;
import position.DNode;
import position.InvalidPositionException;
import position.Position;
import exception.EmptyListException;
import exception.EmptySequenceException;
import exception.IndexOutOfBoundsException;


/**
 * The NodeSequence implements Sequence interface with a NodePositionList.
 * When an NodeSequence is first created, it contains no items. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class NodeSequence<E> extends NodePositionList<E> implements Sequence<E> {
	//Instance Variables - NodePositionList
	
	//Constructor - NodePositionList
		/**
		* Constructs an empty NodeSequence. 
		*/
		public NodeSequence(){
			super();
		}
	
	
	//Methods
		/**
	 	* Bridge-Methods. Returns the position of an element in the Sequence relative 
	 	* to a given index.
	 	* @param index the index of an element in the Sequence.
	 	* @return the position of the element in the Sequence 
	 	* at the specified index.
	 	* @throws IndexOutOfBoundsException if the index is not valid.
	 	* 
	 	*/
		public Position<E> atIndex(int index){
			checkIndex(index, size());
			DNode <E> node;
			if(index <=size()/2){
				node =header.getNext();
				for(int i = 0; i<index; i++)
					node = node.getNext();
			}
			else {
				node = trailer.getPrev();
				for(int i=size()-1; i>index; i--)
					node = node.getPrev();
			}
			return node;
		}
	

		/**
		 * Bridge-Methods.Returns the index of an element in the Sequence relative 
		 * to a given position.
		 * @param position the position of an element in the Sequence.
		 * @return the index of the element in the Sequence 
		 * at the specified position.
		 * @throws InvalidPositionException if the position is not valid.
		 * 
		 */
		public int indexOf(Position<E> position) {
			DNode<E> node = header.getNext();
			int i;
			for(i= 0; i<size(); i++){
				if(node == position)
					break;
				node = node.getNext();
			}
			if(i == size())
				throw new InvalidPositionException();
			return i;
		}

		/**
		 * Inserts the specified element at the specified position in this list. 
		 * Shifts the element currently at that position (if any) 
		 * and any subsequent elements to the right (adds one to their indices). 
		 * When the Sequence become full this Sequence is changed 
		 * with another Sequence(with double-size)containing 
		 * all old elements and the newest one.
		 * @param i index at which the specified element is to be inserted.
		 * @param e element to be inserted. 
		 * @throws IndexOutOfBoundsException if the index given in input is not valid.
		 * 
		 */
		public void add(int i, E e) {
			if(i == size()) {
				addLast(e); }
			else{
				checkIndex(i, size());
				addBefore(atIndex(i), e);
			}
		}

		/**
		 * Returns the first Position in the Sequence.
		 * @return the first Position in the Sequence.
		 * @throws EmptySequenceException if Sequence is empty.
		 */
		public Position<E> first(){
			try{
				Position <E> toReturn = super.first();
				return toReturn;
			}
			catch(EmptyListException e){
				throw new EmptySequenceException();
			}
		}
	
		/**
		 * Returns the last Position in the Sequence.
		 * @return the last Position in the Sequence.
		 * @throws EmptySequenceException if Sequence is empty.
		 */
		public Position<E> last(){
			try{
				Position <E> toReturn = super.last();
				return toReturn;
			}
			catch(EmptyListException e){
				throw new EmptySequenceException();
			}
		}

		/**
		 * Returns the element stored at the specified position.
		 * @param i index of element to return. 
		 * @throws IndexOutOfBoundsException if the index is not valid.
		 * 
		 */
		public E get(int i) {
			return atIndex(i).element();
		}	

		/**
		 * Removes the element at the specified position in this Sequence. 
		 * Shifts any subsequent elements to the left (subtracts one 
		 * from their indices). 
		 * @param i the index of the element to removed. 
		 * @return the element that was removed from the Sequence. 
		 * @throws IndexOutOfBoundsException if the index given in input is not valid.
		 * @throws EmptySequenceException if Sequence is empty.
		 */
		public E remove(int i){
			return remove(atIndex(i));
		}

		/**
		 * Sets the element currently stored at the index taken in input(i) 
		 * with another element el(taken in input). 
		 * @param i the index of the element to set.
		 * @param e the new element to store.
		 * @return the element stored(before modification)at index i in the 
		 * Sequence.
		 * @throws InvalidPositionException if the position is not valid.
		 */
		public E set(int i, E e){
			return set(atIndex(i),e);
		}
	
	//Overriding deque's methods
		
		/**
		 * Retrieves, but does not remove, the first element of this Sequence.
		 * @return the head of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty.
		 */
		public E getFirst() throws EmptySequenceException{
			try {
				return get(0);
			}
			catch(Exception e){
				throw new EmptySequenceException();
			}
		}
		
		/**
		 * Retrieves, but does not remove, the last element of this Sequence.
		 * @return the tail of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty
		 */
		public E getLast() throws EmptySequenceException {
			try {
				return get(size()-1);
			}
			catch(Exception e){
				throw new EmptySequenceException();
			}
		}
	
		/**
		 * Retrieves and removes the first element of this Sequence.
		 * @return the head of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty.
		 */
		public E removeFirst() throws EmptySequenceException{
			try{
				return remove(0);
			}
			catch(Exception e){
				throw new EmptySequenceException();
			}
		}

		/**
		 * Retrieves and removes the last element of this Sequence.
		 * @return the tail of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty.
		 */
		public E removeLast() {
			try{
				return remove(size()-1);
			}
			catch(Exception e){
				throw new EmptySequenceException();
			}
		}

		/**
		 * Checks if an index is valid for this Sequence.
		 * (if index out of range (i < 0 || i >= n).
		 * @param i the index to check.
		 * @param n the maximum index.
		 * @throws IndexOutOfBoundsException if the index is not valid.
		 */
		private void checkIndex(int i,int n){
			if(i<0 || i >=n)
				throw new IndexOutOfBoundsException();
		}
		
		@Override
		public String toString() {
			  return super.toString();
		}

		@Override
		public boolean equals(Object otherObject){
			return super.equals(otherObject);
		}
}
