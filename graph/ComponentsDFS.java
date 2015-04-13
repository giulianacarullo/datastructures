package graph;

import position.Vertex;

/** 
 * This class extends DFS to compute the connected components 
 * of a graph. 
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param V the vertex type.
 * @param E the edge type.
 */
public class ComponentsDFS<V, E> extends DFS<V, E, Object, Integer> {
  //Instance Variables
	private Integer compNumber; // Connected component number
	private Object COMPONENT = new Object(); // Connected comp. selector
  
  //Methods
	protected void setup() { 
		compNumber = 1; 
	}
  
	protected void startVisit(Vertex<V> v) { 
		v.put(COMPONENT, compNumber);
	}
  
	protected Integer finalResult(Integer dfsResult) { 
		for (Vertex<V> v : graph.vertices()) // check for any unvisited vertices
			if (v.get(STATUS) == UNVISITED) { 
				compNumber += 1;  // we have found another connected component
				dfsTraversal(v);  // visit all the vertices of this component
			}
		return compNumber;
	}
}
