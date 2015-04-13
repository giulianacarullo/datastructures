package graph;

import position.Edge;
import position.Position;
import position.Vertex;
import tree.LinkedTree;
import tree.Tree;
/**
 * Creates DFS tree.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <V> the vertex type.
 * @param <E> the edge type.
 */

public class CreateDFSTree<V, E> extends DFS<V, E, Object, Tree<V>> {
	 //Instance Variables
		private  LinkedTree<V> tree;
		private Position<V> COMPONENT; // Connected comp. selector
		private Object key = new Object();
	 //Methods
		
		/** Setup method to initialize the path. */
		protected void setup() {
			tree = new LinkedTree<V>();
			COMPONENT = tree.addRoot(start.element());
			start.put(key, COMPONENT);	
		}
	
		@SuppressWarnings("unchecked")
		protected void traverseDiscovery(Edge<E> e, Vertex<V> from) {
			Vertex<V> edge = graph.opposite(from, e);
			COMPONENT = tree.addChild(edge.element(), (Position<V>)from.get(key));
			edge.put(key, COMPONENT);
		}

		protected Tree<V> result(){
			return tree;
		}
	
}
