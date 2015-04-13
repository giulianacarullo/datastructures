package exams;

import priorityQueue.HeapPriorityQueue;
import queue.NodeQueue;
import queue.Queue;

/*
 *La funzione deve cancellare da Q gli ultimi k elementi lasciando gli altri elementi nello
stesso ordine in cui apparivano inizialmente. Ad esempio se Q=<4,1,7,5,8,2,9> dove 4 è
l’elemento al front allora dopo aver invocato extract(Q,3) si ha Q=<4,1,7,5>.
• Se Q contiene meno di k elementi allora la funzione deve cancellare tutti gli elementi di Q.
• La funzione NON deve usare alcuna struttura dati ausiliaria.
 */

public class ExQueue_12_11 {
		
		public static <E> void extract(Queue<E> Q,int k){
			if(k>=Q.size())
				while(!Q.isEmpty())
					Q.dequeue();
			else {
				for(int i = 0; i<=Q.size()-k; i++)
					Q.enqueue(Q.dequeue());
				for(int i = 0; i<k-1; i++)
					Q.dequeue();
			}
			
		}
		
		public static void main(String[] args){
				NodeQueue<Integer> q = new NodeQueue<Integer>();
				q.enqueue(1);
				q.enqueue(2);
				q.enqueue(3);
				q.enqueue(4);
				q.enqueue(5);
				q.enqueue(6);
				extract(q, 2);
				System.out.print(q);
		}
}
