package exams;

import position.Position;
import exception.EmptyTreeException;
import tree.LinkedTree;
import tree.Tree;


/*
 * La funzione deve effettuare una visita (ricorsiva) dell’albero T e sommare 
 * gli elementi delle foglie visitate fino a che non ottiene un valore maggiore 
 * o uguale di x. La funzione restituisce in output il valore risultante da tale 
 * somma. Se la somma degli elementi di tutte le foglie è minore di x, la funzione 
 * restituisce -1. Se l’albero è vuoto la funzione lancia l’eccezione 
 * EmptyTreeException. La funzione NON deve invocare funzioni che restituiscono o 
 * utilizzano collezioni/iteratori di tutti i nodi o di tutte le foglie dell’albero.
 */


public class ExTree_26_2 {
	public static int sommaFoglie(Tree<Integer> T,int x){
		int sum = 0;
		if(T.isEmpty())
			throw new EmptyTreeException();
		sum = summingLeaves(T, T.root(), x);
		if(sum <x)
			return -1;
		return sum;
		
	}
	private static int summingLeaves(Tree<Integer> T, Position<Integer> currentPos, int x){
		if(T.isExternal(currentPos))
			return currentPos.element();
		int sum = 0;
		for(Position<Integer> child: T.children(currentPos)){
			sum += summingLeaves(T, child, x);
			if(sum >=x) 
				return sum; 
		}
		return sum;
		
	}
	
	
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> root = tree.addRoot(1);
		Position<Integer> child = tree.addChild(2, root);
		tree.addChild(3, root);
		tree.addChild(4, root);
		tree.addChild(23, child);
		tree.addChild(24, child);
		tree.addChild(25, child);
		System.out.println(sommaFoglie(tree,50));
	}
}
