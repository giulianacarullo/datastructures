package exams;

import dictionary.Dictionary;
import entry.Entry;
import map.HashTableMap;
import map.Map;


/*
 * La funzione deve restituire una mappa con chiavi di tipo K e valori di tipo 
 * Integer. Per ciascuna chiave k di M, la funzione deve cancellare da D tutte le 
 * entrate con chiave uguale a k e inserire nella mappa output l’entrata (k,m), dove
 * m è il numero di volte in cui k appare in D.
 * La funzione deve lasciare inalterato il contenuto di M. Se D è vuoto o non 
 * contiene nessuna delle chiavi di M, allora la funzione deve restituire una
 * mappa vuota.
 */

public class ExMap_18_9 {
	public static <K,V> Map<K,Integer> subtract(Dictionary<K,V> D, Map<K,V> M){
		Map<K, Integer> map = new HashTableMap<K, Integer>();
		for(K key:M.keys()){
			int counter = 0;
			for(Entry<K,V> entry: D.findAll(key)){
				D.remove(entry);
				counter++;
			}
			map.put(key, counter);
		}
		return map;
		
	}
}
