package exams;



import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference.Elements;

import partition.ListPartition;

public class ExListPartition_26_2_II {

	
	
	
	public static class Exam<E> extends ListPartition<E>{
		
		
		/*
		 * La funzione cancella dagli insiemi della partizione gli elementi 
		 * contenuti in C. Gli insiemi rimasti vuoti dopo la cancellazione 
		 * degli elementi di C devono essere rimossi dalla partizione. Si noti 
		 * che C potrebbe contenere elementi che non appartengono ad alcun 
		 * insieme della partizione. Il metodo removeElts NON deve invocare alcun 
		 * metodo di ListPartition. Aiuto: Per cancellare un elemento dall’insieme 
		 * si può usare il metodo remove (fornito dalla docente). Il metodo 
		 * remove va inserito nell’implementazione di Set usata da ListPartition 
		 * (sempre che Set sia stato implementato mediante una lista).
		 */
		
		public void removeElts(Iterable<E> C){
			for(E el:C){
			}
		}
		
	}
}
