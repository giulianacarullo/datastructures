package exams;

import exception.EmptyQueueException;
import queue.NodeQueue;
import queue.Queue;

/*
 * Se la sequenza contenuta in q è palindroma la funzione restituisce true; 
 * se la sequenza contenuta in q non è palindroma la funzione restituisce false 
 * dopo aver rimosso da q le coppie di caratteri che rendono la sequenza non 
 * palindroma. Ad esempio se q= <abcdcba> la funzione restituisce true; 
 * se q=<arbecdchbva> la funzione restituisce false e cancella da q i caratteri 
 * evidenziati in neretto in modo che alla fine risulti q=<abcdcba>. 
 * Se la coda è vuota la funzione deve lanciare l’eccezione EmptyQueueException.
 */

public class ExQueue_26_2_II {
	@SuppressWarnings("unchecked")
	public static <E> boolean isPalindrome(Queue<E> q){
		if(q.isEmpty())
			throw new EmptyQueueException();
		boolean palindrome = true;
		int dim = q.size()/2;
		int tmp = q.size();
		E[] array = (E[]) new Object[dim];
		E center = null;
		for(int i = 0; i<dim; i++){
			array[i] = q.dequeue();
		}
		if(tmp % 2 != 0){
			center = q.dequeue();
		}
		for(int i = dim-1; i>=0; i--){
			if(!array[i].equals(q.dequeue())){
				palindrome = false;
				array[i] = null;
			}
		}
		for(int i = 0; i<dim; i++){
			if(array[i] != null)
				q.enqueue(array[i]);
		}
		if(center != null)
			q.enqueue(center);
		
		for(int i = dim-1; i>=0; i--){
			if(array[i] != null)
				q.enqueue(array[i]);
		}
		
		return palindrome;
	}
	
	
	public static void main(String[] args){
		NodeQueue<Character> queue = new NodeQueue<Character>();
		queue.enqueue('a');
		queue.enqueue('b');
		queue.enqueue('c');
		queue.enqueue('d');
		queue.enqueue('e');
		queue.enqueue('d');
		queue.enqueue('c');
		queue.enqueue('b');
		queue.enqueue('a');
		System.out.println(queue);
		System.out.println(isPalindrome(queue));
		System.out.println(queue);
		queue = null;
		//arbecdchbva
		queue = new NodeQueue<Character>();
		queue.enqueue('a');
		queue.enqueue('r');
		queue.enqueue('b');
		queue.enqueue('e');
		queue.enqueue('c');
		queue.enqueue('d');
		queue.enqueue('c');
		queue.enqueue('h');
		queue.enqueue('b');
		queue.enqueue('v');
		queue.enqueue('a');
		System.out.println(queue);
		System.out.println(isPalindrome(queue));
		System.out.println(queue);
	}
}
