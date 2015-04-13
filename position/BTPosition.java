package position;

/**
 * 
 * @author SuperJulietta
 * @param <E>
 * 
 * The BTPosition class extends Position and represents a location into 
 * a BinaryTree. 
 */

public interface BTPosition<E> extends Position<E> {
		void setElement(E el);
		BTPosition<E> getLeft();
		BTPosition<E> getRight();
		BTPosition<E> getParent();
		void setLeft(BTPosition<E> pos);
		void setRight(BTPosition<E> pos);
		void setParent(BTPosition<E> pos);
		
}
