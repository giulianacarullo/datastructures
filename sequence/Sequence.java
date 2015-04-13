package sequence;

import nodeList.PositionList;
import indexList.IndexList;
import position.InvalidPositionException;
import position.Position;
import deque.Deque;

	/**
	 * The Sequence class supports all methods of the Deque ADT,
	 * PositionList ADT and IndexList ADT. 
	 * The user of this interface can access to the elements in the list
	 * either by their positions or by their indices. 
	 * Additional methods like atIndex and indexOf are provided in order to
	 * get a position from its index and vice versa.
	 * 
	 * @author SuperJulietta
	 * @param <E> the element type.
	 * 
	 */

public interface Sequence<E> extends PositionList<E>, IndexList<E>, Deque<E> {
		
	Position<E> atIndex(int index) throws IndexOutOfBoundsException;
	int indexOf(Position<E> position) throws InvalidPositionException;
		

}
