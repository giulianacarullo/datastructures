package nodeList;

import exception.BoundaryViolationException;
import exception.EmptyListException;
import position.InvalidPositionException;
import position.Position;


/**
 * The PositionList class represents an ordered collection. 
 * The user of this interface has precise control over 
 * where in the list each element is inserted. 
 * The user can access elements by their position in the list.
 * Additional methods like size and isEmpty are provided. 
 * The PositionList interface provides ten methods for positional 
 * access to list elements.  Additional methods like 
 * size and isEmpty are provided. 
 * 
 * @author SuperJulietta
 * @param <E> the element type.
 * 
 * 
 */

public interface PositionList<E> extends Iterable<E> {
	Position<E> first() throws EmptyListException;
	Position<E> last() throws EmptyListException;
	Position<E> next(Position<E> pos) throws InvalidPositionException, BoundaryViolationException;
	Position<E> prev(Position<E> pos) throws InvalidPositionException, BoundaryViolationException;
	void addFirst(E el);
	void addLast(E el);
	Position<E> addBefore(Position<E> pos, E el) throws InvalidPositionException;
	Position<E> addAfter(Position<E> pos, E el) throws InvalidPositionException;
	E remove(Position<E> pos) throws InvalidPositionException;
	E set(Position <E> pos, E el)throws InvalidPositionException;
	Iterable <Position<E>> positions();
	int size();
	boolean isEmpty();
}
