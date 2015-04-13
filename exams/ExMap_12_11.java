package exams;

import java.util.Comparator;

import comparator.DefaultComparator;

import priorityQueue.HeapAdaptablePriorityQueue;
import priorityQueue.HeapPriorityQueue;
import priorityQueue.PriorityQueue;

import nodeList.NodePositionList;
import map.HashTableMap;
import map.Map;
import entry.Entry;

/*
 * La funzione deve restituire una collezione iterabile contente tutte le entry 
 * della mappa M ordinate rispetto al valore delle chiavi. La relazione di ordine 
 * totale definita sull’insieme delle chiavi di M è specificata dal comparatore c.
 * La funzione deve lasciare inalterato il contenuto di M. Se M è vuota allora 
 * la funzione deve restituire una collezione vuota.
 */

public class ExMap_12_11 {
	public static <K,V> Iterable <Entry<K,V>> sortKeys(Map<K,V> M, Comparator <K> c){
		NodePositionList<Entry<K, V>> list = new NodePositionList<Entry<K,V>>();
		if(!M.isEmpty()){
			Iterable<K> keys = M.keys();
			PriorityQueue<K, V> pq = new HeapPriorityQueue<K, V>(c);
			for(K key : keys){
				V value = M.get(key);
				pq.insert(key, value);
			}
			while(!pq.isEmpty())
				list.addLast(pq.removeMin());
		}
		return list;
	}
	
	public static void main(String[] args){
		Map<Integer, String> m = new HashTableMap<Integer, String>();
		m.put(1, "cacca");
		m.put(3, "cacca");
		m.put(2, "cacca");
		NodePositionList<Entry<Integer, String>> list = (NodePositionList<Entry<Integer, String>>) sortKeys(m, new DefaultComparator<Integer>());
		System.out.print(list);
	}
}
