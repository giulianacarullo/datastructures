package graph;

import nodeList.NodePositionList;
import nodeList.PositionList;
import position.Edge;
import position.Position;
import position.Vertex;

/** 
 * Class specializing DFS to find a path between a start vertex and a target 
 * vertex. It assumes the target vertex is passed as the info object to the 
 * execute method. It returns an iterable list of the vertices and edges 
 * comprising the path from start to info. The returned path is empty if 
 * info is unreachable from start.  
 *
 * @author SuperJulietta
 * @version 1.0
 * @param V the vertex type.
 * @param E the edge type.
 * 
 */

@SuppressWarnings("unchecked")
public class FindPathDFS<V, E> extends DFS<V, E, Vertex<V>, Iterable<Position>> {
	//Instance Variables
		private PositionList<Position> path;
		private boolean done;
	
	//Methods
		
		/** 
		 * Setup method to initialize the path. 
		 */
		public void setup() {
			path = new NodePositionList<Position>();
			done = false;
		}
 
		protected void startVisit(Vertex<V> v) {
			path.addLast(v); // add vertex v to path
			if (v == info)
				done = true;
		}
	
		protected void finishVisit(Vertex<V> v) {
			path.remove(path.last());	// remove v from path
			if(!path.isEmpty())		// if v is not the start vertex
				path.remove(path.last());	// remove discovery edge into v from path
		}
	
	
		protected void traverseDiscovery(Edge<E> e, Vertex<V> from) {
			path.addLast(e); // add edge e to the path
		} 
	
		protected boolean isDone() { 
			return done; 
		}
	
		public Iterable<Position> finalResult(Iterable<Position> r) { 
			return path;
		}
}

