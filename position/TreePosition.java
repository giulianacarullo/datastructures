package position;

import nodeList.PositionList;

/**
 * The TreePosition class represents a Position into a tree. 
 * @author SuperJulietta
 * @param <E> the element type.
 */

public interface TreePosition<E> extends Position<E>{
	void setElement(E el);
	PositionList<Position<E>> getChildren();
	void setChildren(PositionList<Position<E>> c);
	TreePosition<E> getParent();
	void setParent(TreePosition<E> p);

}
