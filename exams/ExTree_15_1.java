package exams;

import nodeList.NodePositionList;
import exception.EmptyTreeException;
import position.Position;
import tree.LinkedTree;
import tree.Tree;

/*
 * La funzione deve effettuare una visita ricorsiva preorder dell’albero e 
 * fermarsi non appena trova una foglia contenente x. La funzione deve restituire 
 * una collezione iterabile di tutti i nodi visitati in precedenza. Se x non è 
 * contenuto in alcuna foglia dell’albero la funzione deve restituire una collezione 
 * contenente tutti i nodi dell’albero. Se T è vuoto la funzione deve lanciare 
 * l’eccezione EmptyTreeException. La funzione NON deve invocare funzioni che 
 * restituiscono o utilizzano collezioni/iteratori di tutti i nodi dell’albero.
 */

public class ExTree_15_1 {

	public static <E> Iterable<Position<E>> searchLeaf(Tree<E> T,E x){
		if(T.isEmpty())
			throw new EmptyTreeException();
		NodePositionList<Position<E>> list = new NodePositionList<Position<E>>();
		search(T, T.root(),x, list);
		return list;	
	}
	public static <E> boolean search(Tree<E> T, Position<E> currentPos, E x,
			NodePositionList<Position<E>> list){
		list.addLast(currentPos);
		if(T.isExternal(currentPos))
			if(currentPos.element().equals(x))
				return true;
			else
				return false;
		for(Position<E> child:T.children(currentPos)){
			if(search(T, child, x, list))
				return true;	

		}
		return false;
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
		System.out.println(searchLeaf(tree, 38));
		System.out.println(searchLeaf(tree, 2));
		System.out.println(searchLeaf(tree, 23));

		
	}
}
