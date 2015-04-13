package exams;

import java.util.Comparator;

import comparator.DefaultComparator;
import entry.Entry;
import nodeList.NodePositionList;
import priorityQueue.HeapPriorityQueue;
import priorityQueue.PriorityQueue;

/*
 * La funzione deve restituire una collezione iterabile delle k chiavi più grandi 
 * tra tutte quelle contenute in Q1 e Q2. Si assuma che k è maggiore di 0 e che la 
 * relazione di ordine totale definita sull’insieme delle chiavi di Q1 e Q2 è la 
 * stessa specificata dal comparatore C. Se k>|Q1|+|Q2| allora la funzione deve 
 * restituire una collezione vuota. La funzione deve lasciare inalterate le due 
 * code a priorità.
 */

public class ExPQ_18_6 {

	public static <K,V> Iterable<K> maxElts(PriorityQueue<K,V> Q1,
			PriorityQueue<K,V> Q2, Comparator <K> C, int k){
		NodePositionList<K> outputList = new NodePositionList<K>();
		HeapPriorityQueue<K,V> pq1 = new HeapPriorityQueue<K,V>(new ExamComp<K>());
		HeapPriorityQueue<K,V> pq2 = new HeapPriorityQueue<K,V>(new ExamComp<K>());
		
		if(k<=Q1.size()+Q2.size()){
			while(!Q1.isEmpty()){
				Entry<K, V> x = Q1.removeMin();
				pq1.insert(x.getKey(), x.getValue());
			}
			
			while(!Q2.isEmpty()){
				Entry<K, V> x = Q2.removeMin();
				pq2.insert(x.getKey(), x.getValue());
			}
			
			for(int i = 0; i<k; i++){
				Entry<K, V> min1 = pq1.min();
				Entry<K, V> min2 = pq2.min();
				if(((Comparable<K>)min1.getKey()).compareTo(min2.getKey())>=0){
					pq1.removeMin();
					outputList.addLast(min1.getKey());
					Q1.insert(min1.getKey(), min1.getValue());
				}
				else{
					pq2.removeMin();
					outputList.addLast(min2.getKey());
					Q2.insert(min2.getKey(), min2.getValue());
				}
			}
			while(!pq1.isEmpty()){
				Entry<K, V> entry = pq1.removeMin();
				Q1.insert(entry.getKey(), entry.getValue());
			}
			while(!pq2.isEmpty()){
				Entry<K, V> entry = pq2.removeMin();
				Q2.insert(entry.getKey(), entry.getValue());
			}
		}
		
		return outputList;
		
	}
	
	
	public static void main(String[] args){
		HeapPriorityQueue<Integer, Integer> q1 = new HeapPriorityQueue<Integer, Integer>();
		HeapPriorityQueue<Integer, Integer> q2 = new HeapPriorityQueue<Integer, Integer>();
		q1.insert(1, 1);
		q1.insert(3, 3);
		q1.insert(5, 5);
		q1.insert(2, 2);
		q1.insert(7, 7);
		q2.insert(4, 4);
		q2.insert(7, 7);
		q2.insert(8, 8);
		q2.insert(2, 2);
		q2.insert(10, 10);
		Iterable<Integer> list = maxElts(q1, q2, new DefaultComparator<Integer>(), 4);
		System.out.print(list);
	}
	
	
	
	
	
	
	
	public static class ExamComp<K> implements Comparator<K> {

		@Override
		public int compare(K o1, K o2) {
			return -(((Comparable<K>)o1).compareTo(o2));
		}
		
		
	}
}
