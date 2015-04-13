package sequence;

import iterator.LinkedIterator;
import java.util.Iterator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import position.ArrayPosition;
import position.Position;
import exception.BoundaryViolationException;
import exception.EmptySequenceException;
import exception.IndexOutOfBoundsException;
import position.InvalidPositionException;


/**
 * 
 * The ArraySequence implements Sequence interface with an array 
 * of ArrayPosition. 
 * When an ArraySequence is first created, it contains no items. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class ArraySequence<E> implements Sequence<E>{

	//Instance Variables
		private ArrayPosition<E>[] positions;
		private static final int CAP = 16;
		private int capacity;
		private int size = 0;
		
	//Constructors
		/**
		 * Constructs an empty ArraySequence with an initial capacity of 16. 
		 */
		@SuppressWarnings("unchecked")
		public ArraySequence(){
			capacity = CAP;
			positions = new ArrayPosition[capacity];
		}
		
		/**
		 * Constructs an empty list with an initial capacity of cap.
		 * @param cap the initial capacity of the sequence. 
		 */
		@SuppressWarnings("unchecked")
		public ArraySequence(int cap){
			capacity = cap;
			positions = new ArrayPosition[capacity];
		}
		
	//Methods
		
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
			checkIndex(index, size);
			return positions[index];
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
			ArrayPosition<E> pos = checkPosition(position);
			return pos.getIndex();
		}
		
		/**
		 * Checks if a position is valid for this Sequence.
		 * (if index out of range (i < 0 || i >= n).
		 * @param i the index to check.
		 * @param n the maximum index.
		 * @throws InvalidPositionException if position is not valid
		 * for this Sequence.
		 * 
		 */
		private ArrayPosition<E> checkPosition(Position<E> position){
			int i = 0;
			ArrayPosition<E> pos = null;
			try{
				pos = (ArrayPosition<E>) position;
				for(i = 0; i<size; i++){
					if(positions[i] == pos)
						break;
				}
			}
			catch(ClassCastException e){
				throw new InvalidPositionException();
			}
			if(i >= size)
				throw new InvalidPositionException();
			else
				return pos;
		}

		/**
		 * Returns the first Position in the Sequence.
		 * @return the first Position in the Sequence.
		 * @throws EmptySequenceException if Sequence is empty.
		 */
		public Position<E> first(){
			if(isEmpty())
				throw new EmptySequenceException();
			ArrayPosition <E> toReturn = positions[0];
			return toReturn;
		}

		/**
		 * Returns the last Position in the Sequence.
		 * @return the last Position in the Sequence.
		 * @throws EmptySequenceException if Sequence is empty.
		 */
		public Position<E> last() throws EmptySequenceException {
				if(isEmpty())
					throw new EmptySequenceException();
				ArrayPosition <E> toReturn = positions[size-1];
				return toReturn;
		}

		/**
		 * Sets the element currently stored in the position taken in input(pos) 
		 * with another element el(taken in input). 
		 * @param pos the position of the element to set.
		 * @param el the new element to store.
		 * @return the element stored(before modification)in pos.
		 * @throws InvalidPositionException if the position is not valid.
		 */
		
		public E set(Position<E> pos, E el) throws InvalidPositionException {
			return set(indexOf(pos),el);
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
		public E set(int i, E e) throws IndexOutOfBoundsException{
			checkIndex(i, size+1);
			E tmp = positions[i].element();
			positions[i].setElement(e);
			return tmp;
		}

		
		/**
		 * Inserts the specified element at the specified position in this list. 
		 * Shifts the element currently at that position (if any) 
		 * and any subsequent elements to the right (adds one to their indices). 
		 * When the Sequence become full this Sequence is changed 
		 * with another Sequence(with double-size)containing 
		 * all old elements and the newest one.
		 * @param index index at which the specified element is to be inserted.
		 * @param e element to be inserted. 
		 * @throws IndexOutOfBoundsException if the index given in input is not valid.
		 * 
		 */
		@SuppressWarnings("unchecked")
		public void add(int index, E e) {
			checkIndex(index, size+1);
			if(size == capacity){
				ArrayPosition<E>[] tmp = new ArrayPosition[capacity*2];
				capacity*=2;
				for(int i= 0; i<size+1;i++){
					if(i == index){
						ArrayPosition<E> el = new ArrayPosition<E>(index, e);
						tmp[i] = el;
						i++;
					}
					tmp[i] = positions[i];
				}
				positions = tmp;
			}
			for(int i = size-1; i>=index; i--){
				positions[i+1] = positions[i];
				positions[i+1].setIndex(i+1);
			}
			positions[index] = new ArrayPosition<E>(index,e);
			size++;	
		}
		
		/**
		 * Returns the element stored at the specified position.
		 * @param i index of element to return. 
		 * @throws IndexOutOfBoundsException if the index is not valid.
		 * 
		 */
		public E get(int i){
			checkIndex(i, size);
			return positions[i].element();
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
		public E remove(int i)  {
			if(isEmpty())
				throw new EmptySequenceException();
			checkIndex(i, size);
			E tmp = positions[i].element();
			for(int j = i; j<size-1;j++){
				positions[j].setElement(positions[j+1].element());
				//Modificato
				positions[j].setIndex(j);
			}
			positions[size] = null;
			size--;
			return tmp;
		}


		/**
		 * Retrieves, but does not remove, the first element of this Sequence.
		 * @return the head of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty.
		 */
		public E getFirst() throws EmptySequenceException{
			if(isEmpty())
				throw new EmptySequenceException();
			return positions[0].element();
		}
		
		/**
		 * Retrieves, but does not remove, the last element of this Sequence.
		 * @return the tail of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty
		 */
		public E getLast() throws EmptySequenceException {
			if(isEmpty())
				throw new EmptySequenceException();
			return positions[size-1].element();
		}
		
		/**
		 * Retrieves and removes the first element of this Sequence.
		 * @return the head of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty.
		 */
		public E removeFirst()  throws EmptySequenceException{
			return remove(0);
		}
		
		/**
		 * Retrieves and removes the last element of this Sequence.
		 * @return the tail of this Sequence.
		 * @throws EmptySequenceException where Sequence is empty.
		 */
		public E removeLast() throws EmptySequenceException{
			return remove(size-1);
		}

		
		/**
		 * Inserts the specified element at the position after the specified 
		 * position in this Sequence. 
		 * @param pos position after which the specified element is to be inserted.
		 * @param el element to be inserted. 
		 * @return the position of the element inserted.
		 * @throws InvalidPositionException if the index given in input is not 
		 * valid.
		 * 
		 */
		public Position<E> addAfter(Position<E> pos, E el) throws InvalidPositionException {
			int index = indexOf(pos);
			this.add(index+1,el);
			return positions[index+1];
		}

		/**
		 * Inserts the specified element at the position before the specified 
		 * position in this Sequence. 
		 * @param pos position before which the specified element is to be inserted.
		 * @param el element to be inserted. 
		 * @return the position of the element inserted.
		 * @throws InvalidPositionException if the index given in input is not 
		 * valid.
		 * 
		 */
		public Position<E> addBefore(Position<E> pos, E el) {
			int index = indexOf(pos);
			this.add(index, el);
			return positions[index];
		}
		
		/**
		 * Inserts the specified element at the front of this Sequence
		 * @param el the item to be inserted at the front of this Sequence.
		 */
		public void addFirst(E el) {
			add(0,el);
		}

		/**
		 * Inserts the specified element at the end of this Sequence
		 * @param el the item to be inserted at the end of this Sequence.
		 */
		public void addLast(E el) {
			//add(size+1,el);	
			add(size,el);	
		}

		/**
		 * Tests if this Sequence is empty. 
		 * @return true if and only if this Sequence contains no items; false otherwise.
		 */
		public boolean isEmpty() {
			return (size<=0);
		}

		/**
		 * Returns position after the specifiedPosition taken in input (pos).  
		 * @param pos the position before the Position to return. (? xD)
		 * @return the position after pos.
		 * @throws InvalidPositionException if the position is not valid.
		 * @throws BoundaryViolationException when we try to accede to a 
		 * position out of this Sequence.
		 * 
		 */
		public Position<E> next(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
			int index = indexOf(pos);
			if(index == size)
				throw new BoundaryViolationException();
			return positions[index+1];
		}
		
		/**
		 * Returns position before the specified position taken in input (pos).  
		 * @param pos the position after the position to return. (? xD)
		 * @return the position before pos.
		 * @throws InvalidPositionException when Position is not valid.
		 * @throws BoundaryViolationException when we try to accede to a 
		 * position out of this Sequence.
		 * 
		 */
		public Position<E> prev(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
			int index = indexOf(pos);
			if(index == 0)
				throw new BoundaryViolationException();
			return positions[index-1];
		}
		
		/**
		 * Removes the position taken in input(pos) from this Sequence
		 * and returns the element stored(before removal)in this Position.
		 * @param pos the position to remove.
		 * @return the element stored(before removal) in pos.
		 * @throws InvalidPositionException when pos is not a valid position.
		 */
		public E remove(Position<E> pos) throws InvalidPositionException {
			return remove(indexOf(pos));
		}

		/**
		 * Returns the number of elements stored in the Sequence.
		 * @return the number of elements stored in the Sequence.
		 */
		public int size() {
			return size;
		}

		
		/**
		 * Returns an iterator of the elements in this Sequence
		 * (in proper sequence).
		 * @return an elements' iterator
		 */
		@SuppressWarnings("unchecked")
		public Iterator<E> iterator() {
			E[] el = (E[]) new Object[size];
			for(int i=0; i<=size; i++)
				el[i] = positions[i].element();
			return new LinkedIterator<E>(el);
		}
		/**
		 * Returns an iterable collection of the positions in this Sequence.
		 * @return an iterable collection of the positions in this Sequence.
		 */
		public Iterable<Position<E>> positions() {
			PositionList <Position<E>> toReturn = new NodePositionList<Position<E>>();
			Position<E> current = first();
			if(!isEmpty()){
				for(int i = 0; i<size;i++){
					toReturn.addLast(current);
					current = next(current);
				}
				toReturn.addLast(last());
			}
			return toReturn;
		}
		
		@Override
		public String toString() {
			  String s;
			  s = "[";
			  if (size() > 0) s+= positions[0];
			  if (size() > 1)
			   for (int i=1; i<= size()-1; i++)
			    s += " - " + positions[i];
			  return s+"]";
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			ArraySequence<E> other = (ArraySequence<E>)otherObject;
			if(size() != other.size())
				return false;
			for(int i = 0; i<size(); i++)
				if(!positions[i].equals(other.positions[i]))
					return false;
			return true;
		}

		

	
}
