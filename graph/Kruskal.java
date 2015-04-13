package graph;

import comparator.DefaultComparator;
import nodeList.NodePositionList;
import nodeList.PositionList;
import partition.ListPartition;
import position.Edge;
import position.Vertex;
import priorityQueue.HeapPriorityQueue;
import priorityQueue.PriorityQueue;

/**
 * O(mlogn) Kruskal algorithm implementation(Union-find-based)
 */

public class Kruskal<V,E> {
	/** Input graph. */
	protected Graph<V, E> graph;
	/** Decoration key for edge weights */
	@SuppressWarnings("unused")
	private Object WEIGHT;
	/** Auxiliary priority queue. */
	protected PriorityQueue<E, Edge<E>> Q;
	/*Final Minimum Spanning Tree*/
	//protected LinkedTree<Vertex<V>> mst;
	protected  ListPartition<Vertex<V>> MST;
	
	
	
	/** Executes Kruskal's algorithm.
	 * @param g Input graph
	 * @param w Weight decoration object */
	@SuppressWarnings("unchecked")
	public  PositionList<Edge<E>> execute(Graph<V, E> g, Object w) {
		graph = g;
		WEIGHT = w;
		DefaultComparator dc = new DefaultComparator();
		Q = new HeapPriorityQueue<E, Edge<E>>(dc);
		MST = new ListPartition<Vertex<V>>();
		return kruskalVisit();
	}
	
	/** 
	 * The actual execution of Kruskal's algorithm.
	 */
	@SuppressWarnings("unused")
	protected PositionList<Edge<E>> kruskalVisit() {
			Vertex<V>[] endV;
			PositionList<Edge<E>> edges = new NodePositionList<Edge<E>>();
			for(Vertex<V> vertex: graph.vertices())
				MST.makeSet(vertex);
			for(Edge<E> edge: graph.edges())
				Q.insert(edge.element() , edge);
				
			while(!Q.isEmpty() && edges.size() < graph.numVertices()-1){
				Edge<E> edge = Q.removeMin().getValue();
				System.out.println("edge: "+ edge);
				endV = graph.endVertices(edge);
				Vertex<V> v1= endV[0];
				Vertex<V> v2= endV[1];
				if(MST.find(endV[0]) != MST.find(endV[1])){
					edges.addLast(edge);
					MST.union(MST.find(endV[0]),MST.find(endV[1]));
				}
			}	
		return edges;
	}
		
}