package graph;

import position.DecorablePosition;
import position.Edge;
import position.Vertex;


/** 
* Generic DFS traversal of a graph using the template method pattern.
*   @param V, the type for the elements stored at vertices
*   @param E, the type for the elements stored at edges
*   @param I, the type for the information object passed to the execute method
*   @param R, the type for the result object returned by the DFS
*/

public class DFS<V, E, I, R> {

	//Instance variables
		protected Graph<V, E> graph;    // The graph being traversed
		protected Vertex<V> start;      // The start vertex for the DFS
		protected I info;               // Information object passed to DFS
		protected R visitResult;        // The result of a recursive traversal call
		protected static Object STATUS = new Object();    // The status attribute
		protected static Object VISITED = new Object();   // Visited value
		protected static Object UNVISITED = new Object(); // Unvisited value


	//Methods
		/** 
		 * Execute a depth first search traversal on graph g, starting
		 * from a start vertex s, passing in an information object (in) 
		 */

		public R execute(Graph<V, E> g, Vertex<V> s, I in) {
			graph = g;
			start = s;
			info = in;
			for(Vertex<V> v: graph.vertices()) 
				unVisit(v); // mark vertices as unvisited
			for(Edge<E> e: graph.edges()) 
				unVisit(e);      // mark edges as unvisited
			setup();           // perform any necessary setup prior to DFS traversal
			return finalResult(dfsTraversal(start));
		}
		
		/** 
		 * Recursive template method for a generic DFS traversal.  
		 */
		protected R dfsTraversal(Vertex<V> v) {
			initResult();
			if (!isDone())
				startVisit(v);
			if (!isDone()) {
				visit(v);
				for (Edge<E> e: graph.incidentEdges(v)) {
					if (!isVisited(e)) {
						// found an unexplored edge, explore it
						visit(e);
						Vertex<V> w = graph.opposite(v, e);
						if (!isVisited(w)) {
							// w is unexplored, this is a discovery edge
							traverseDiscovery(e, v);
							if (isDone()) break;
							visitResult = dfsTraversal(w); // get result from DFS-tree child
							if (isDone()) break;
						}
						else {
							// w is explored, this is a back edge
							traverseBack(e, v);
							if (isDone()) break;
						}
					}
				}
			}
			if(!isDone())
				finishVisit(v);
			return result();
		}


		/** 
		 * Mark a position (vertex or edge) as visited. 
		 */
		protected void visit(DecorablePosition<?> p) { 
			p.put(STATUS, VISITED); 
		}

		/** 
		 * Mark a position (vertex or edge) as unvisited. 
		 */
		protected void unVisit(DecorablePosition<?> p) { 
			p.put(STATUS, UNVISITED); 
		}

		/** 
		 * Test if a position (vertex or edge) has been visited. 
		 */
		protected boolean isVisited(DecorablePosition<?> p) {
			return (p.get(STATUS) == VISITED);
		}


		// Auxiliary methods (all initially null) for specializing a generic DFS

		/** 
		 * Invoked before DFS execution. 
		 */
		protected void setup() {}
		/** Initializes result (called first, once per vertex visited). */
		protected void initResult() {}
		/** Called when we encounter a vertex (v). */
		protected void startVisit(Vertex<V> v) {}
		/** Called after we finish the visit for a vertex (v). */
		protected void finishVisit(Vertex<V> v) {}
		/** Called when we traverse a discovery edge (e) from a vertex (from). */
		protected void traverseDiscovery(Edge<E> e, Vertex<V> from) {}
		/** Called when we traverse a back edge (e) from a vertex (from). */
		protected void traverseBack(Edge<E> e, Vertex<V> from) {}
		/** Determines whether the traversal is done early. */
		protected boolean isDone() { return false; /* default value */ }
		/** Returns a result of a visit (if needed). */
		protected R result() { return null; /* default value */ }
		/** Returns the final result of the DFS execute method. */
		protected R finalResult(R r) { return r; /* default value */ }

}
