package merge;

import nodeList.PositionList;

public class UnionMerge<E> extends Merge<E> {
	
	@Override
	public void bothAreEquals(E a, E b, PositionList<E> C) {	
		C.addLast(a);
	}
	
	@Override
	public void aIsLess(E a, PositionList<E> C) {	
		C.addLast(a);
	}
	@Override
	public void bIsLess(E b, PositionList<E> C) {	
		C.addLast(b);
	}

}
