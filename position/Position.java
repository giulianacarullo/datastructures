package position;

/**
 * 
 * @author SuperJulietta
 * @param <E>
 * 
 * The Position class represents a location into a list. 
 * A Position is defined relatively, that is, in terms of its neighbors.
 * In a list, a position p will always be after a position q and before 
 * a position r(unless p is first or last position in the list).
 * The Position interface provides only a method to extract element from
 * the position.
 */

public interface Position <E>{
	E element();
}
