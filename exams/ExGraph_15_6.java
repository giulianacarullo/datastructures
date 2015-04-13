package exams;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import map.HashTableMap;
import map.Map;
import nodeList.NodePositionList;
import position.Edge;
import position.Vertex;
import graph.AdjacencyListGraph;
import graph.Graph;

public class ExGraph_15_6 {
	
	
	
	public static void main (String[]args){
		Exam<Integer,Integer> graph=new Exam<Integer,Integer>();
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
        System.out.println(graph.removeClosestVertices(v1));
        
		
	}
	
	
	public static class Exam<E, V> extends AdjacencyListGraph<V, E> {
		
		/*
		 * Il metodo deve cancellare dal grafo tutti i vertici (diversi da v) 
		 * che sono a distanza minore o uguale di 2 da v. In altre parole, 
		 * il metodo deve cancellare tutti i vertici adiacenti a v e tutti i 
		 * vertici che sono adiacenti a qualche vertice adiacente a v. Al termine 
		 * la funzione deve restituire una collezione iterabile dei vertici 
		 * cancellati. Si assuma che il vertice v appartenga al grafo.
		 */
		
		public Iterable<Vertex<V>> removeClosestVertices(Vertex<V> v){
			NodePositionList<Vertex<V>> list = new NodePositionList<Vertex<V>>();
			Map<Vertex<V>, Object> map = new HashTableMap<Vertex<V>, Object>();
			Iterable<Edge<E>> edges = this.incidentEdges(v);
			for(Edge<E> edge : edges){
				Vertex<V> opposite = opposite(v, edge);
				for(Edge<E> e : this.incidentEdges(opposite)){
					Vertex<V> op = opposite(opposite, e);
					if(op != v) {
						map.put(op, new Object());
					}
				}
				map.put(opposite, new Object());
			}
			for(Vertex<V> vertex: map.keys()){
				this.removeVertex(vertex);
				list.addLast(vertex);
			}
			return list;
		}
	}
}
