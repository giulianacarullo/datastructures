package merge;

import nodeList.PositionList;

public class SubtractMerge<E> extends Merge<E>{
	@Override
	public void aIsLess(E a, PositionList<E> C) {	
		C.addLast(a);
	}
}
