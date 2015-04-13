package set;

import java.util.Comparator;
import java.util.Iterator;

import comparator.DefaultComparator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import partition.ListPartition;
import position.Position;
import merge.IntersectMerge;
import merge.Merge;
import merge.SubtractMerge;
import merge.UnionMerge;

/**
 * The OrderedListSet is a set that further guarantees that its 
 * elements are in ascending element order, sorted according to 
 * the natural ordering of its elements ( {@link DefaultComparator} ),
 * or by a Comparator provided at OrderedListSet creation time. 
 * Several additional operations are provided to take advantage of 
 * the ordering.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the set type.
 */

public class OrderedListSet<E> implements Set<E> {
	//Instance variables
		private Comparator<E> comp;
		private PositionList<E> list;
		private Position<Set<E>> loc; //useful for Partition

	//Constructors
		/**
		 *  Constructs a new, empty set, sorted according to the 
		 *  elements' natural order.
		 */
		public OrderedListSet() {
			this(new DefaultComparator<E>());
		}
	
		/**
		 * Constructs a new, empty set, sorted according to the 
		 * specified comparator.
		 * @param comp the Comparator to use for this OrderedListSet.
		 */
		public OrderedListSet(Comparator<E> comp){
			this.comp = comp;
			list = new NodePositionList<E>();
		}
	
		//Creating singleton
		/**
		 * Constructs a singleton.
		 * @param x the element to insert in this OrderedListSet.
		 * @param comp the Comparator to use for this OrderedListSet.
		 */
		public OrderedListSet(E x, Comparator<E> comp){
			this(comp);
			list.addFirst(x);
		}
	
	//Methods
		/**
		 * Auxiliary method (Used in {@link ListPartition} ) - Removes
		 * the first element in the set and returns it.
		 * @return the removed element.
		 */
		public E remove(){
			return list.remove(list.first());
		}
	
		/**
		 * Returns the set.
		 * @return the set.
		 */
		public PositionList<E> getSet(){
			return list;
		}
	
		/**
		 * Returns the location.
		 * @return the location.
		 */
		public Position<Set<E>> location() {
			return loc;
		}
	
		/**
		 * Sets the location stored in this OrderedListSet.
		 * @param loc the new location.
		 */
		public void setLocation(Position<Set<E>> loc){
			this.loc = loc;
		}
	
	
		/**
		 * Intersects this set with another set.
		 * @param anotherSet the second set to intersect.
		 * @return the result set.
		 */
		public Set<E> intersect(Set<E> anotherSet) {
			PositionList<E> C = new NodePositionList<E>();
			Merge<E> op = new IntersectMerge<E>();
			op.merge(list, ((OrderedListSet<E>)anotherSet).list, comp, C);
			list = C;
			return this;
		}

		/**
		 * Subtracts a given set from this set.
		 * @param anotherSet the set to subtract from this set.
		 * @return the result set.
		 */
		public Set<E> subtract(Set<E> anotherSet) {
			PositionList<E> C = new NodePositionList<E>();
			Merge<E> op = new SubtractMerge<E>();
			op.merge(list, ((OrderedListSet<E>)anotherSet).list, comp, C);
			list = C;
			return this;
		}


		/**
		 * Unions this set with another set.
		 * @param anotherSet the second set to union.
		 * @return the result set.
		 */
		public Set<E> union(Set<E> anotherSet) {
			PositionList<E> C = new NodePositionList<E>();
			Merge<E> op = new UnionMerge<E>();
			op.merge(list, ((OrderedListSet<E>)anotherSet).list, comp, C);
			list = C;
			return this;
		}
	
		//USARE CON CAUTELA! xD
		/**
		 * Inserts the specified element at the end of the list.
		 * Precondition: the elements of this set must be ordered and disjointed.
		 * @param x the element to insert.
		 */
		public void fastInsert(E x){
			list.addLast(x); 
		}
		
		/**
		 * Inserts all elements stored in B in this set. 
		 * Precondition: set disjointed.
		 * @param B 
		 * @return the new set
		 */
		public Set<E> fastUnion(Set<E> B)
		{
			Iterator<E> it = ((OrderedListSet<E>)B).list.iterator();
			while(it.hasNext()) 
				list.addLast(it.next());
			return this;
		}

		/**
		 * Tests if this set is empty. 
		 * Return true if no elements are stored in this set; false otherwise.
		 * @return true if no elements are stored in this set; false otherwise.
		 */
		public boolean isEmpty() {
			return list.isEmpty();
		}
		
		/**
		 * Returns the number of elements stored in this set.
		 * @return the number of elements stored in this set.
		 */
		public int size() {
			return list.size();
		}
		
		@Override
		public String toString(){
			return list.toString();
		}
}
