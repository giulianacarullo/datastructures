package exams;

import queue.NodeQueue;
import queue.Queue;

/*
 * La funzione deve inserire l’elemento newEl nella coda Q in modo che venga a 
 * trovarsi prima dell’elemento x. Se la coda Q contiene più occorrenze di x 
 * allora newEl deve essere inserito prima di ogni occorrenza di x in Q. Se Q 
 * non contiene alcuna occorrenza di x allora la funzione non deve effettuare alcun 
 * inserimento. Ad esempio, se Q=<1 4 2 3 2 5 3>, dove 1 è l’elemento al front, 
 * newEl=6 e x=3, allora dopo l’invocazione di insertBefore si ha e 
 * Q=<1 4 2 6 3 2 5 6 3>. Se Q è vuota allora la funzione deve lanciare l’eccezione 
 * EmptyQueueException. La funzione NON deve usare alcuna struttura dati ausiliaria.
 */

public class ExQueue_22_4 {
	public static <E> void insertBefore(Queue<E> Q, E newElt, E x){
		int dim = Q.size();
		boolean flag = false;
		for(int i = 0; i<dim; i++){
			E currentEl = Q.dequeue();
			if(currentEl.equals(x) && flag == false) {
				Q.enqueue(newElt);
				flag = true;
			}
			Q.enqueue(currentEl);
		}
	}
	
	
	public static void main(String[] args){
		Queue<Integer> queue = new NodeQueue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(1);
		queue.enqueue(3);
		queue.enqueue(6);
		queue.enqueue(7);
		System.out.println(queue);
		insertBefore(queue, 10, 2);
		System.out.println(queue);
		insertBefore(queue, 10, 1);
		System.out.println(queue);
		insertBefore(queue, 22, 22);
		System.out.println(queue);
		
	}
}
