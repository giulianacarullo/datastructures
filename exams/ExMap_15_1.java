package exams;

import java.util.Comparator;

import priorityQueue.HeapPriorityQueue;
import priorityQueue.PriorityQueue;
import dictionary.Dictionary;
import entry.Entry;
import graph.FindPathDFS;
import map.Map;

/*
 * Per ciascuna chiave k del dizionario D la funzione controlla se k è presente 
 * nella mappa M e in caso contrario inserisce nella mappa l’entrata di D con valore 
 * più piccolo tra quelle con chiave k. Se M contiene già un’entrata con chiave k 
 * allora questa entrata non deve essere modificata. La funzione deve lasciare 
 * inalterato il contenuto di D.
 */

public class ExMap_15_1 {

	public static <K> void addEntries(Map<K,K> M, Dictionary<K,K> D, Comparator <K> c){
		for(Entry<K, K> entry : D.entries()){
			if(M.get(entry.getKey()) == null){
				Iterable<Entry<K, K>> entries = D.findAll(entry.getKey());
				PriorityQueue<K, K> pq = new HeapPriorityQueue<K, K>(c);
				for(Entry<K,K>e : entries)
					pq.insert(e.getValue(), e.getKey());
				M.put(pq.min().getValue(), pq.min().getKey());
			}
		}
	}
	
}
