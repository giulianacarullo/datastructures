package set;
/**
 * A collection that contains no duplicate elements. 
 * More formally, sets contain no pair of elements e1 and e2 such that 
 * e1.equals(e2). As implied by its name, this interface models the 
 * mathematical set abstraction. 
 * 
 * @author SuperJulietta
 * @param <E> the set type.
 */
public interface Set<E> {
		public int size();
		public boolean isEmpty();
		public Set<E> union(Set<E> anotherSet);
		public Set<E> intersect(Set<E> anotherSet);
		public Set<E> subtract(Set<E> anotherSet);

}
