package exams;

import position.Position;
import tree.LinkedTree;
import tree.Tree;

/*
 * La funzione deve effettuare una visita (ricorsiva) postorder dell’albero T 
 * e restituire la profondità del primo nodo incontrato che ha il campo element 
 * uguale ad x. Se non c’è alcun nodo con il campo element uguale ad x allora la 
 * funzione restituisce -1. La funzione NON deve invocare funzioni che calcolano 
 * la profondità di un nodo. Nel caso in cui non venga soddisfatto questo requisito 
 * la funzione sarà valutata al massimo 18 punti. Ad eccezione di children, 
 * la funzione depthOf NON deve invocare funzioni che restituiscono o utilizzano 
 * collezioni/iteratori di nodi dell’albero.
 */

public class ExTree_11_2 {
	public static <E> int depthOf(Tree<E> T, E x){
		if(T.isEmpty())
			return -1;
		return depth(T, T.root(), x);
	}
	
	private static <E> int depth(Tree<E> T, Position<E> currentPos, E x){
		
		if(T.isExternal(currentPos))
			if(currentPos.element().equals(x))
				return 1;
			else
				return -1;
		int tmp;
		for(Position<E> child : T.children(currentPos)){
			tmp = depth(T, child, x);
			if(tmp != -1)
				return tmp+1;
		}
		if(currentPos.element().equals(x))
			return 1;
		return -1;	
	}
	
	
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> child = tree.addRoot(1);
		Position<Integer> ch = tree.addChild(2, child);
		tree.addChild(3, child);
		tree.addChild(4, child);
		tree.addChild(7, ch);
		System.out.println(depthOf(tree, 7));
		System.out.println(depthOf(tree, 1));
		System.out.println(depthOf(tree, 4));
		System.out.println(depthOf(tree, 17));

	}
}
