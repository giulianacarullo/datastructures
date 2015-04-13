package graph;

import position.Vertex;

/** This class specializes DFS to determine whether the graph is connected. */
public class ConnectivityDFS<V, E> extends DFS <V, E, Object, Boolean> {
	//Instance variables
	protected int reached;
	
	//Methods
	protected void setup() { 
		reached = 0; 
	}
	protected void startVisit(Vertex<V> v) { 
		reached++; 
	}
	protected Boolean finalResult(Boolean dfsResult) { 
		return new Boolean(reached == graph.numVertices());
	}
}

