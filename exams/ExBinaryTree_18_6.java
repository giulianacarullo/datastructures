package exams;

import nodeList.NodePositionList;
import binaryTree.BinaryTree;
import binaryTree.LinkedBinaryTree;
import position.Position;

/*
 * La funzione deve effettuare una visita ricorsiva inorder dell’albero T 
 * e restituire una collezione iterabile contenente i primi k nodi visitati 
 * contenenti x. Se nell’albero ci sono meno di k nodi contenenti x allora la 
 * funzione restituisce una collezione contenente tutti i nodi contenenti x 
 * (se l’albero è vuoto la collezione restituita è vuota).
 */
public class ExBinaryTree_18_6 {
	public static <E> Iterable<Position<E>>collectElts(BinaryTree<E> T, int k, E x){
		NodePositionList<Position<E>> list = new NodePositionList<Position<E>>();
		collect(T, k, x, T.root(), list);
		return list;
	}
	
	private static <E> int collect(BinaryTree<E> T,int k,E x,Position<E> currentPos, NodePositionList<Position<E>> list){
		if(T.hasLeft(currentPos)) 
			k = collect(T, k, x,T.left(currentPos), list);
			if(currentPos.element().equals(x)){
				if(k>0){
					list.addLast(currentPos);
					k = k-1;
					return k;
			}
		}
		if(T.hasRight(currentPos)) 
			k= collect(T, k, x,T.right(currentPos), list);
		return k;
	}
	
	public static void main(String[] args){
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();
		tree.addRoot(1);
		Position<Integer> x = tree.insertLeft(tree.root(), 2);
		tree.insertRight(tree.root(), 3);
		tree.insertLeft(x, 1);
		tree.insertRight(x, 1);
		
		Iterable<Position<Integer>> list = collectElts(tree, 2, 1);
		System.out.println(list);
	}
}
