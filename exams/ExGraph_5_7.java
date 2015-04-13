package exams;

import exception.EmptyListException;
import nodeList.NodePositionList;
import nodeList.PositionList;
import graph.AdjacencyListGraph;
import graph.Graph;
import position.*;

/*
 * La funzione prende in input un grafo non direzionato G ed una lista LC di vertici
 * appartenenti al grafo e restituisce true se e solo se i vertici in LC, 
 * presi nell’ordine in cui appaiono in LC, formano un percorso (dal primo 
 * all’ultimo vertice della lista). Se la lista è vuota la funzione deve lanciare 
 * l’eccezione EmptyListException. 
 */
public class ExGraph_5_7 {
	
	public static <V,E> boolean IsPath(Graph<V,E> G, PositionList<Vertex<V>> LC){
		if(LC.isEmpty())
			throw new EmptyListException();
		boolean flag = false;
		Position<Vertex<V>> prev = LC.first();
		for(int i = 0; i<LC.size()-1; i++){
			Position<Vertex<V>> next = LC.next(prev);
			Iterable<Edge<E>> edges = G.incidentEdges(prev.element());
			for(Edge<E> edge:edges){
				flag = false;
				Vertex<V> opposite = G.opposite(prev.element(), edge);
				if(opposite == next.element()) {
					flag = true;
					break;
				}
			}
			if(flag == false)
				return false;
			prev = next;
		}
		return true;	
	}
	
	public static void main(String[] args){
		AdjacencyListGraph<Integer, Integer> graph = new AdjacencyListGraph<Integer, Integer>();
		Vertex<Integer> one = graph.insertVertex(1);
		Vertex<Integer> two = graph.insertVertex(2);
		Vertex<Integer> three = graph.insertVertex(3);
		Vertex<Integer> four = graph.insertVertex(4);
		graph.insertEdge(one, two, 1);
		graph.insertEdge(one, three, 1);
		graph.insertEdge(one, four, 1);
		graph.insertEdge(two, three, 1);
		graph.insertEdge(two, four, 1);
		//graph.insertEdge(three, four, 1);
		NodePositionList<Vertex<Integer>> list = new NodePositionList<Vertex<Integer>>();
		list.addLast(one);
		list.addLast(two);
		list.addLast(three);
		list.addLast(four);
		System.out.println(IsPath(graph, list));

	}
	
}
