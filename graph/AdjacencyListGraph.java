package graph;

import java.util.Iterator;
import position.InvalidPositionException;
import nodeList.NodePositionList;
import nodeList.PositionList;
import position.Edge;
import position.Position;
import position.Vertex;

/**
 * A PositionList-based graph implementations.
 * @author SuperJulietta
 *
 * @param <V> the vertex type.
 * @param <E> the edge type.
 */

public class AdjacencyListGraph<V,E> implements Graph<V,E> {
	//Instance Variables
		protected NodePositionList<Vertex<V>> VList;
		protected NodePositionList<Edge<E>> EList; 
	
	//Constructor
		/**
		 * Constructs an empty Graph. 
		 */
		public AdjacencyListGraph() {
			VList = new NodePositionList<Vertex<V>>();
			EList = new NodePositionList<Edge<E>>();
		}
	
	//Methods
		
		/**
		 * Returns an iterable collection of vertexes.
		 * @return an iterable collection of vertexes.
		 */
		
		public Iterable<Vertex<V>> vertices() {
			return VList;
		}
	
		
		/**
		 * Returns an iterable collection of edges.
		 * @return an iterable collection of edges.
		 */
		public Iterable<Edge<E>> edges() {
			return EList;
		}
		
		/**
		 * Creates a new edge in this graph, going from the source vertex 
		 * to the target vertex, and returns the created edge.
		 * @param v the source vertex.
		 * @param w the target vertex.
		 * @param o the element to store in the vertex.
		 * @return the edge created.
		*/
		public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E o) throws InvalidPositionException {
		  MyVertex<V> vv = checkVertex(v);
		  MyVertex<V> ww = checkVertex(w);
		  MyEdge<E> ee = new MyEdge<E>(v, w, o);
		 /* Inserimento dell'arco nelle liste di incidenza dei vertici */
		  Position<Edge<E>> pv = vv.insertIncidence(ee);
		  Position<Edge<E>> pw = ww.insertIncidence(ee);
		 /* setta i riferimenti alle position dell'arco nelle liste di incidenza dei
		   vertici */
		  ee.setIncidences(pv, pw);
		  EList.addLast(ee); //aggiunge l’arco alla lista degli archi EList
		  Position<Edge<E>> pe = EList.last();
		  ee.setLocation(pe); //setta il riferimento alla position dell'arco in EList
		  return ee;
		}

		/**
		 * Replaces the element stored into a specified edge 
		 * with one other.
		 * @param p the edge.
		 * @param o the new element.
		 * @return the element previously stored in the edge.
		 * @throws InvalidPositionException if the Position is not valid.
		 * 
		 */
		public E replace(Edge<E> p, E o) throws InvalidPositionException {
		  E temp = p.element();
		  MyEdge<E> ee = checkEdge(p);
		  ee.setElement(o);
		  return temp;
		}
		
		/**
		 * Auxiliary method - Checks if an edge is valid for this
		 * {@link AdjacencyListGraph}. 
		 * @param p the edge to check.
		 * @return the edge casted to MyEdge.
		 */
		@SuppressWarnings("unchecked")
		private MyEdge<E> checkEdge(Edge<E> p) {
			if(p == null || !(p instanceof MyEdge))
				throw new InvalidPositionException();
			MyEdge<E> edge = (MyEdge<E>) p;
			  if(edge.location() == null)
				  throw new InvalidPositionException();
			return (MyEdge<E>)p;
		}
		
		/**
		 * Returns true if the specified vertexes are adjacent; 
		 * false, otherwise.
		 * @param u the first vertex.
		 * @param v the second vertex.
		 * @return true if the specified vertexes are adjacent; 
		 * false, otherwise.
		 */
		
		public boolean areAdjacent(Vertex<V> u, Vertex<V> v) {
			MyVertex<V> vertexV = checkVertex(u);
			MyVertex<V> vertexU = checkVertex(v);
			Iterable<Edge<E>> inc;
			Edge<E> edge= null;
			if(vertexV.degree()<vertexU.degree()) {
				inc= vertexV.incidentEdges();
				Iterator<Edge<E>> it = inc.iterator();
				while(it.hasNext()){
					edge = it.next();
					if(opposite(vertexV, edge) == vertexU)
						return true;
				}
			}
			else{
				inc= vertexU.incidentEdges();
				Iterator<Edge<E>> it = inc.iterator();
				while(it.hasNext()){
					edge = it.next();
					if(opposite(vertexU, edge) == vertexV)
						return true;
				}
			}
			return false;
		}
		
		/**
		 * Returns the end points of the specified edge.
		 * @param e the edge of which we want the end points.
		 * @return the end points of the specified edge.
		 */
		public Vertex<V>[] endVertices(Edge<E> e) {
			MyEdge<E> edge = checkEdge(e);
			return edge.endVertices();
		}
		
		/**
		 * Returns the incident edges of the specified vertex.
		 * @param v the vertex of which we want the incident edges.
		 * @return  the incident edges of the specified vertex.
		 * @throws InvalidPositionException if the vertex is not valid.
		 */
		
		public Iterable<Edge<E>> incidentEdges(Vertex<V> v) {
			MyVertex<V> vertex= checkVertex(v);
			return vertex.incidentEdges();
		}
		
		/**
		 * Inserts the specified vertex into this graph.
		 * @param o vertex to insert.
		 * @return the vertex added.
		 */
		
		public Vertex<V> insertVertex(V o) {
			MyVertex<V> vertex = new MyVertex<V>(o);
			 VList.addLast(vertex);
			 vertex.setLocation(VList.last());
			return vertex;
		}
		
		/**
		 * Returns the number of edges.
		 * @return the number of edges.
		 */
		public int numEdges() {
			return EList.size();
		}
		
		/**
		 * Returns the number of vertexes.
		 * @return the number of vertexes.
		 */
		public int numVertices() {
			return VList.size();
		}
		
		/**
		 * Returns the opposite vertex of a specified vertex on a given edge.
		 * @param v the vertex.
		 * @param e the edge.
		 * @return the opposite vertex of v.
		 * @throws InvalidPositionException if v is a non valid vertex or
		 * if e is a non valid edge.
		 * 
		 */
		@SuppressWarnings("unchecked")
		public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
			MyVertex<V> vertex = checkVertex(v);
			MyEdge<E> edge = checkEdge(e);
			Vertex<V>[] endPoints = (Vertex<V>[]) new Vertex[2]; 
			endPoints= edge.endVertices();
			if(endPoints[0] == vertex)
				return endPoints[1];
			else if(endPoints[1] == vertex)
				return endPoints[0];
			return null;
		}
		
		/**
		 * Removes the specified edge from this graph.
		 * @param e the edge to remove.
		 * @return the element stored in the removed edge.
		 */
		public E removeEdge(Edge<E> e) {
			MyEdge<E> edge = checkEdge(e); 
			MyVertex<V>[] endPoints = edge.endVertices();
			Position<Edge<E>>[] incidents = edge.incidences();
			endPoints[0].removeIncidence(incidents[0]);
			endPoints[1].removeIncidence(incidents[1]);
			EList.remove(edge.location());
			edge.setLocation(null);
			return e.element();
		}
		
		/**
		 * Removes the specified vertex from this graph.
		 * @param v the vertex to remove.
		 * @return the element stored in the removed vertex.
		 */
		public V removeVertex(Vertex<V> v) {
			MyVertex<V> vertex = checkVertex(v);
			MyEdge<E> edge;
			Iterator<Edge<E>> inc = incidentEdges(v).iterator();
			while(inc.hasNext()){
				edge = (MyEdge<E>) inc.next();
				EList.remove(edge.location());
			}
			inc = null;
			VList.remove(vertex.location());
			vertex.setLocation(null); //Invalidating position
			return v.element();
		}

		/**
		 * Replaces the element stored in the specified vertex.
		 * @param p the vertex to update.
		 * @param o the new value.
		 * @return the element previously stored in the vertex.
		 */
		public V replace(Vertex<V> p, V o) {
			MyVertex<V> vertex = checkVertex(p);
			V oldElement = vertex.element();
			vertex.setElement(o);
			return oldElement;
		}
		
		
		/**
		 * Tests if the given position is valid.
		 * @param p the position to check.
		 * @return the given position casted to MyPosition 
		 * if the position is valid.
		 * @throws InvalidPositionException if the position is not valid.
		 */
		@SuppressWarnings({ "unused", "unchecked" })
		private MyPosition checkPosition(Position p) throws InvalidPositionException {
		   if (p == null || !(p instanceof MyPosition))
		     throw new InvalidPositionException();
		   return (MyPosition) p;
		 }
		
		
		/**
		 * Tests if the given vertex is valid.
		 * @param v the position to check.
		 * @return the given position casted to MyVertex 
		 * if the position is valid.
		 * @throws InvalidPositionException if the position is not valid.
		 */
		@SuppressWarnings("unchecked")
		private MyVertex<V> checkVertex(Vertex<V> v) throws InvalidPositionException {
		  if (v == null || !(v instanceof MyVertex))
		   throw new InvalidPositionException();
		  MyVertex<V> vertex = (MyVertex<V>) v;
		  if(vertex.location() == null)
			  throw new InvalidPositionException();
		  return (MyVertex<V>) v;
		 }
		
		
		@Override
		public String toString(){
			  String s;
			  s = "[";
			  if (EList.size() > 0) 
				  s+= EList.first();
			  if (EList.size() > 1){
				  int x = 0;
				 Iterable<Position<Edge<E>>> itPos = EList.positions();
				  for(Position<Edge<E>> pos : itPos){
					  if(x!=0 )
						  s += " - " + pos.element();
					  else
						  x++;
				  }
			  }
			  return s+"]";
		}
		
		/**
		 * 
		 * The MyEdge class implements Edge interface. 
		 * @author SuperJulietta
		 * @version 1.0
		 * @param <E> the edge type.
		 */
		@SuppressWarnings("hiding")
		protected class MyEdge<E> extends MyPosition<E> implements Edge<E> {
			//Instance variables
				/** edge endPoints */
				protected MyVertex<V>[] endVertices;
				/** edge's positions in the incidence list of the edge's endPoints */
				protected Position<Edge<E>>[] Inc;
				/** edge's position in the edge list */
				protected Position<Edge<E>> loc;
			
			//Constructor
				/**
				 * Constructs an edge between two given edges with a given
				 * element stored.
				 * @param v the first end point.
				 * @param w the second end point.
				 * @param o the element to store in this edge.
				 */
				@SuppressWarnings("unchecked")
				MyEdge (Vertex<V> v, Vertex<V> w, E o) {
					elem = o;
					endVertices = (MyVertex<V>[]) new MyVertex[2];
					endVertices[0] = (MyVertex<V>)v;
					endVertices[1] = (MyVertex<V>)w;
					Inc = (Position<Edge<E>>[]) new Position[2];
				}
			
			//Methods
				/** 
				 * Returns an array containing the edge's endPoints.
				 * @return an array containing the edge's endPoints.
				 */
				public MyVertex<V>[] endVertices() {
					return endVertices;      
				}
				
				/**
				 * Returns the list of incident vertexes on this edge.
				 * @return the list of incident vertexes on this edge.
				 */
				public Position<Edge<E>>[] incidences() {
					return Inc;
				}
				
				/**
				 * Sets the incident vertexes.
				 * @param pv the new (first) vertex . 
				 * @param pw the new (second) vertex.
				 */
				public void setIncidences(Position<Edge<E>> pv, Position<Edge<E>> pw) {
				     Inc[0] = pv;
				     Inc[1] = pw;
				   }
				
				/**
				 * Returns the edge's location.
				 * @return the edge's location.
				 */
				public Position<Edge<E>> location() {
				     return loc;
				  }
				
				/**
				 * Sets the edge's location.
				 * @param p the new position for this edge.
				 */
				public void setLocation(Position<Edge<E>> p) {
				     loc = p;
				   }
				
				@Override
				public String toString() {
				     return element() + "(" + endVertices[0].toString() +
				"," + endVertices[1].toString() + ")";
				}
		}

		/**
		 * MyVertex class implements Vertex interface.
		 * @author SuperJulietta
		 * @version 1.0
		 * @param <V> the vertex type.
		 */
		@SuppressWarnings("hiding")
		protected class MyVertex<V> extends MyPosition<V>
		   implements Vertex<V> {
		  //Instance Variables
				/** Reference to the vertex's incidence list */
				protected PositionList<Edge<E>> incEdges;
				/** Reference to the vertex's position in the vertex list */
				protected Position<Vertex<V>> loc;
			
		 //Constructor
				/**
				 * Constructs a vertex.
				 * @param o the element to store in this vertex.
				 */
				public MyVertex(V o) {
					elem = o;
					incEdges = new NodePositionList<Edge<E>>();
				}
		
				/**
				 * Returns the degree of this vertex. 
				 * @return the degree of this vertex.
				 */
				public int degree() {
					return incEdges.size();
				}
		 
				/**
				 * Returns an iterable collection of the incident edges. 
				 * @return an iterable collection of the incident edges.
				 */
				public Iterable<Edge<E>> incidentEdges() {
					return incEdges;                          
				}      
				
				/** 
				 * Inserts an edge into the incident_edges' list.
				 * @param e the edge.
				 * @return the position of the inserted edge.
				 */
				public Position<Edge<E>> insertIncidence(Edge<E> e) {
					incEdges.addLast(e);
					return incEdges.last(); 
				}
				
				/** 
				 * Removes the specified edge from the incident_edges' list. 
				 * @param p the position of the edge to remove. 
				 */
				public void removeIncidence(Position<Edge<E>> p){ 
					incEdges.remove(p); 
				}
				
				/**
				 * Returns the location of this vertex.
				 * @return the location of this vertex.
				 */
				public Position<Vertex<V>> location() { 
					return loc; 
				}
				
				/**
				 * Sets the vertex's position.
				 * @param p the new position.
				 */
				public void setLocation(Position<Vertex<V>> p) { 
					loc = p;
				}
				
				@Override
				public String toString() { 
					return elem.toString();
				}
		}
		

}
