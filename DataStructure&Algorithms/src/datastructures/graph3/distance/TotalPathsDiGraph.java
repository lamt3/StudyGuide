package datastructures.graph3.distance;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class TotalPathsDiGraph {
	public static void main(String[] args) {
		// vector of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(new Edge(0, 6), new Edge(0, 1), new Edge(1, 6), new Edge(1, 5), new Edge(1, 2),
				new Edge(2, 3), new Edge(3, 4), new Edge(5, 2), new Edge(5, 3), new Edge(5, 4), new Edge(6, 5),
				new Edge(7, 6), new Edge(7, 1));

		// Number of vertices in the graph
		final int N = 8;

		// construct graph
		Graph g = new Graph(edges, N);

		int src = 0, dest = 3, m = 4;

		// Do modified BFS traversal from source vertex src
		System.out.println(CountPaths.modifiedBFS(g, src, dest, m));
	}
}

class Edge {
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

// BFS Node
class Node {
	// stores current vertex number and current depth of
	// BFS (how far away current node is from the source?)
	int vertex, depth;

	public Node(int vertex, int depth) {
		this.vertex = vertex;
		this.depth = depth;
	}
};

// class to represent a graph object
class Graph {
	// An array of Lists to represent adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the directed graph
		for (int i = 0; i < edges.size(); i++) {
			int src = edges.get(i).source;
			int dest = edges.get(i).dest;

			adjList.get(src).add(dest);
		}
	}
}

class CountPaths {
	// Perform BFS on graph g starting from vertex v
	public static int modifiedBFS(Graph g, int src, int dest, int m) {
		// create a queue used to do BFS
		Queue<Node> q = new ArrayDeque<>();

		// push source vertex into the queue
		q.add(new Node(src, 0));

		// stores number of paths from source to destination
		// having exactly m edges
		int count = 0;

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue
			Node node = q.poll();

			int v = node.vertex;
			int depth = node.depth;

			// if destination is reached and BFS depth is equal to m
			// update count
			if (v == dest && depth == m)
				count++;

			// don't consider nodes having BFS depth more than m.
			// This check will result in optimized code and also
			// handle cycles in the graph (else loop will never break)
			if (depth > m)
				break;

			// do for every adjacent vertex u of v
			for (int u : g.adjList.get(v)) {
				// push every vertex (discovered or undiscovered) into
				// the queue
				q.add(new Node(u, depth + 1));
			}
		}

		// return number of paths from source to destination
		return count;
	}
}