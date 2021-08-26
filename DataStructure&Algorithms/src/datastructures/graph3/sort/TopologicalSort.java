package datastructures.graph3.sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

	public static void main(String[] args) {

		/*
		 * https://www.geeksforgeeks.org/all-topological-sorts-of-a-directed-acyclic-
		 * graph/
		 * 
		 * In a Directed acyclic graph many a times we can have vertices which are
		 * unrelated to each other because of which we can order them in many ways.
		 * These various topological sorting is important in many cases, for example if
		 * some relative weight is also available between the vertices, which is to
		 * minimize then we need to take care of relative ordering as well as their
		 * relative weight, which creates the need of checking through all possible
		 * topological ordering.
		 * 
		 * We can go through all possible ordering via backtracking , the algorithm step
		 * are as follows : 1. Initialize all vertices as unvisited
		 * 
		 * 2. Now choose vertex which is unvisited and has zero indegree and decrease
		 * indegree of all those vertices by 1 (corresponding to removing edges) now add
		 * this vertex to result and call the recursive function again and backtrack)
		 * 
		 * 3. After returning from function reset values of visited, result and indegree
		 * for enumeration of other possibilities.
		 */

		// Create a graph given in the above diagram
		Graph graph = new Graph(6);
		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(4, 0);
		graph.addEdge(4, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);

		System.out.println("All Topological sorts");
		graph.allTopologicalSorts();
	}

}

class Graph {
	int V; // No. of vertices

	List<Integer> adjListArray[];

	public Graph(int V) {

		this.V = V;

		@SuppressWarnings("unchecked")
		List<Integer> adjListArray[] = new LinkedList[V];

		this.adjListArray = adjListArray;

		for (int i = 0; i < V; i++) {
			adjListArray[i] = new LinkedList<>();
		}
	}

	// Utility function to add edge
	public void addEdge(int src, int dest) {

		this.adjListArray[src].add(dest);

	}

	// Main recursive function to print all possible
	// topological sorts
	private void allTopologicalSortsUtil(boolean[] visited, int[] indegree, ArrayList<Integer> stack) {
		// To indicate whether all topological are found
		// or not
		boolean flag = false;

		for (int i = 0; i < this.V; i++) {
			// If indegree is 0 and not yet visited then
			// only choose that vertex
			if (!visited[i] && indegree[i] == 0) {

				// including in result
				visited[i] = true;
				stack.add(i);
				for (int adjacent : this.adjListArray[i]) {
					indegree[adjacent]--;
				}
				allTopologicalSortsUtil(visited, indegree, stack);

				// resetting visited, res and indegree for
				// backtracking
				visited[i] = false;
				stack.remove(stack.size() - 1);
				for (int adjacent : this.adjListArray[i]) {
					indegree[adjacent]++;
				}

				flag = true;
			}
		}
		// We reach here if all vertices are visited.
		// So we print the solution here
		if (!flag) {
			stack.forEach(i -> System.out.print(i + " "));
			System.out.println();
		}

	}

	// The function does all Topological Sort.
	// It uses recursive alltopologicalSortUtil()
	public void allTopologicalSorts() {
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[this.V];

		int[] indegree = new int[this.V];

		for (int i = 0; i < this.V; i++) {

			for (int var : this.adjListArray[i]) {
				indegree[var]++;
			}
		}

		ArrayList<Integer> stack = new ArrayList<>();

		allTopologicalSortsUtil(visited, indegree, stack);
	}
	
	//====================================================================================================================

	// A recursive function used by topologicalSort --> this is to find ONLY 1 TOPOLGOICAL SORT 
	void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
		// Mark the current node as visited.
		visited[v] = true;
		Integer i;

		// Recur for all the vertices adjacent to this
		// vertex
		Iterator<Integer> it = adjListArray[v].iterator();
		while (it.hasNext()) {
			i = it.next();
			if (!visited[i])
				topologicalSortUtil(i, visited, stack);
		}

		// Push current vertex to stack which stores result
		stack.push(v);
	}

	// The function to do Topological Sort. It uses
	// recursive topologicalSortUtil()
	void topologicalSort() {
		Stack<Integer> stack = new Stack<>();

		// Mark all the vertices as not visited
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Call the recursive helper function to store
		// Topological Sort starting from all vertices
		// one by one
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				topologicalSortUtil(i, visited, stack);

		// Print contents of stack
		while (stack.empty() == false)
			System.out.print(stack.pop() + " ");
	}
}
