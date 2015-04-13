package exams;

import java.util.Comparator;
import java.util.Iterator;

import comparator.DefaultComparator;

import position.Position;
import priorityQueue.HeapPriorityQueue;

import nodeList.NodePositionList;
import dictionary.Dictionary;
import dictionary.LinearProbingHashTable;
import entry.Entry;


/*
 * La funzione deve restituire una collezione iterabile contenente tutte le entry 
 * di D ordinate rispetto ai valori delle entrate. La relazione di ordine totale 
 * definita sull’insieme dei valori delle entrate di D è la stessa specificata 
 * dal comparatore c. La funzione deve lasciare inalterato il contenuto di D.
 * Se D è vuoto allora la funzione deve restituire una collezione vuota.
 * no sd ausiliarie
 */

public class ExDict_20_4 {
	public static<K,V> Iterable<Entry<K,V>>sortValues(Dictionary <K,V> D,Comparator<V> c){
		NodePositionList<Entry<K,V>> list = new NodePositionList<Entry<K,V>>();
		if(!D.isEmpty()){
			Iterable<Entry<K, V>> entries = D.entries();
			for(Entry<K,V>entry: entries){
				if(list.isEmpty())
					list.addFirst(entry);
				else if(c.compare(entry.getValue(), list.last().element().getValue())>0)
					list.addLast(entry);
				else {
					Position<Entry<K,V>> current = list.first();
					while(c.compare(entry.getValue(), current.element().getValue())>0)
						current = list.next(current);
					list.addBefore(current, entry);
				}
			}
		}
		return list;
	}
	
	
	public static void main(String[] args){
		LinearProbingHashTable<Integer, Integer> dic = new LinearProbingHashTable<Integer, Integer>();
		dic.insert(1, 1);
		dic.insert(2, 2);
		dic.insert(3, 3);
		dic.insert(4, 4);
		dic.insert(5, 5);
		Iterable<Entry<Integer, Integer>> list = sortValues(dic, new DefaultComparator<Integer>());
		System.out.println(list);
	}
}
