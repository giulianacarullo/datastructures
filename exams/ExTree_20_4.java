package exams;

import position.Position;
import tree.LinkedTree;
import tree.Tree;


/*
 * La funzione deve restituire il numero di nodi dell’albero che contengono 
 * l’elemento x. A tale scopo la funzione deve effettuare una visita ricorsiva 
 * dell’albero T. Se l’albero è vuoto la funzione deve lanciare l’eccezione 
 * EmptyTreeException. Eccezion fatta per children, la funzione NON deve invocare 
 * funzioni che restituiscono o utilizzano collezioni/iteratori di nodi dell’albero.
 */

public class ExTree_20_4 {

	public static <E> int countNodes(Tree<E> T, E x){
		return counter(T,x,T.root());
		
	}
	
	private static <E> int counter(Tree<E> T, E x, Position<E> currentPos){
		if(T.isExternal(currentPos))
			if(x.equals(currentPos.element()))
				return 1;
			else
				return 0;
		
		int count = 0;
		if(x.equals(currentPos.element()))
			count++;
		for(Position<E> child :T.children(currentPos))
			count += counter(T,x,child);
		return count;
		
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
		System.out.println(countNodes(tree, 2));
	}
}

