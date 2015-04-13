package exams;

import deque.Deque;
import deque.NodeDeque;
import exception.EmptyDequeException;

/*
 * La funzione prende in input due deque D1 e D2 di uguale dimensione. La funzione 
 * deve aggiungere a D1 gli elementi di D2 in modo tale che gli elementi di D1 
 * siano alternati a quelli di D2 presi in ordine inverso. La funzione deve lasciare 
 * inalterato il contenuto di D2 . Ad esempio se d1=<a,b,c,d,e> e d2=<o,p,q,r,s> 
 * allora dopo aver invocato join(d1,d2) si ha d1=<a,s,b,r,c,q,d,p,e,o> e 
 * d2=<o,p,q,r,s>. Se D1 e D2 sono vuoti allora la funzione lancia l’eccezione 
 * EmptyDequeException. La funzione NON deve usare alcuna struttura dati ausiliaria.
 */

public class ExDeque_11_2 {
	public static <E> void join(Deque<E> D1,Deque<E> D2){
		if(D1.isEmpty())
			throw new EmptyDequeException();
		//E front1 = D1.getFirst();
		//E front2 = D2.getFirst();
		int dim = D1.size();
		for(int i = 0; i<dim; i++){
			D1.addLast(D1.removeFirst());
			E tmp = D2.removeLast();
			D1.addLast(tmp);
			D2.addFirst(tmp);
		}		
	}
	
	
	public static void main(String[] args){
		NodeDeque<String> d1 = new NodeDeque<String>();
		NodeDeque<String> d2 = new NodeDeque<String>();
		d1.addLast("a");
		d1.addLast("b");
		d1.addLast("c");
		d1.addLast("d");
		d1.addLast("e");
		d2.addLast("o");
		d2.addLast("p");
		d2.addLast("q");
		d2.addLast("r");
		d2.addLast("s");
		join(d1, d2);
		System.out.println(d1);
		System.out.println(d2);
	}
}
