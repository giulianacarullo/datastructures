package exams;

import map.HashTableMap;
import map.Map;
import position.*;
import tree.LinkedTree;
import tree.Tree;

/*
 * La funzione deve effettuare una visita ricorsiva dell’albero e computare per 
 * ciascun nodo p il numero di discendenti di p (incluso p). La funzione deve 
 * restituire in output una mappa che contiene per ciascun nodo p dell’albero 
 * un’entrata con chiave p e valore uguale al numero dei discendenti di p.
 * Se T è vuoto la funzione deve lanciare l’eccezione EmptyTreeException.
 * Ad eccezione di children(), la funzione NON deve invocare funzioni che 
 * restituiscono o utilizzano collezioni/iteratori di nodi dell’albero.
 */
public class ExTree_12_2 {
	public static <E> Map<Position<E>,Integer> recSize(Tree<E> T){
		Map<Position<E>, Integer> map = new HashTableMap<Position<E>, Integer>();
		counter(T, T.root(), map);
		return map;
	}
	
	private static <E> int counter(Tree<E> T, Position<E> currentPos, Map<Position<E>, Integer> map){
		int desc = 1;
		if(T.isExternal(currentPos))
			return 1;
		Iterable<Position<E>> children = T.children(currentPos);
		for(Position<E> child:children){
			desc += counter(T, child, map);
		}
		map.put(currentPos, desc);
		return desc;	
	}
	
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> root = tree.addRoot(1);
		Position<Integer> child = tree.addChild(2, root);
		tree.addChild(1, root);
		tree.addChild(4, root);
		tree.addChild(1, child);
		tree.addChild(4, child);
		tree.addChild(4, child);
		System.out.println(recSize(tree));
	}
}
