package exams;

import java.util.Comparator;

import comparator.DefaultComparator;

import position.Position;

import entry.Entry;
import entry.MyEntry;

import binaryTree.ArrayListCompleteBinaryTree;

public class ExCBT_12_11 {
	
	
	
	
	
	/*
	 * La classe ExamArrayListCBT estende ArrayListCompleteBinaryTree e 
	 * rappresenta un albero binario completo di elementi di tipo Entry. 
	 * Quando è invocato su un’istanza T di ExamArrayListCBT, il metodo isHeap 
	 * verifica se T è un heap rispetto alla relazione d’ordine totale definita 
	 * dal comparatore c. Se T è un heap allora isHeap inserisce tutte le entrate 
	 * di T in newT disponendole nello stesso modo in cui sono disposte in T e 
	 * restituisce true. Se T non è un heap allora isHeap inserisce in newT 
	 * le entrate di T che, a partire dalla radice e attraversando i livelli 
	 * successivi da sinistra verso destra, soddisfano l’heap-order 
	 */
	public static class  ExamArrayListCBT<K,V> extends ArrayListCompleteBinaryTree<Entry<K, V>>{
		public boolean isHeap(Comparator<K> c, ExamArrayListCBT <K,V>newT){
			newT.add(this.root().element());
			return myHeap(c, newT, this.root());
		}
		
		
		public boolean myHeap(Comparator<K> c, ExamArrayListCBT<K, V> newT, Position<Entry<K, V>> currentPos){
			if(this.isExternal(currentPos)){
				return true;
			}
			boolean flag = true;
			if(hasLeft(currentPos))
				if(c.compare(currentPos.element().getKey(), left(currentPos).element().getKey()) <=0)
					newT.add(left(currentPos).element());
				else
					flag = false;
			if(hasRight(currentPos))
				if(c.compare(currentPos.element().getKey(), right(currentPos).element().getKey()) <=0)
					newT.add(right(currentPos).element());
				else
					flag = false;
			boolean flag1 = true;
			if(hasLeft(currentPos))
				flag1 = myHeap(c, newT, left(currentPos));
			 boolean flag2 = true;
			 if(hasRight(currentPos))
				 flag2 = myHeap(c, newT, right(currentPos));
			 if(flag && flag1 && flag2)
				 return true;
			 return false;
			
		}
	}
	
	public static void main(String[] args){
		ExamArrayListCBT<Integer, Integer> t = new ExamArrayListCBT<Integer, Integer>();
		t.add(new MyEntry<Integer, Integer>(1, 1));
		t.add(new MyEntry<Integer, Integer>(2, 2));
		t.add(new MyEntry<Integer, Integer>(3, 3));
		t.add(new MyEntry<Integer, Integer>(4, 4));
		t.add(new MyEntry<Integer, Integer>(5, 5));
		t.add(new MyEntry<Integer, Integer>(6, 6));
		ExamArrayListCBT<Integer, Integer> newT = new ExamArrayListCBT<Integer, Integer>();
		System.out.println(t.isHeap(new DefaultComparator<Integer>(), newT));
		System.out.println(newT);
	}
}
