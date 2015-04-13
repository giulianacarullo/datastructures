package exams;

import java.util.Comparator;

import comparator.DefaultComparator;

import nodeList.NodePositionList;
import entry.Entry;
import exception.EmptyPriorityQueueException;
import priorityQueue.HeapPriorityQueue;
import priorityQueue.PriorityQueue;

/*
 * La funzione prende in input una coda a priorità P, un comparatore C, 
 * dello stesso tipo di quello utilizzato dalla coda a priorità P, e due chiavi 
 * k1 e k2, con k1 più piccola di k2. La funzione deve restituire una collezione 
 * iterabile delle entrate di P con chiave maggiore di k1 e minore di k2.
 * Se almeno una delle due chiavi k1 o k2 non è contenuta in P allora la funzione 
 * deve restituire una collezione iterabile vuota.
 * Nel caso in cui la coda a priorità P sia vuota, la funzione deve lanciare 
 * l’eccezione EmptyPriorityQueueException. La funzione deve lasciare inalterato 
 * il contenuto della coda a priorità P.
 */

//Supponiamo che le chiavi k1 e k1 non si ripetano

public class ExPQ_22_4 {
	
	public static <K,V> Iterable<Entry<K,V>> eltsBetween(PriorityQueue<K,V> P,
			Comparator<K> C,K k1, K k2){
		if(P.isEmpty())
			throw new EmptyPriorityQueueException();
		boolean flag = false;//???
		int howManyKeys = 0;
		PriorityQueue<K, V> pq = new HeapPriorityQueue<K, V>(C);
		NodePositionList<Entry<K,V>> list = new NodePositionList<Entry<K,V>>();
		while(!P.isEmpty()){
			Entry<K,V> entry = P.removeMin();
			pq.insert(entry.getKey(), entry.getValue());
			if(C.compare(k1, entry.getKey()) == 0) {
				flag = true;
				howManyKeys++;
				continue;
			}
			
			if(C.compare(k2, entry.getKey()) == 0) {
				flag = false;
				howManyKeys++;
				continue;
			}
			if(flag)
				list.addLast(entry);
			
		}
		if(howManyKeys != 2)
			while(!list.isEmpty())
				list.remove(list.first());
		while(!pq.isEmpty()){
			Entry<K, V> entry = pq.removeMin();
			P.insert(entry.getKey(), entry.getValue());
		}
		return list;
	}
	
	public static void main(String[] args){
		PriorityQueue<Integer, String> pq = new HeapPriorityQueue<Integer, String>();
		pq.insert(1, "perbacco");
		pq.insert(2, "perdindirindina");
		pq.insert(4, "accipicchia");
		pq.insert(5, "eddai");
		pq.insert(9, "beveie");
		System.out.println(pq);
		System.out.println(eltsBetween(pq, new DefaultComparator<Integer>(), 1, 5));
		System.out.println(pq);
	}
}
