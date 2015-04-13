package exams;

import java.util.EmptyStackException;

import position.Position;

import stack.ArrayStack;
import stack.Stack;
import nodeList.NodePositionList;
import nodeList.PositionList;

/*
 * La funzione deve restituire una lista palindroma la cui prima metà contiene gli 
 * elementi di S nello stesso ordine in cui appaiono in S (se letti dal bottom verso 
 * il top) e la cui seconda metà contiene gli elementi di S disposti in ordine 
 * inverso. Ad esempio se S=<a b c d>, dove il bottom è l’elemento più a sinistra, 
 * allora la funzione restituisce la lista <a b c d d c b a>.
 * Se lo stack è vuoto la funzione deve lanciare l’eccezione StackEmptyException.
 * La funzione deve lasciare inalterato il contenuto dello stack S.
 * La funzione NON deve usare alcuna struttura dati al di fuori di S e della lista
 * output.
 */
public class ExStack_18_6 {
	public static <E> PositionList<E> createList(Stack<E>S){
		if(S.isEmpty())
			throw new EmptyStackException();
		NodePositionList<E> list = new NodePositionList<E>();
		while(!S.isEmpty()){
			E el = S.pop();
			list.addFirst(el);
			list.addLast(el);
		}
		Position<E> el = list.first();
		for(int i = 0; i<list.size()/2; i++){
			S.push(el.element());
			el = list.next(el);
		}
		return list;
	}
	
	public static void main(String[] args){
		Stack<String> stack = new ArrayStack<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		System.out.println("stack before - "+stack);
		PositionList<String> list = createList(stack);
		System.out.println("stack after - "+stack);
		System.out.println("output list - "+list);
	}
}
