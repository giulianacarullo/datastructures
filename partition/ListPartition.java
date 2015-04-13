package partition;


import java.util.Iterator;

import nodeList.NodePositionList;
import nodeList.PositionList;
import position.Position;
import map.HashTableMap;
import map.Map;
import set.OrderedListSet;
import set.Set;

/**
 * The ListPartition class implements Partition with a PositionList.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 */

public class ListPartition<E>  implements Partition<E>{

	//Instance Variables
	protected PositionList<Set <E>> partition;
	protected Map<E,Set<E>> elements;
	
	//Constructor
	public ListPartition(){
		partition = new NodePositionList<Set<E>>();
		elements = new HashTableMap<E, Set<E>>();
	}

	/**
	 * Makes a set containing the given element.
	 * @param x the element to insert in the set.
	 * @return the created set.
	 */
	public Set<E> makeSet(E x) {
		OrderedListSet<E> newSet = new OrderedListSet<E>();
		newSet.fastInsert(x);
		elements.put(x, newSet);
		partition.addLast(newSet);
		newSet.setLocation(partition.last());
		return newSet;
	}


	/**
	 * Finds a set which contains the specified element.
	 * @param x the element to search.
	 * @return the set which contains the specified element.
	 */
	public Set<E> find(E x) {
		return elements.get(x);
	}
	
	/**
	 * Unions two disjoint sets.
	 * @param A the first set.
	 * @param B the second set.
	 * @return the result set.
	 */
	public Set<E> union(Set<E> A, Set<E> B) {
		OrderedListSet<E> little = null;
		OrderedListSet<E> AUB = null;
		if(A.size()>B.size()){
			little = (OrderedListSet<E>) B; AUB = (OrderedListSet<E>) A;
		}
		else{
			little = (OrderedListSet<E>) A; AUB = (OrderedListSet<E>) B;
		}
		Position<Set<E>> toRemove;
		toRemove = little.location();
		E el = null;
		while(!little.isEmpty()) {
			el = little.remove();
			AUB.fastInsert(el);
			elements.put(el, AUB);		
		}
		partition.remove(toRemove);
		return AUB;
	}
	
	/**
	 * Tests if this Partition is empty.
	 * Returns true if this Partition is empty; false otherwise.
	 * @return true if this Partition is empty; false otherwise.
	 */
	public boolean isEmpty() {
		return partition.isEmpty();
	}
	
	/**
	 * Returns the number of sets stored in this ListPartition.
	 * @return the number of sets stored in this ListPartition.
	 */
	public int size() {
		return partition.size();
	}

	@Override
	public String toString() {
		  String s;
		  s = "[";
		  if (size() > 0) {
			  Iterator<Set<E>> it = partition.iterator();
			  s+= it.next();
			  if (size() > 1){
				  while(it.hasNext())
					  s += " - " + it.next();
		  }
		  }
		  return s+"]";
	}

}
