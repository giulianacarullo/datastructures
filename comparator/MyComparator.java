package comparator;

import java.util.Comparator;

import position.Position;

/**
 * The MyComparator class implements Comparator interface.
 * This class can be used to compare the element of two Positions.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 */

@SuppressWarnings("unchecked")
public class MyComparator <E extends Comparable> implements Comparator<Position<E>> {

	public int compare(Position<E> arg0, Position<E> arg1) {
		return arg0.element().compareTo(arg1.element());
	}



}
