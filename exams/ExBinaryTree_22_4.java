package exams;

import position.Position;
import tree.Tree;
import exception.EmptyTreeException;
import binaryTree.BinaryTree;
import binaryTree.LinkedBinaryTree;

/*
 * La funzione prende in input un albero binario T e restituisce il numero di nodi 
 * interni che hanno esattamente due figli. Se l’albero è vuoto la funzione lancia 
 * l’eccezione EmptyTreeException. La funzione NON deve invocare funzioni che 
 * restituiscono o utilizzano collezioni/iteratori di TUTTI i nodi o di TUTTE le 
 * foglie dell’albero.
 */

public class ExBinaryTree_22_4 {

	public static <E> int count(BinaryTree<E> T){
		if(T.isEmpty())
			throw new EmptyTreeException();
		return countingNodes(T, T.root());
	}
	
	public static <E> int countingNodes(BinaryTree<E> T, Position<E> currentPos){
		int sum;
		if(T.isExternal(currentPos))
			return 0;
		
		if(T.hasLeft(currentPos) && T.hasRight(currentPos))
			sum = 1;
		else
			sum = 0;
		if(T.hasLeft(currentPos))
			sum += countingNodes(T, T.left(currentPos));
		if(T.hasRight(currentPos))
			sum +=countingNodes(T, T.right(currentPos));
		return sum;
		
	}
	
	public static void main(String[] args){
		LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
		Position<Integer> root = bt.addRoot(1);
		Position<Integer> child1 =bt.insertLeft(root, 2);
		Position<Integer> child2 = bt.insertRight(root, 3);
		Position<Integer> child3 = bt.insertLeft(child1, 4);
		bt.insertRight(child1, 5);
		bt.insertLeft(child2, 6);
		bt.insertLeft(child3, 7);
		bt.insertRight(child3, 8);
		System.out.println(count(bt));

	}
}
