package york.eecs.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UndirectedGraphAlgorithms<T extends Comparable<T>> 
								implements GraphAlgorithms<T> {

	/**
	 * Please implement BFS algorithm as described on the handout
	 * @param g: a graph
	 * @param initial: the starting vertex of the path
	 * @param destination: the destination vertex of the path
	 * @return the path from initial to destination in the form of
	 *         an ArrayList of vertices, with initial as the first
	 *         element, and destination as the last element of the 
	 *         ArrayList
	 */
	public List<T> findBFSpath(Graph<T> g, T initial, T destination) {
		// TODO : implement BFS path search
		ArrayList<T> result = new ArrayList<T>();
		Queue<ArrayList<T>> queue = new LinkedList<ArrayList<T>>();
		ArrayList<T> n = new ArrayList<T>();
		ArrayList<T> copy = new ArrayList<T>();
		HashMap<T,T> parent = new HashMap<T, T>();
		
		
		boolean hasPath = false;
		T temp;
		
		result.add(initial);
		
		if(initial == destination) {
			return result;
		}
		
		queue.offer(result);
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			temp = n.get(n.size()- 1);
			if(n.get(n.size() - 1) == destination) {
				hasPath = true;
				break;
			}
			
			for(T m: g.getAdjacent(n.get(n.size() - 1))) {
				if(!n.contains(m)) {
					parent.put(m, temp);
					copy = n;
					copy.add(n.size(), m);
					queue.offer(copy);
					
				}
			}
		}
		
		result.clear();
		
		//Returns empty list if no path can be found
		if(!hasPath) {
			return result;
		}
		
		result.add(destination);
		while(!(result.contains(destination)&& result.contains(initial))) {
			result.add(parent.get(result.get(result.size() - 1)));
		}
		Collections.reverse(result);
		return result; // this line must change
	}

}
