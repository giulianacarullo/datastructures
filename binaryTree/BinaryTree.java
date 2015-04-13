package binaryTree;

import exception.BoundaryViolationException;
import position.InvalidPositionException;
import position.Position;
import tree.Tree;


/**
 * The BinaryTree class extends Tree interface. 
 * Indeed, a BinaryTree is the specialized tree that has two 
 * possible branches i.e left and right branch. So further methods 
 * like left() right() hasLeft() hasRight() are provided to the user.
 * 
 * @author SuperJulietta
 * @param <E> the element type.
 * 
 */
public interface BinaryTree<E> extends Tree<E>  {
	Position<E> left(Position<E> pos) throws InvalidPositionException, BoundaryViolationException;
	Position<E> right(Position<E> pos) throws InvalidPositionException, BoundaryViolationException;
	boolean hasLeft(Position<E> pos) throws InvalidPositionException;
	boolean hasRight(Position<E> pos) throws InvalidPositionException;
}
