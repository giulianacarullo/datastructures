package exams;

import position.Position;
import nodeList.NodePositionList;
import tree.LinkedTree;
import tree.Tree;

/*
 * La funzione deve effettuare una visita ricorsiva postorder dell’albero T e 
 * restituire una collezione iterabile contenente gli elementi dei primi k nodi 
 * interni visitati. Se l’albero contiene meno di k nodi interni allora la funzione 
 * restituisce una collezione contenente gli elementi di tutti i nodi interni 
 * dell’albero (se l’albero non contiene alcun nodo interno allora la collezione 
 * restituita è vuota). La funzione NON deve invocare funzioni che restituiscono 
 * o utilizzano collezioni/iteratori di tutti i nodi o di tutti i nodi interni 
 * dell’albero (o dei loro elementi).
 */

public class ExTree_18_9 {
	public static <E> Iterable<E>collectElts(Tree<E> T, int k){
		NodePositionList<E> list = new NodePositionList<E>();
		search(T, T.root(), k, list);
		return list;	
	}
	
	public static <E> void search(Tree<E> T, Position<E> currentPos,int k, NodePositionList<E> list){
		if(T.isExternal(currentPos))
			if(k>list.size()){
				list.addLast(currentPos.element());
				return;
			}
			else
				return;
		for(Position<E> child : T.children(currentPos)){
			if(k>list.size()) {
				search(T, child, k, list);
			}
			else 
				return;
		}
		if(k>list.size()){
			list.addLast(currentPos.element());
		}
		return;
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
		System.out.println(collectElts(tree, 3));
		System.out.println(collectElts(tree, 1));
		System.out.println(collectElts(tree, 5));
		System.out.println(collectElts(tree, 0));



		
	}
}
