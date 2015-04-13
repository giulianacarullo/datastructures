package exams;

import deque.Deque;
import deque.NodeDeque;
import stack.ArrayStack;
import stack.Stack;
import utilities.Node;

/*
 * La funzione deve invertire l’ordine dei primi k elementi (primi a partire 
 * dal bottom) dello stack S. Ad esempio se S=<a b c d e f g h i> dove i è 
 * l’elemento al bottom, allora dopo aver invocato flipBottomElts(S,5) si ha 
 * S=<a b c d i h g f e >. Se S contiene un numero di elementi minore o uguale di 
 * k allora la funzione deve invertire l’ordine di tutti gli elementi.
 * Se S è vuoto la funzione deve lanciare l’eccezione EmptyStackException.
 * La funzione deve usare come unica struttura dati ausiliaria un deque.
 */

public class ExStack_13_7 {
	public static <E> void flipBottomElts(Stack <E> S,int k){
		Deque<E> deque = new NodeDeque<E>();
		while(!S.isEmpty())
			deque.addLast(S.pop());
		if(k<deque.size()){
			int dim = deque.size()-k;
			for(int i =0; i<dim; i++)
				deque.addLast(deque.removeFirst());
			for(int i = 0; i<k; i++)
				S.push(deque.removeFirst());
			while(!deque.isEmpty())
				S.push(deque.removeLast());
		}
		else
			while(!deque.isEmpty())
				S.push(deque.removeFirst());
	}
	
	public static void main(String[] args){
		Stack<String> stack = new ArrayStack<String>();
		stack.push("i");
		stack.push("h");
		stack.push("g");
		stack.push("f");
		stack.push("e");
		stack.push("d");
		stack.push("c");
		stack.push("b");
		stack.push("a");
		System.out.println(stack);
		flipBottomElts(stack, 10);
		System.out.println(stack);
	}
}
