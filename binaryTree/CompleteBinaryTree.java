package binaryTree;

import position.Position;

/**
 * The CompleteBinaryTree class extends BinaryTree interface. 
 * Indeed, A CompleteBinaryTree is a BinaryTree with the additional 
 * property that every node must have exactly two children.
 * 
 * @author SuperJulietta
 * @param <E> the element type.
 * 
 */

public interface CompleteBinaryTree<E> extends BinaryTree<E> {
	Position<E> add(E el);
	E remove();
}
