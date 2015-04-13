package graph;

import java.util.Iterator;

import nodeList.PositionList;
import position.Edge;
import position.Position;
import position.Vertex;
import tree.Tree;

public class TestingGraph {
	public static void main(String[] args){
		System.out.println("Testing graph");
		Graph<Integer, Integer> graph = new AdjacencyListGraph<Integer, Integer>();
		Vertex<Integer> v1 = graph.insertVertex(5);
		Vertex<Integer> v2 = graph.insertVertex(6);
		Vertex<Integer> v3 =graph.insertVertex(7);
		Vertex<Integer> v4 =graph.insertVertex(8);
		Vertex<Integer> v5 =graph.insertVertex(10);
		Vertex<Integer> v6 =graph.insertVertex(13);
		graph.insertEdge(v1, v2, 1);
		graph.insertEdge(v1, v3, 2);
		graph.insertEdge(v2, v3, 4);
		graph.insertEdge(v3, v4, 7);
		graph.insertEdge(v3, v5, 2);
		graph.insertEdge(v3, v6, 1);
		for(Vertex<Integer> el: graph.vertices())
			for(Edge<Integer>edge: graph.incidentEdges(el))
				System.out.println(el + " - "+graph.opposite(el, edge));

		
		System.out.println("__________________");
		System.out.println("Testing Kruskal:");
		bigGraph();
		}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void bigGraph(){
		//BIG GRAPH
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
        System.out.println("Testing incidents edges");
        System.out.println("Incident edges of v1 - "+graph.incidentEdges(v1));
        System.out.println("Incident edges of v2 - "+graph.incidentEdges(v2));
        System.out.println("Incident edges of v3 - "+graph.incidentEdges(v3));
        System.out.println("Incident edges of v4 - "+graph.incidentEdges(v4));
        System.out.println("Incident edges of v5 - "+graph.incidentEdges(v5));
        System.out.println("Incident edges of v6 - "+graph.incidentEdges(v6));
        System.out.println("Incident edges of v7 - "+graph.incidentEdges(v7));
        System.out.println("Incident edges of v8 - "+graph.incidentEdges(v8));
        System.out.println("Incident edges of v9 - "+graph.incidentEdges(v9));

        Kruskal<Integer,Integer> krusky=new Kruskal();
        PositionList<Edge<Integer>> edges = krusky.execute(graph, new Object());
		System.out.println("MST: "+edges);
		System.out.println("___________________");
		
		System.out.println("Testing createDFSTree");
		CreateDFSTree<Integer,Integer> dfsTree=new CreateDFSTree<Integer, Integer>();
	    Tree<Integer> tree = dfsTree.execute(graph, v1, null);
	    System.out.println("size dfsTree= "+tree.size());
	    System.out.println("num edges= "+graph.numEdges());
	    for (Position<Integer> x:tree.positions())
        {
        	if (tree.isRoot(x)) System.out.println("i am the root "+x.element() );
        	else 
        	{
        		System.out.println("i am "+x.element()+" child of "+tree.parent(x).element());
        	}
        }
	
		
		
		
		System.out.println("Testing Dijkstra");
		Dijkstra<Integer, Integer> dks = new Dijkstra<Integer, Integer>();
		Iterator<Vertex<Integer>> it = graph.vertices().iterator();
		Object w = new Object();
		while(it.hasNext()){
			Vertex<Integer> v = it.next();
			for (Edge<Integer> e: graph.incidentEdges(v)) {
				e.put(w, e.element());
			}
		}
		dks.execute(graph, v1, w);
		System.out.println("v1 raggiungibile "+dks.getDist(v1));
		System.out.println("v2 raggiungibile "+dks.getDist(v2));
		System.out.println("v3 raggiungibile "+dks.getDist(v3));
		System.out.println("v4 raggiungibile "+dks.getDist(v4));
		System.out.println("v5 raggiungibile "+dks.getDist(v5));
		System.out.println("v6 raggiungibile "+dks.getDist(v6));
		System.out.println("v7 raggiungibile "+dks.getDist(v7));
		System.out.println("v8 raggiungibile "+dks.getDist(v8));
		System.out.println("v9 raggiungibile "+dks.getDist(v9));
	
		System.out.println("_____________________");
		System.out.println("Testing LayerBFS:");
		LayerBFS<Integer, Integer> layer = new LayerBFS<Integer, Integer>();
		layer.execute(graph, v1, 1);
		Iterator<Vertex<Integer>> levels = layer.result().iterator();
		while(levels.hasNext())
			System.out.println(levels.next());
	}

}
