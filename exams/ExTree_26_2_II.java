package exams;

import exception.EmptyTreeException;
import position.Position;
import tree.LinkedTree;
import tree.Tree;

/*
 * La funzione prende in input un albero T di interi positivi. La funzione deve 
 * effettuare una visita (ricorsiva) dell’albero T e restituire in output 
 * l’elemento più grande tra quelli contenuti nelle foglie di T.
 * Se l’albero è vuoto la funzione lancia l’eccezione EmptyTreeException.
 * La funzione NON deve invocare funzioni che restituiscono o utilizzano 
 * collezioni/iteratori di tutti i nodi o di tutte le foglie dell’albero.
 */

public class ExTree_26_2_II {
	public static Integer maxLeaf(Tree<Integer> T){
		if(T.isEmpty())
			throw new EmptyTreeException();
		return searchingMax(T, T.root());
		
	}
	
	
	public static Integer searchingMax(Tree<Integer> T, Position<Integer> currentPos){
		if(T.isExternal(currentPos)){
				return currentPos.element();
		}
		int tmpMax = 0;
		int currentMax = 0;
		for(Position<Integer> child : T.children(currentPos)){
			currentMax = searchingMax(T, child);
			if(tmpMax<currentMax)
				tmpMax = currentMax;
		}
		
		return tmpMax;
		
	}
	
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> root = tree.addRoot(50);
		Position<Integer> child = tree.addChild(2, root);
		tree.addChild(3, root);
		tree.addChild(4, root);
		tree.addChild(23, child);
		tree.addChild(24, child);
		tree.addChild(25, child);
		System.out.println(maxLeaf(tree));

		
	}
}
