package graph;
import map.HashTableMap;
import position.DecorablePosition;

/**
 * The MyPosition class implements DecorablePosition.
 * 
 * @author SuperJulietta
 * @param <T>
 */
public class MyPosition<T> extends HashTableMap<Object,Object> implements DecorablePosition<T> {
	//Instance variables
		protected T elem;
	//Constructor
		/**
		 * Returns the element stored in this Position.
		 * @return the element stored in this Position.
		 */
		public T element() {
			return elem;
		}
	
	//Method
		/**
		 * Sets the element stored in this Position.
		 * @param o the new element.
		 */
		public void setElement(T o) {
			elem = o;
		}
}

