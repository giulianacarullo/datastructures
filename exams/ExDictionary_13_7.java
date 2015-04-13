package exams;

import java.util.Iterator;

import nodeList.NodePositionList;
import nodeList.PositionList;
import dictionary.Dictionary;
import dictionary.LinearProbingHashTable;
import entry.Entry;
import entry.HashEntry;
import exception.InvalidEntryException;
import exception.InvalidKeyException;

public class ExDictionary_13_7{
	
	public static void main(String[] args){
		ExamLinearProbingHashTable<Integer,Integer> D = new ExamLinearProbingHashTable<Integer,Integer>(100);
		
		
		for(int i=100;i<=400;i=i+20)
		     D.insert(i, i).getKey();
		
		
		
		System.out.println("I test:");
		for(int i=100;i<=400;i=i+40){
		    Entry<Integer,Integer> ent=D.find(i);
		    if(D.checkAndMove(ent))
		     System.out.println("L'entrata "+ ent + " si trova gia` nella cella con indice hashValue(" + ent.getKey() + ")=" +  D.hashValue(i)); 
		     
		    else 
		    	 System.out.println("L'entrata "+ ent + " viene spostata nella cella con indice hashValue(" + ent.getKey() + ")=" +  D.hashValue(i)); 
		    	 
		}
		
		
		
		System.out.println("\n\nII test:");
		for(int i=100;i<=400;i=i+40){
			Entry<Integer,Integer> ent=D.find(i);
		    if(D.checkAndMove(ent))
		     System.out.println("L'entrata "+ ent + " si trova gia` nella cella con indice hashValue(" + ent.getKey() + ")=" +  D.hashValue(i)); 
		     
		    else
		    	 System.out.println("L'entrata "+ ent + " viene spostata nella cella con indice hashValue(" + ent.getKey() + ")=" +  D.hashValue(i)); 
		    	
		   
		}
	      
	 
	
	
	
	}	
	
	
	
	
	public static class ExamLinearProbingHashTable<K,V> extends LinearProbingHashTable<K, V> implements Dictionary<K, V>{

		protected int c; 
		public ExamLinearProbingHashTable(int cap){
			super(cap);
		    c=cap;
		}
			
	
			
		
		public int hashValue(K key){
			 return (Integer)key%c;
		 }
			/*
			 * Il metodo restituisce true se l’indice dalla cella contenente 
			 * entry è uguale al valore hash della sua chiave. In caso contrario 
			 * la funzione restituisce false dopo aver spostato entry nella cella 
			 * con indice uguale al valore hash della sua chiave. Se questa cella 
			 * era piena allora la sua vecchia entrata dovrà essere spostata in un’altra 
			 * cella (opportunamente scelta). Si noti che il metodo insert non può 
			 * essere usato per spostare un’entrata in quanto insert prende in input 
			 * una chiave e un valore e inserisce una nuova entrata con quella chiave
			 *  e con quel valore. Evitare di effettuare operazioni inutili.
			 */
			public boolean checkAndMove(Entry <K,V> entry){
				checkEntry(entry);
				int hash = hashValue(entry.getKey());
				int i = hash;
				int j = i;
				do {
					Entry<K,V> e = bucket[i];
					if( e ==null)
						break;
					if(e.equals(entry))
						break;
					i = (i+1) % capacity;
				}
				while(i!=j);
				if(hash == i)
					return true;
				Entry<K,V> en = bucket[hash];
		
				bucket[hash] = bucket [i];
				bucket[i] = AVAILABLE;
				if(en != null && en!=AVAILABLE){
					if(capacity <2*size())
						rehash();
					i = hash;
					j = i;
					do {
						Entry<K,V> e = bucket[i];
						if(e == null || e == AVAILABLE){
							bucket[i] = en;
							numEntries++;
							break;
						}
						i = (i+1) % capacity;
					} while(i != j);
				}
				return false;
			}
	}			
}
