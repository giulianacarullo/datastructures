package exams;

import map.HashTableMap;
import indexList.ArrayIndexList;
import indexList.IndexList;

/*
 * La funzione prende in input un vettore V di interi compresi tra 0 e k-1 
 * e per ciascun elemento del vettore, sostituisce con null tutte le occorrenze 
 * dell’elemento ad eccezione della prima occorrenza. Ad esempio se inizialmente 
 * V = <10 5 12 6 5 7 5 11 12 8 10 6 >, dopo aver invocato la funzione si ha
 * V= <10 5 12 6 null 7 null 11 null 8 null null >.
 */
public class ExArrayList_20_7 {
	public static void main(String[] args){
		ArrayIndexList<Integer> V = new ArrayIndexList<Integer>();
		V.add(0, 10);
		V.add(1, 5);
		V.add(2, 12);
		V.add(3, 6);
		V.add(4, 5);
		V.add(5, 7);
		V.add(6, 5);
		V.add(7, 11); //
		V.add(8, 12);
		V.add(9, 8);
		V.add(10, 10);
		V.add(11, 6);
		System.out.println("Before - "+V);
		removeDuplicates(V, 13);
		System.out.println("After - "+V);
		ArrayIndexList<Integer> V1 = new ArrayIndexList<Integer>();
		V1.add(0, 1);
		V1.add(1, 1);
		V1.add(2, 1);
		V1.add(3, 1);
		V1.add(4, 1);
		System.out.println("Before - "+V1);
		removeDuplicates(V1, 2);
		System.out.println("After - "+V1);
	}
	
	public static <E> void removeDuplicates(IndexList<Integer>V, int k){
		HashTableMap<Integer, Object> map = new HashTableMap<Integer, Object>();
		int size = V.size();
		for(int i = 0; i<size; i++){
			Integer el = V.get(i);
			if(map.get(el) == null) //element not in the map
				map.put(el, new Object());
			else
				V.set(i, null); 
		}
	}
	
}
