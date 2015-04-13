package exams;

import position.Position;
import exception.EmptyTreeException;
import tree.LinkedTree;
import tree.Tree;

/*
 * La funzione deve restituire il numero di nodi interni dell’albero. A tale 
 * scopo la funzione deve effettuare una visita ricorsiva dell’albero T. 
 * Se l’albero è vuoto la funzione deve lanciare l’eccezione EmptyTreeException.
 * Eccezion fatta per children, la funzione NON deve invocare funzioni che 
 * restituiscono o utilizzano collezioni/iteratori di nodi dell’albero.
 */

public class ExTree_15_6 {
	public static <E> int countInternalNodes(Tree<E> T){
		if(T.isEmpty())
			throw new EmptyTreeException();
		return count(T, T.root());
		
	}
	
	private static <E> int count(Tree<E> T, Position<E> currentPos){
		if(T.isExternal(currentPos))
			return 0;
		int sum = 1;
		for(Position<E> child :T.children(currentPos)){
			sum+= count(T, child);
		}
		return sum;
	}
	
	
	
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> root = tree.addRoot(1);
		Position<Integer> child = tree.addChild(2, root);
		tree.addChild(3, root);
		tree.addChild(4, child);
		System.out.println(countInternalNodes(tree));
		
		
	}
}
