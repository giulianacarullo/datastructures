package exams;

import position.Position;
import tree.LinkedTree;
import tree.Tree;


/*
 * Se p2 è un antenato di p1 allora la funzione deve restituire la distanza tra 
 * p1 e p2. Se p2 non è un antenato di p1 allora la funzione deve restituire 
 * un intero negativo. Si vedano gli esempi di output all’inizio del file di test.
 * La funzione deve essere ricorsiva. Nel caso in cui non venga soddisfatto questo 
 * requisito la funzione sarà valutata 7 punti. La funzione non deve usare 
 * strutture dati ausiliarie.
 */

public class ExTree_13_7 {
	public static <E> int distance(Tree<E> T, Position <E> p1, Position<E> p2){
		return search(T, p1, p2,0);
		
	}
	
	public static <E> int search(Tree<E>T, Position<E> p1, Position<E> currentPos, int distance){
		if(T.isExternal(currentPos)){
			if(currentPos.equals(p1)) 
				return distance;
			else return -1;
		}
		int x = 0;
		System.out.println("p1 "+p1 +"currentPos "+ currentPos);

		if(currentPos == p1) {
			return distance;
		}
		
		for(Position<E> child: T.children(currentPos)){
			if ((x = search(T, p1, child, distance+1))>0)
				return x;
		}
		
		return -1;
		
	}
	
	public static void main(String[] args){
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		Position<Integer> root = tree.addRoot(1);
		Position<Integer> child = tree.addChild(2, root);
		tree.addChild(3, root);
		tree.addChild(4, root);
		Position<Integer> x = tree.addChild(23, child);
		tree.addChild(24, child);
		tree.addChild(25, child);
		System.out.println(distance(tree, child,root));
		System.out.println(distance(tree, x,root));

	}
}
