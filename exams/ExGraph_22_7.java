package exams;

import java.util.Iterator;

import map.HashTableMap;
import graph.AdjacencyListGraph;
import graph.Graph;
import position.Edge;
import position.Vertex;


/*
 * La funzione prende in input un grafo G ed una collezione iterabile EC di archi 
 * appartenenti a G e restituisce true se e solo se per ogni vertice v di G c’è un
 * arco in EC che incide su v. Si assuma che l’ insieme dei vertici di G non sia
 * vuoto.
 */

public class ExGraph_22_7 {
	public static <V,E> boolean isAnEdgeCover(Graph<V,E> G,Iterable <Edge<E>> EC ){
		boolean founded = false;
		HashTableMap<Edge<E>, Object> map = new HashTableMap<Edge<E>, Object>();
		Iterator<Edge<E>> it = EC.iterator();
		while(it.hasNext())
			map.put(it.next(), new Object());
		for(Vertex<V> vertex : G.vertices() ){
			founded = false;
			Iterator<Edge<E>> edges = G.incidentEdges(vertex).iterator();
			while(edges.hasNext()){
				if(map.get(edges.next()) != null){
					founded = true;
					break;
				}
			}
			if(founded != true)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		AdjacencyListGraph<Integer, Integer> graph = new AdjacencyListGraph<Integer, Integer>();
	}
}
