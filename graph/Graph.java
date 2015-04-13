package graph;

import position.Edge;
import position.InvalidPositionException;
import position.Vertex;

/**
 * 
 * A mathematical graph-theory graph object G(V,E) contains a set V of
 * vertices and a set  E of edges. 
 * Each edge e=(v1,v2) in E connects vertex v1 to vertex v2. 
 * (for more information see 
 * <A HREF="http://en.wikipedia.org/wiki/Graph_theory">graph</A> )
 *
 * @author SuperJulietta
 * @param <V> the vertex type.
 * @param <E> the edge type.
 */

public interface Graph<V, E> {
	 public int numVertices();
	 public int numEdges();
	 public Iterable<Vertex<V>> vertices();
	 public Iterable<Edge<E>> edges();
	 public V replace(Vertex<V> p, V o) throws InvalidPositionException;
	 public E replace(Edge<E> p, E o) throws InvalidPositionException;
	 public Iterable<Edge<E>> incidentEdges(Vertex<V> v)throws InvalidPositionException;
	 public Vertex<V>[] endVertices(Edge<E> e) throws InvalidPositionException;
	 public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidPositionException;
	 public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidPositionException;
	 public Vertex<V> insertVertex(V o);
	 public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o) throws InvalidPositionException;
	 public V removeVertex(Vertex<V> v) throws InvalidPositionException;
	 public E removeEdge(Edge<E> e) throws InvalidPositionException;
}
