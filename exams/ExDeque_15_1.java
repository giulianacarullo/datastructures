package exams;

import deque.Deque;
import deque.NodeDeque;
import exception.EmptyDequeException;


/* 
 * La funzione restituisce true se la somma della prima metà degli elementi di D 
 * è uguale alla somma dell’ultima metà degli elementi di D. In caso contrario 
 * la funzione restituisce false. Nel caso in cui il numero di elementi di D sia 
 * dispari l’elemento centrale non viene preso in considerazione. Ad esempio, 
 * la funzione restituisce false se D=<2,1,8,3,5,2,6> e true se D=<4,1,8,3,5,2,6> . 
 * Se D è vuoto la funzione deve lanciare l’eccezione EmptyDequeException. 
 * La funzione deve lasciare inalterato il contenuto di D.
 * La funzione checkSums NON deve usare alcuna struttura dati ausiliaria.
 */

public class ExDeque_15_1 {
	public static boolean checkSums(Deque<Integer> D){
		if(D.isEmpty())
			throw new EmptyDequeException();
		int size = D.size();
		int sum1 = 0;
		int sum2 = 0;
			for(int i = 0; i<size/2; i++){
				Integer tmp = D.removeFirst();
				sum1+=tmp;

				D.addLast(tmp);
			}
			if(size % 2 != 0)
				D.addLast(D.removeFirst());
			for(int i = 0; i<size/2; i++){
				Integer tmp = D.removeFirst();
				sum2+=tmp;
				D.addLast(tmp);
			}
		return sum1 == sum2;
	}
	
	public static void main(String[] args){
		Deque<Integer> deque = new NodeDeque<Integer>();
		//D=<2,1,8,3,5,2,6> false
		deque.addLast(2);
		deque.addLast(1);
		deque.addLast(8);
		deque.addLast(3);
		deque.addLast(5);
		deque.addLast(2);
		deque.addLast(6);
		System.out.println(checkSums(deque));
		//D=<4,1,8,3,5,2,6> true
		Deque<Integer> d = new NodeDeque<Integer>();
		d.addLast(4);
		d.addLast(1);
		d.addLast(8);
		d.addLast(3);
		d.addLast(5);
		d.addLast(2);
		d.addLast(6);
		System.out.println(checkSums(d));

	}
}
