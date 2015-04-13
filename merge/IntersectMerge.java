package merge;

import nodeList.PositionList;

public class IntersectMerge<E> extends Merge<E>{
	@Override
	public void bothAreEquals(E a, E b, PositionList<E> C) {	
		C.addLast(a);
	}
}
