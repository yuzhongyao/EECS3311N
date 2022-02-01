package york.eecs.source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UndirectedGraph<T extends Comparable<T>> extends Graph<T> {

	private Map<T, Set<T>> graph;
	
	/**
	 *  This is the constructor.
	 *  Please do not change it.
	 */
	public UndirectedGraph() {
		this.graph = new HashMap<>();
	}
	
	/**
	 * @return true if graph is empty, false otherwise.
	 */
	public boolean isEmpty() {
		// TODO: Complete this method
		// Hint: An empty graph contains zero vertices
		return this.graph.size() == 0; // this line needs to be rewritten 
	}

	/**
	 * @return the size (i.e. number of vertices) of this graph
	 */
	public int getSize() {
		// TODO: compute the size
		return this.graph.size(); // this line needs to be rewritten
	}
	
	/**
	 * Add a new vertex to the graph. A new vertex points
	 * to an empty set of adjacent vertices.
	 * 
	 * @param vertex: an object that is a new vertex in the graph
	 */
	public void addVertex(T vertex) throws VertexExistsException {
		// TODO: Complete this method  
		// Hints: If the vertex already exists, throw and exception
		//        Else, add a new pair to the graph hashmap:
		//        the vertex is the key, the value is an empty
		//        set of vertices.
		if(this.graph.containsKey(vertex)) {
			throw new VertexExistsException("Vertex already exists");
		}
		
		Set<T> set = new HashSet<T>();
		this.graph.put(vertex, set);
		
	}
	
	public List<T> getAdjacent(T vertex) {
		return new ArrayList<>(graph.get(vertex));
	}
	
	
	@Override
	public List<T> getVertices() {
		return graph.keySet().stream()
                .collect(Collectors.toList());
	}

	/**
	 * @param fromVertex one of vertices of this edge
	 * @param toVertex the other vertex of this egde
	 */
	public void addEdge(T fromVertex, T toVertex) {
		// TODO: Complete this method
		// Hint: Recall, both vertices already exist. Also,
		//       our graphs are not oriented, hence both edges
		//       need to be added.
		this.graph.get(fromVertex).add(toVertex);
		this.graph.get(toVertex).add(fromVertex);
	}

	@Override
	public String toString() {
		// TODO: Override toString() method
		Set<T> set = new HashSet<T>();
		set = this.graph.keySet();
		
		ArrayList<T> sortedVertices = new ArrayList<T>(set);
		sortedVertices.sort((s1, s2) -> s1.compareTo(s2));
		
		Set<T> adjacents = new HashSet<T>();
		
		
		String result = "Graph:\n";
		for(int i = 0; i < sortedVertices.size(); i++) {
			adjacents = this.graph.get(sortedVertices.get(i));
			ArrayList<T> sortedAdjacents = new ArrayList<T>(adjacents);
			sortedAdjacents.sort((s1, s2) -> s1.compareTo(s2));
			
			
			result += "Vertex: " + sortedVertices.get(i) + " & Adjacent Vertices: " + sortedAdjacents.toString() +"\n";
		}
		
		
		
		
		
		
		
		/**
		 * List<String> s = new ArrayList<String>();
		s.add("Graph:\n");
		this.graph.forEach((k, v) ->  s.add("Vertex: " + k + " & Adjacent Vertices: " + v.toString()+"\n"));
		String result = "";
		for(int i = 0; i < s.size(); i++) {
			String temp = s.get(i);
			result += temp;
		}
		*/
        return result; // this line needs to change
	}


}
