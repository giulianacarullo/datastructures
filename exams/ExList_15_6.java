package exams;

import position.Position;
import priorityQueue.HeapPriorityQueue;
import priorityQueue.PriorityQueue;
import entry.Entry;
import exception.EmptyDequeException;
import nodeList.NodePositionList;
import nodeList.PositionList;

/*
 * La funzione deve cancellare dalla lista L i k elementi più piccoli e restituire 
 * una collezione iterabile contenente gli elementi cancellati. Se la lista L 
 * è vuota la funzione deve lanciare l’eccezione EmptyListException. Se la lista L 
 * contiene un numero di elementi minore o uguale di k, la funzione deve cancellare 
 * tutti gli elementi di L e quindi restituire una collezione contenente tutti 
 * gli elementi di L.
 */

public class ExList_15_6 {
	public static Iterable<String> smallestElements(PositionList<String> L,int k){
		if(L.isEmpty())
			throw new EmptyDequeException();
		NodePositionList<String> output = new NodePositionList<String>();
		PriorityQueue<String, Position<String>> pq = new HeapPriorityQueue<String, Position<String>>();
		Position<String> pos = L.first();
		pq.insert(pos.element(), pos);
		for(int i = 0; i<L.size()-1; i++){
			pos = L.next(pos);
			pq.insert(pos.element(), pos);
		}
		int min;
		if(k>L.size())
			min = L.size();
		else
			min = k;
		for(int i = 0; i<min; i++){
			Entry<String, Position<String>> x = pq.removeMin();
			output.addLast(x.getKey());
			L.remove(x.getValue());
		}
		return output;
	}
	
	public static void main(String[] args){
		NodePositionList<String> list = new NodePositionList<String>();
		list.addLast("a");
		list.addLast("b");
		list.addLast("c");
		list.addLast("d");
		list.addLast("e");
		System.out.println(smallestElements(list, 2));
		System.out.println(list);

	}
}
