package exams;

import position.Position;
import exception.EmptyTreeException;
import tree.LinkedTree;
import tree.Tree;

/*
 * La funzione deve restituire true se x è contenuto in qualche nodo dell’albero 
 * e false altrimenti. 
 * Se T è vuoto la funzione deve lanciare l’eccezione EmptyTreeException.
 * La funzione NON deve invocare funzioni che restituiscono o utilizzano 
 * collezioni/iteratori di tutti i nodi dell’albero.
 */

public class ExTree_12_11 {
	public static <E> boolean search(Tree<E> T,E x){
		if(T.isEmpty())
			throw new EmptyTreeException();
		return mySearch(T, T.root(), x);
	}
	
	private static <E> boolean mySearch(Tree<E> T, Position<E> currentPos, E x){
		boolean founded = false;
		if(T.isExternal(currentPos))
			if(x.equals(currentPos.element()))
				return true;
			else
				return false;
		if(currentPos.element().equals(x))
			return true;
		for(Position<E> child :T.children(currentPos)){
			founded = mySearch(T, child, x);
			if(founded)
				return true;
		}
		return false;
	}
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> child = tree.addRoot(1);
		Position<Integer> ch = tree.addChild(2, child);
		tree.addChild(3, child);
		tree.addChild(4, child);
		System.out.println(search(tree, 1));
		System.out.println(search(tree, 2));
		System.out.println(search(tree, 3));
		System.out.println(search(tree, 4));
		System.out.println(search(tree, 5));
	}

}

