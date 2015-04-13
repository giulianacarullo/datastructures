package merge;

import java.util.Comparator;
import java.util.Iterator;

import nodeList.PositionList;



//Generic mergeSort
public abstract class Merge<E> {
	//Instance Variables
	private E a,b; //Current elements
	private Iterator<E> iteratorA, iteratorB;
	
	public void merge(PositionList<E> A, PositionList<E> B, Comparator<E> comp, PositionList<E> C){
		iteratorA = A.iterator();
		iteratorB = B.iterator();
		boolean existsA = advanceA();
		boolean existsB = advanceB();
		while(existsA && existsB){
			int x = comp.compare(a, b);
			if(x<0){
				aIsLess(a, C);
				existsA = advanceA();
			}
			else if(x == 0){
				bothAreEquals(a,b,C);
				existsA = advanceA();
				existsB = advanceB();
			}
			else {
				bIsLess(b, C);
				existsB = advanceB();
			}
		}
		while(existsA){
			aIsLess(a, C);
			existsA = advanceA();
		}
		while(existsB){
			bIsLess(b, C);
			existsB = advanceB();
		}
	}

	public void bothAreEquals(E a, E b, PositionList<E> C) {	}
	public void aIsLess(E a, PositionList<E> C) {	}
	public void bIsLess(E b, PositionList<E> C) {	}

	public boolean advanceA() {
		if(iteratorA.hasNext()){
			a = iteratorA.next();
			return true;
		}
		return false;
	}

	public boolean advanceB() {
		if(iteratorB.hasNext()){
			b = iteratorB.next();
			return true;
		}
		return false;
	}
}