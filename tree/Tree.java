package tree;

import exception.BoundaryViolationException;
import exception.EmptyTreeException;
import position.InvalidPositionException;
import position.Position;

/**
 * The Tree class provides three getter methods, one setter method and 3 checker methods(????). 
 * Additional methods like isEmpty and size are provided.
 * 
 * @author SuperJulietta
 * @param <E> the element type.
 * 
 */
public interface Tree <E> extends Iterable<E>{
	int size();
	boolean isEmpty();
	Position<E> root() throws EmptyTreeException;
	Position<E> parent(Position <E> p) throws InvalidPositionException, BoundaryViolationException;
	Iterable<Position <E>> children(Position <E> p)throws InvalidPositionException;
	boolean isInternal(Position<E> p)throws InvalidPositionException;
	boolean isExternal(Position<E> p)throws InvalidPositionException;
	boolean isRoot(Position<E> p)throws InvalidPositionException;
	E replace(Position<E> p, E el)throws InvalidPositionException;
	Iterable <Position<E>> positions();

}
