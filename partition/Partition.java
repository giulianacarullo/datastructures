package partition;

import set.Set;

/**
 * A collection that contains no duplicate sets. 
 * More formally, partitions contain no pair of sets set1 and set2 such that 
 * set1.equals(set2). As implied by its name, this interface models the 
 * mathematical partition abstraction. 
 * 
 * @author SuperJulietta
 * @param <E> the partition type.
 */

public interface Partition<E> {
	public int size();
	public boolean isEmpty();
	public Set<E> makeSet(E x);
	public Set <E> union(Set<E> A, Set<E> B);
	public Set <E> find(E x);
	
}
