package exams;

import position.Position;
import nodeList.NodePositionList;
import dictionary.BinarySearchTree;
import entry.Entry;

/*
 * La funzione deve restituire una collezione iterabile delle entrate dell’albero 
 * binario di ricerca in cui le entrate risultano disposte in ordine non crescente 
 * (dalla più grande alla più piccola)rispetto al valore delle chiavi.
 * Se l’albero è vuoto la collezione output deve essere vuota. La funzione non 
 * deve usare alcuna struttura dati ad eccezione della collezione da restituire
 * in output.
 */

public class ExBST_5_7 {
	public static <K,V> Iterable<Entry<K,V>> sortedEntries(BinarySearchTree<K,V>D){
		NodePositionList<Entry<K, V>> outputList = new NodePositionList<Entry<K,V>>();
		if(!D.isEmpty())
			sorted(D, D.root(),outputList); 
		return outputList;
	}
	
	private static<K,V> void sorted(BinarySearchTree<K, V> dic, Position<Entry<K, V>> currentPos, NodePositionList<Entry<K, V>> list ){
		
		if(dic.hasRight(currentPos))
			sorted(dic, dic.right(currentPos), list);
		if(currentPos.element() != null)
			list.addLast(currentPos.element());
		if(dic.hasLeft(currentPos))
			sorted(dic, dic.left(currentPos), list);

	}
	
	public static void main(String[] args){
		BinarySearchTree<Integer, String> dic = new BinarySearchTree<Integer, String>();
		dic.insert(1, "cacca");
		dic.insert(4, "pupù");
		dic.insert(0, "caccapupù");
		dic.insert(2, "perdincibacco");
		System.out.println(dic);
		
		Iterable<Entry<Integer, String>> list = sortedEntries(dic);
		System.out.println(list);
	}
}
