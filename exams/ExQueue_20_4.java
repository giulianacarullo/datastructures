package exams;

import priorityQueue.HeapPriorityQueue;
import queue.ArrayQueue;
import queue.Queue;

/*
 * La funzione deve cancellare da Q il k-esimo elemento lasciando gli altri 
 * elementi nello stesso ordine in cui apparivano inizialmente. Ad esempio 
 * se Q=<a,b,c,d,e,f,g,h> dove a è l’elemento al front allora dopo aver invocato 
 * extract(Q,3) si ha Q=<a,b,d,e,f,g,h>. Se Q contiene meno di k elementi allora 
 * la funzione non deve cancellare nessun elemento. La funzione NON deve 
 * usare alcuna struttura dati ausiliaria.
 */

public class ExQueue_20_4 {
	public static <E> void extract(Queue<E> Q,int k){
		int size = Q.size();
		if(k<Q.size()){
			for(int i = 0; i<size; i++)
				if(i == k-1)
					Q.dequeue();
				else
					Q.enqueue(Q.dequeue());
		}
	}
	
	public static void main(String[] args){
		ArrayQueue<String> q = new ArrayQueue<String>();
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");
		q.enqueue("f");
		q.enqueue("g");
		q.enqueue("h");
		System.out.println(q);
		extract(q, 3);
		//while(!q.isEmpty())
		//	System.out.print("  "+q.dequeue());
		System.out.print(q);
	}
}
