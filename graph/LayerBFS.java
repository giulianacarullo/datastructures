package graph;

import nodeList.NodePositionList;
import position.Vertex;

public class LayerBFS<V, E>  extends BFS<V, E, Integer, Iterable<Vertex<V>>> {
	//private boolean done = false;
	private NodePositionList<Vertex<V>> level;
	
	public Iterable<Vertex<V>> execute(Graph<V, E> g, Vertex<V> s, Integer i){
		level = new NodePositionList<Vertex<V>>();
		return super.execute(g, s, i);
	}
	
	protected void startVisit(Vertex<V> v) {
		if(i == info)
			level.addLast(v);
	}
	protected Iterable<Vertex<V>> result() { 
		return  level;
	}
}
