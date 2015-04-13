package indexList;

import exception.IndexOutOfBoundsException;

/**
 * The ArrayIndexList class implements IndexList interface with an array. 
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

public class ArrayIndexList<E> implements IndexList<E> {
	//Instance Variables
		private E[] array;
		private int size;
		public static int CAP = 256;
		private int cap;
	
	
	//Constructors
		/**
		 * Constructs an empty list with an initial capacity of 256. 
		 */
		public ArrayIndexList(){
			this(CAP);
		}
		
		/**
		 * Constructs an empty list with an initial capacity of cap. 
		 * @param cap the initial capacity.
		 */
		
		@SuppressWarnings("unchecked")
		public ArrayIndexList(int cap){
			this.cap = cap;
			array = (E[]) new Object[cap];
		}
	
	//Methods
		
		/**
		 * Inserts the specified element at the specified position in this list. 
		 * Shifts the element currently at that position (if any) 
		 * and any subsequent elements to the right (adds one to their indices). 
		 * When the ArrayIndexList become full this ArrayIndexList is changed 
		 * with another ArrayIndexList(with double-size)containing 
		 * all old elements and the newest one.
		 * @param i index at which the specified element is to be inserted.
		 * @param el element to be inserted. 
		 * @throws IndexOutOfBoundsException if the index given in input is not valid.
		 * 
		 */
		
		@SuppressWarnings("unchecked")
		public void add(int i, E el) {
			checkIndex(i, size+1);
			if(size == cap){
				E[] tmp = (E[]) new Object[cap*2];
				for(int j = 0; j<size; j++)
					tmp[j] = array[j];
				array = tmp;
				cap *=2;
			}
			for(int j = size-1 ; j>=i; j--) 
				array[j+1] = array[j];
			array[i] = el;
			size++;
			
		}

		/**
		 * Checks if an index is valid for this ArrayIndexList.
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
		 * Returns the element stored at the specified position.
		 * @param i index of element to return. 
		 * @throws IndexOutOfBoundsException if the index is not valid.
		 * 
		 */
		public E get(int i)  {
			checkIndex(i, size);
			return array[i];
		}
		
		/**
		 * Removes the element at the specified position in this ArrayIndexList. 
		 * Shifts any subsequent elements to the left (subtracts one 
		 * from their indices). 
		 * @param i the index of the element to removed. 
		 * @return the element that was removed from the ArrayIndexList. 
		 * @throws IndexOutOfBoundsException if the index given in input is not valid.
		 * 
		 */
		public E remove(int i)  {
			checkIndex(i, size);
			E tmp = array[i];
			for(; i <size()-1; i++)
				array[i] = array[i+1];
			//array[i+1] = null;
			size--;
			return tmp;
		}
		
		/**
		 * Replaces the element at the specified position in this ArrayIndexList
		 * with the specified element.
		 * @param i the index of element to replace.
		 * @param el element to be stored at the specified position. 
		 * @return the element previously at the specified position. 
		 * @throws IndexOutOfBoundsException if the index given in input is not valid.
		 * 
		 */
		public E set(int i, E el) {
			checkIndex(i, size);
			E tmp = array[i];
			array[i] = el;
			return tmp;
		}
		
		/**
		 * Tests if this ArrayIndexList is empty. 
		 * @return true if and only if this ArrayIndexList contains no items; 
		 * false otherwise.
		 */
		public boolean isEmpty() {
			return (size == 0);
		}
		
		/**
		 * Returns the number of elements stored in the ArrayIndexList.
		 * @return the number of elements stored in the ArrayIndexList.
		 */
		
		public int size() {
			return size;
		}

		@Override
		public String toString() {
			  String s;
			  s ="[";
			  if (size() > 0) s+= array[0];
			  if (size() > 1)
			   for (int i=1; i<= size()-1; i++)
			    s += " - " + array[i];
			  return s+"]";
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			ArrayIndexList<E> other = (ArrayIndexList<E>)otherObject;
			if(size() != other.size())
				return false;
			for(int i = 0; i<size(); i++)
				if(!array[i].equals(other.array[i]))
					return false;
			return true;
		}
}
