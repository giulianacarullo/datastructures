package exams;

import java.util.Iterator;

import nodeList.NodePositionList;

import map.HashTableMap;
import map.Map;
import graph.AdjacencyListGraph;
import graph.Graph;
import position.Edge;
import position.Vertex;

/*
 * La funzione cancella da G tutti gli archi contenuti in C e tutti quei vertici 
 * su cui incidono solo archi contenuti in C e che quindi rimarrebbero senza 
 * archi incidenti in seguito alla rimozione degli archi di C. I vertici che 
 * già in origine non avevano archi incidenti non devono essere cancellati.
 * Si assuma che tutti gli archi di C siano contenuti in G. Se il grafo non 
 * contiene archi la funzione deve lanciare l’eccezione EmptyEdgeListException.
 */

public class ExGraph_26_2 {
	public static <V,E> void removeEdges(Graph<V,E> G, Iterable<Edge<E>> C){
		Map<Edge<E>, Object> map = new HashTableMap<Edge<E>, Object>();
		boolean flag = true;
		for(Edge<E> edge : C)
			map.put(edge, new Object());
		
		if(G.edges().iterator().hasNext() == false)
			throw new EmptyEdgeListException();
		for(Vertex<V> vertex:G.vertices()){
			Iterator<Edge<E>> it = G.incidentEdges(vertex).iterator();
			if(!it.hasNext())
				continue;
			while(it.hasNext()){
				Edge<E> edge = it.next();
				if(map.get(edge) == null) //c'è un arco incidente sul vertice che non è in C
					flag = false;
				else
					G.removeEdge(edge);
			}
			if(flag)
				G.removeVertex(vertex);
		}
	}
	
	
	public static void main(String[] args){
		Graph<Integer,Integer> graph=new AdjacencyListGraph<Integer,Integer>();
		Vertex<Integer> v1 = graph.insertVertex(1);
		Vertex<Integer> v2 = graph.insertVertex(2);
		Vertex<Integer> v3 = graph.insertVertex(3);
        Vertex<Integer> v4 = graph.insertVertex(4);
        Vertex<Integer> v5 = graph.insertVertex(5);
        Vertex<Integer> v6 = graph.insertVertex(6);
        Vertex<Integer> v7 = graph.insertVertex(7);
        Vertex<Integer> v8 = graph.insertVertex(8);
        Vertex<Integer> v9 = graph.insertVertex(9);
		Edge<Integer> x1221 = graph.insertEdge(v1, v2, 7);
        Edge<Integer> x1441 = graph.insertEdge(v1, v4, 8);
        Edge<Integer> x1661 = graph.insertEdge(v1, v6, 11);
        Edge<Integer> x2442 = graph.insertEdge(v2, v4, 4);
        Edge<Integer> x2332 = graph.insertEdge(v2, v3, 14);
        Edge<Integer> x3443 = graph.insertEdge(v3, v4, 10);
        Edge<Integer> x3553 = graph.insertEdge(v3, v5, 9);
        Edge<Integer> x3993 = graph.insertEdge(v3, v9, 16);
        Edge<Integer> x4664 = graph.insertEdge(v4, v6, 12);
        Edge<Integer> x4554 = graph.insertEdge(v4, v5, 17);
        Edge<Integer> x5775 = graph.insertEdge(v5,v7,3);
        Edge<Integer> x5885 = graph.insertEdge(v5, v8, 1);
        Edge<Integer> x6776 = graph.insertEdge(v6, v7, 6);
        Edge<Integer> x6996 = graph.insertEdge(v6, v9,13);
        Edge<Integer> x7887 = graph.insertEdge(v7, v8, 2);
        Edge<Integer> x7997 = graph.insertEdge(v7, v9, 5);
        Edge<Integer> x8998 = graph.insertEdge(v8, v9, 15);
        System.out.println(graph);
        NodePositionList<Edge<Integer>> C = new NodePositionList<Edge<Integer>>();
        C.addLast(x1661);
        C.addLast(x1441);
        C.addLast(x1221);
        C.addLast(x4664);
        C.addLast(x2332);
        System.out.println(graph.vertices());
        removeEdges(graph, C);
        System.out.println(graph);
        System.out.println(graph.vertices());


        
	}
	
	
	
	
	
	
	
	
	
	
	public static class EmptyEdgeListException extends RuntimeException{
		public EmptyEdgeListException(){
			super("Empty edge list!");
		}
	}
}
