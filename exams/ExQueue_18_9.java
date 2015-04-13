package exams;

import priorityQueue.PriorityQueue;
import queue.NodeQueue;
import queue.Queue;

/*
 * La funzione deve inserire gli elementi di Q1 in Q2 in modo che gli elementi di Q1
 * vengano a trovarsi prima di quelli di Q2. Ad esempio se Q1=<7,3,5,2,9> e 
 * Q2=<6,1,4>, dove 7 è l’elemento al front di Q1 e 6 è l’elemento al front di Q2, 
 * allora dopo aver invocato join(Q1,Q2) si ha Q2=<7,3,5,2,9,6,1,4>.
 * La funzione NON deve usare alcuna struttura dati ausiliaria.
 */
public class ExQueue_18_9 {
	public static <E> void join(Queue<E> Q1 , Queue<E> Q2){
		int dim = Q2.size();
		for(int i = 0; i<Q1.size(); i++){
			Q2.enqueue(Q1.front());
			Q1.enqueue(Q1.dequeue());
		}
		for(int i = 0; i<dim; i++){
			Q2.enqueue(Q2.dequeue());
		}
	}
	
	public static void main(String[] args){
		NodeQueue<Integer> q1 = new NodeQueue<Integer>();
		NodeQueue<Integer> q2 = new NodeQueue<Integer>();
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		q1.enqueue(4);
		q1.enqueue(5);
		q2.enqueue(6);
		q2.enqueue(7);
		q2.enqueue(8);
		join(q1, q2);
		System.out.println(q2);
	}
}
