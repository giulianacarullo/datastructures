package comparator;

import java.util.Comparator;

/**
 * Default Comparator.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 */
public class DefaultComparator<E> implements Comparator<E> {

	@SuppressWarnings("unchecked")
	public int compare(E arg1, E arg2) {
		return((Comparable<E>)arg1).compareTo(arg2);
	}

}
