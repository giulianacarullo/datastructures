package exams;

import deque.Deque;
import exception.EmptyDequeException;

/*
 * La funzione prende in input il deque D e l’intero i e inverte i primi i elementi 
 * della prima metà di D e gli ultimi i elementi della seconda metà di D. Ad esempio 
 * se d=<a,b,c,d,e,f,g,h,i,l,m> allora dopo aver invocato reverseInTheMiddle(d,4) 
 * si ha d=<d,c,b,a,e,f,g,m,l,i,h>. Il deque D non viene modificato se i è un intero
 * minore o uguale di 1 o se D contiene meno di 2i elementi. Se D è vuoto allora 
 * la funzione lancia l’eccezione EmptyDequeException.
 * FACILE BASTA. -.-
 */
public class ExDeque_11_2_II {
	public static <E> void reverseAtTheEnds(Deque<E> D, int i){
		if(D.isEmpty())
			throw new EmptyDequeException();
		if(i>0 && D.size() >= 2*i){
			
		}
	}
}
