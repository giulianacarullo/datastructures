package graph;

import nodeList.NodePositionList;
import position.DecorablePosition;
import position.Edge;
import position.Vertex;



/** 
* Generic BFS traversal of a graph using the template method pattern.
*  
*  @author SuperJulietta
*  @version 1.0
*  @param V, the type for the elements stored at vertices.
*  @param E, the type for the elements stored at edges.
*  @param I, the type for the information object passed to the 
*  execute method.
*  @param R, the type for the result object returned by the BFS.
*/

public class BFS<V, E, I, R> {
	//Instance variables
		protected Graph<V, E> graph;    // The graph being traversed
		protected Vertex<V> start;      // The start vertex for the BFS
		protected I info;               // Information object passed to BFS
		protected R visitResult;        // The result of a recursive traversal call
		protected static Object STATUS = new Object();    // The status attribute
		protected static Object VISITED = new Object();   // Visited value
		protected static Object UNVISITED = new Object(); // Unvisited value
		protected NodePositionList<Vertex<V>>[] levels;
		protected int i;

	//Methods
		/** 
		 * Execute a depth first search traversal on graph g, starting
		 * from a start vertex s, passing in an information object (in) 
		 */

		@SuppressWarnings("unchecked")
		public R execute(Graph<V, E> g, Vertex<V> s, I in) {
			graph = g;
			start = s;
			info = in;
			levels = new NodePositionList[1024]; 
			levels[0] = new NodePositionList<Vertex<V>>();
			for(Vertex<V> v: graph.vertices()) 
				unVisit(v); // mark vertices as unvisited
			for(Edge<E> e: graph.edges()) 
				unVisit(e);      // mark edges as unvisited
			setup();    // perform any necessary setup prior to BFS traversal
			return finalResult(bfsTraversal(start));
		}


		/** 
		 * Recursive template method for a generic BFS traversal. 
		 */
		protected R bfsTraversal(Vertex<V> v) {
			initResult();
			if(isDone())return result();
	    	  startVisit(v);
	    	  if(isDone())return result();
	          visit(v);
	          if(isDone())return result();
	          finishVisit(v);
	          if(isDone())return result();
			levels[0].addLast(v); 
			Vertex<V> vertex = null;
			while(true){
				levels[i+1] = new NodePositionList<Vertex<V>>();
				while(!levels[i].isEmpty()){
					vertex = levels[i].remove(levels[i].first());
					initResult();
					if(!isDone())
						startVisit(vertex);
					 if(isDone())return result();
			          visit(v);
					 if(isDone())return result();
			          finishVisit(v);
					if (isDone()) break;
					for (Edge<E> e: graph.incidentEdges(vertex)) {
						if (!isVisited(e)) {// found an unexplored edge, explore it
							visit(e);
							Vertex<V> w = graph.opposite(vertex, e);
							if (!isVisited(w)) {
								// w is unexplored, this is a discovery edge
								traverseDiscovery(e, vertex);
								if (isDone()) break;
								levels[i+1].addLast(w);
								if(!isDone())
									visit(w);
							}
						}
						else{
							// w is explored, this is a back edge
							traverseCross(e, vertex);
							if (isDone()) break;
						}
						//?if(!isDone())
						//?	finishVisit(vertex);
					}
				}
				if(levels[i+1].isEmpty())
					break;
				i++;
	  			} 
		  		return result();
		}
	  
		//begin decorations
		/** Mark a position (vertex or edge) as visited. */
		protected void visit(DecorablePosition<?> p) { p.put(STATUS, VISITED); }	
		/** Mark a position (vertex or edge) as unvisited. */
		protected void unVisit(DecorablePosition<?> p) { p.put(STATUS, UNVISITED); }
		/** Test if a position (vertex or edge) has been visited. */
		protected boolean isVisited(DecorablePosition<?> p) {
			return (p.get(STATUS) == VISITED);
		}


		// Auxiliary methods (all initially null) for specializing a generic BFS
		/** Invoked before BFS execution. */
		protected void setup() {}
		/** Initializes result (called first, once per vertex visited). */
		protected void initResult() {}
		/** Called when we encounter a vertex (v). */
		protected void startVisit(Vertex<V> v) {}
		/** Called after we finish the visit for a vertex (v). */
		protected void finishVisit(Vertex<V> v) {}
		/** Called when we traverse a discovery edge (e) from a vertex (from). */
		protected void traverseDiscovery(Edge<E> e, Vertex<V> from) {}
		/** Called when we traverse a cross edge (e) from a vertex (from). */
		protected void traverseCross(Edge<E> e, Vertex<V> from) {}
		/** Determines whether the traversal is done early. */
		protected boolean isDone() { return false; /* default value */ }
		/** Returns a result of a visit (if needed). */	
		protected R result() { return null; /* default value */ }
		/** Returns the final result of the BFS execute method. */
		protected R finalResult(R r) { return r; /* default value */ }

}
