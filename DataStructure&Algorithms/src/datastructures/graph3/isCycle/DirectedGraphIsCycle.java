package datastructures.graph3.isCycle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DirectedGraphIsCycle {
	public static void main(String[] args) 
    { 
		
		/*
		 * To detect a back edge, we can keep track of vertices currently in recursion stack of function for DFS traversal. 
		 * If we reach a vertex that is already in the recursion stack, then there is a cycle in the tree. 
		 * The edge that connects current vertex to the vertex in the recursion stack is a back edge. 
		 * We have used recStack[] array to keep track of vertices in the recursion stack.
		 * 
		 * Time Complexity of this method is same as time complexity of DFS traversal which is O(V+E).
		 */
        Graph2 graph = new Graph2(4); 
        graph.addEdge(0, 1); 
        graph.addEdge(0, 2); 
        graph.addEdge(1, 2); 
        graph.addEdge(2, 0); 
        graph.addEdge(2, 3); 
        graph.addEdge(3, 3); 
          
        if(graph.isCyclic()) 
            System.out.println("Graph contains cycle"); 
        else
            System.out.println("Graph doesn't "
                                    + "contain cycle"); 
    } 
}

class Graph2 {

	private final int V;
	private final List<List<Integer>> adj;

	public Graph2(int V) {
		this.V = V;
		adj = new ArrayList<>(V);

		for (int i = 0; i < V; i++)
			adj.add(new LinkedList<>());
	}

	// This function is a variation of DFSUytil() in
	// https://www.geeksforgeeks.org/archives/18212
	 boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {

		// Mark the current node as visited and
		// part of recursion stack
		if (recStack[i])
			return true;

		if (visited[i])
			return false;

		visited[i] = true;

		recStack[i] = true;
		List<Integer> children = adj.get(i);

		for (Integer c : children)
			if (isCyclicUtil(c, visited, recStack))
				return true;

		recStack[i] = false;

		return false;
	}

	void addEdge(int source, int dest) {
		adj.get(source).add(dest);
	}

	// Returns true if the graph contains a
	// cycle, else false.
	// This function is a variation of DFS() in
	// https://www.geeksforgeeks.org/archives/18212
	 boolean isCyclic()  
    { 
          
        // Mark all the vertices as not visited and 
        // not part of recursion stack 
        boolean[] visited = new boolean[V]; 
        boolean[] recStack = new boolean[V]; 
          
          
        // Call the recursive helper function to 
        // detect cycle in different DFS trees 
        for (int i = 0; i < V; i++) 
            if (isCyclicUtil(i, visited, recStack)) 
                return true; 
  
        return false; 
    }
}