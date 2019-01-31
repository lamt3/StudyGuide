package datastructures.graph3.isCycle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class UndirectedGraphIsCycle {

	public static void main(String[] args) {

		/*
		 * Check if an undirected graph contains cycle or not BFS Time Complexity: The
		 * program does a simple BFS Traversal of graph and graph is represented using
		 * adjacency list. So the time complexity is O(V+E)
		 */

		// List<Edge> edges = Arrays.asList(new Edge(1, 2), new Edge(1, 3), new Edge(1,
		// 4), new Edge(2, 5), new Edge(2, 6),
		// new Edge(5, 9), new Edge(5, 10), new Edge(4, 7), new Edge(4, 8), new Edge(7,
		// 11), new Edge(7, 12),
		// new Edge(6, 10)
		// );
		// Set number of vertices in the graph
		// final int N = 13;

		List<Edge> edges = Arrays.asList(new Edge(1, 2), new Edge(1, 3), new Edge(2, 3));
		final int N = 4;

		// create a graph from edges
		Graph graph = new Graph(edges, N);

		// Do BFS traversal in connected components of graph
//		BFSUtil bfs = new BFSUtil();
//		if (bfs.BFS(graph, 1, N))
//			System.out.println("Graph contains cycle");
//		else
//			System.out.println("Graph doesn't contain any cycle");
		
		//=========================================================================================
		
		/*
		 * Check if an undirected graph contains cycle or not DFS Time Complexity: The
		 * program does a simple BFS Traversal of graph and graph is represented using
		 * adjacency list. So the time complexity is O(V+E)
		 */
		
		
		
		
	}
}

// Data structure to store graph edges
class Edge {
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

// Class to represent a graph object
class Graph {
	// An array of Lists to represent adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge edge : edges) {
			int src = edge.source;
			int dest = edge.dest;

			adjList.get(src).add(dest);
			adjList.get(dest).add(src);
		}
	}
}

// Node to store vertex and its parent info in BFS
class Node {
	int v, parent;

	Node(int v, int parent) {
		this.v = v;
		this.parent = parent;
	}
};

class BFSUtil {
	// Perform BFS on graph starting from vertex src and
	// returns true of cycle is found in the graph
	public boolean BFS(Graph graph, int src, int N) {
		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// mark source vertex as discovered
		discovered[src] = true;

		// create a queue used to do BFS and
		// push source vertex into the queue
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(src, -1));

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue and print it
			Node node = q.poll();

			// do for every edge (v -> u)
			for (int u : graph.adjList.get(node.v)) {
				if (!discovered[u]) {
					// mark it discovered
					discovered[u] = true;

					// construct the queue node containing info
					// about vertex and push it into the queue
					q.add(new Node(u, node.v));
				}

				// u is discovered and u is not a parent
				else if (u != node.parent) {
					// we found a cross-edge ie. cycle is found
					return true;
				}
			}
		}

		// No cross-edges found in the graph
		return false;
	}
}

class DFSUtil {
	// Function to perform DFS Traversal
	public static boolean DFS(Graph graph, int v, boolean[] discovered, int parent) {
		// mark current node as discovered
		discovered[v] = true;

		// do for every edge (v -> w)
		for (int w : graph.adjList.get(v)) {
			// w is not discovered
			if (!discovered[w]) {
				if (DFS(graph, w, discovered, v))
					return true;
			}

			// w is discovered and w is not a parent
			else if (w != parent) {
				// we found a back-edge (cycle)
				return true;
			}
		}

		// No back-edges found in the graph
		return false;
	}
}