package datastructures.graph3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * Undirected Adjacency List Graph 
 */
public class AdjacencyListGraph {
	/*can optimize by using set instead of list for remove operations*/
	private Map<Integer, List<Integer>> adjList;
	

	public AdjacencyListGraph(int nodes) {
		this.adjList = new HashMap<Integer, List<Integer>>();
		for (int i = 1; i <= nodes; i++) {
			adjList.put(i, new LinkedList<Integer>());
		}


	}

	public boolean addVertex(int i) {
		if(!adjList.containsKey(i)) {
			return false;
		}
		adjList.put(i, new LinkedList<Integer>());
		return true;
	}
	
/*
 * To remove a vertex, you first need to find the vertex in your data structure.
 * This time complexity of this find operation depends on the data structure you use; if you use a HashMap, it will be O(1); if you use a List, it will be O(V).
 * Once you have identified the vertex that needs to be removed, you now need to remove all the edges of that vertex. 
 * Since you are using an adjacency List, you simply need to iterate over the edge-list of the vertex you found in the previous step and update all those nodes. 
 * The run-time of this step is O(Deg(V)). Assuming a simple graph, the maximum degree of a node is O(V). For sparse graphs it will be much lower.
 * Hence the run-time of removeVertex will only be O(V).
 */
	public boolean removeVertex(int i) {
		if(!adjList.containsKey(i)) {
			return false; 
		}
		
		for(int v : adjList.get(i)) {
			adjList.get(v).remove(i);
		}
		adjList.remove(i);
	
		return true;
	}
	
	
	public void addEdge(int u, int v) {
		addVertex(u);
		addVertex(v);
		adjList.get(u).add(v);
		adjList.get(v).add(u);
	}
	
	
	public void removeEdge(int u, int v) {
		adjList.get(u).remove(v);
		adjList.get(v).remove(u);
	}
	
	public List<Integer> getNeighbors(int i){
		return adjList.get(i);
	}
	

}
