package graph;

import position.Edge;
import position.Vertex;

/**
 * 
 * A directed graph. 
 * 
 * @author SuperJulietta
 * @param <V>
 * @param <E>
 */
public interface DirectedGraph<V, E> extends
Graph<V,E>{
	 boolean isDirected(Edge<E> e);
	void insertDirectedEdge(Vertex<V> u, Vertex<V>v,V o);
}

