package datastructures.graph3.distance;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class ShortestDistanceInUndirectedGraph {
	public static void main(String args[]) {
		// To store adjacency list of graph
		int n = 9;
		Vector<Integer> edges[] = new Vector[9];

		for (int i = 0; i < edges.length; i++) {
			edges[i] = new Vector<>();
		}

		addEdge(edges, 0, 1);
		addEdge(edges, 0, 7);
		addEdge(edges, 1, 7);
		addEdge(edges, 1, 2);
		addEdge(edges, 2, 3);
		addEdge(edges, 2, 5);
		addEdge(edges, 2, 8);
		addEdge(edges, 3, 4);
		addEdge(edges, 3, 5);
		addEdge(edges, 4, 5);
		addEdge(edges, 5, 6);
		addEdge(edges, 6, 7);
		addEdge(edges, 7, 8);
		int u = 0;
		int v = 8;
		System.out.println(minEdgeBFS(edges, u, v, n));
	}

	static void addEdge(Vector<Integer> edges[], int a, int b) {
		edges[a].add(b);
		edges[b].add(a);
	}

	static int minEdgeBFS(Vector<Integer> edges[], int u, int v, int n) {
		int x = 0;
		int[] distance = new int[n];
		boolean[] visited = new boolean[n];

		Queue<Integer> q = new LinkedList<>();
		q.add(u);
		visited[u] = true;
		distance[u] = 0;

		while (!q.isEmpty()) {

			int temp = q.poll();

			for (int a : edges[temp]) {
				if (!visited[a]) {
					distance[a] = distance[temp] + 1;
					visited[a] = true;
					q.add(a);
					if (a == v)
						return distance[a];
				}
			}
		}

		return x;
	}
}
