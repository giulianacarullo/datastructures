package exams;

import java.util.Iterator;

import binaryTree.LinkedBinaryTree;

import position.Position;
import tree.LinkedTree;
import tree.Tree;
import exception.EmptyTreeException;


/*
 * La funzione deve effettuare una visita ricorsiva preorder dell’albero T e 
 * restituire in output la position del primo nodo incontrato contenente x. 
 * Se nessun nodo dell’albero contiene l’elemento x allora la funzione restituisce 
 * null.
 */
public class ExTree_20_7 {

	public static <E> Position<E>findElt(Tree<E> T, E x){
		if(T.isEmpty())
			throw new EmptyTreeException();
		return find(T, x, T.root());
	}
	
	public static <E> Position<E> find(Tree<E> T, E x, Position<E> currentPos){
		if(currentPos.element().equals(x))
			return currentPos;
		if(T.isExternal(currentPos))
			if(currentPos.element().equals(x))
				return currentPos;
			else return null;
		Iterator<Position<E>> itc = T.children(currentPos).iterator();
		while(itc.hasNext()){
			Position<E> pos = find(T, x, itc.next());
			if( pos!= null)
				return pos;
				
		}
		return null;
	}
	
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> child = tree.addRoot(1);
		Position<Integer> ch = tree.addChild(2, child);
		tree.addChild(3, child);
		tree.addChild(4, child);
		System.out.println(findElt(tree, 3));
		System.out.println(findElt(tree, 1));
		System.out.println(findElt(tree, 2));
		System.out.println(findElt(tree, 4));
		System.out.println(findElt(tree, 6));
		tree.addChild(1, ch);
		System.out.println(findElt(tree, 1));
	}
}
