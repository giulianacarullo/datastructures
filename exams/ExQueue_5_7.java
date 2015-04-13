package exams;

import queue.ArrayQueue;
import queue.Queue;
import stack.ArrayStack;
import stack.Stack;

/*
 * La funzione deve restituire uno stack che contiene tutti gli elementi della 
 * coda Q disposti in modo che, se letti dal top verso il bottom, 
 * risultano nello stesso ordine in cui appaiono disposti in Q. 
 * Ad esempio se Q=<2 4 1 6 3>, dove 2 è l’elemento al front, allora la funzione
 * resituisce lo stack <2 4 1 6 3>, dove 2 è l’elemento al top .
 * La funzione deve lasciare inalterato il contenuto di Q. La funzione NON deve 
 * usare alcuna struttura dati al di fuori di Q e dello stack output.
 */

public class ExQueue_5_7 {
	public static <E> Stack<E> createStack(Queue<E>Q){
		ArrayStack<E> stack = new ArrayStack<E>();
		int size = Q.size();
		while(size>0){
			for(int i = 0; i<size-1; i++)
				Q.enqueue(Q.dequeue());
			stack.push(Q.dequeue());
			size--;
		}
		return stack;
		
	}
	
	public static void main(String[] args){
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		Stack<Integer> stack = createStack(queue);
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
}
