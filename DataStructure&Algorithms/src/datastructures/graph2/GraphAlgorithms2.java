package datastructures.graph2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphAlgorithms2 {
	
	/*
	 *  1. Assign RED color to the source vertex (putting into set U).
		2. Color all the neighbors with BLUE color (putting into set V).
		3. Color all neighbor’s neighbor with RED color (putting into set U).
		4. This way, assign color to all vertices such that it satisfies all the constraints of m way coloring problem where m = 2.
		5. While assigning colors, if we find a neighbor which is colored with same color as current vertex, then the graph cannot be colored with 2 vertices (or graph is not Bipartite)
	 */
	public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length <= 1) {
            return true;
        }
        // color: -1 [unvisited], 0 [red], 1 [blue] 
        int m = graph.length;
        int[] colored = new int[m];
        Arrays.fill(colored, -1);
        for (int i = 0; i < m; i++) {
            if (colored[i] == -1) {
                if (!BFS(graph, i, colored)) return false;
            }
        }
        return true;
    }
    
    private boolean BFS(int[][] g, int src,  int[] visited) {
        visited[src] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            int[] neighbors = g[currNode];
            for (int n: neighbors) {
                
                if (visited[n] == -1) {
                    visited[n] = 1 - visited[currNode];
                    queue.offer(n);
                } else {
                    if (visited[n] == visited[currNode]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
    
    
    
    public int[][] fillGraph(){
    	int[][] a = new int[4][2];
    	a[0][0]=1;
    	a[0][1]=3;
    	a[1][0]=0;
    	a[1][1]=2;
    	a[2][0]=1;
    	a[2][1]=3;
    	a[3][0]=0;
    	a[3][1]=2;
    	return a;
    	
    }
    
    public static void main(String[] args) {
    	GraphAlgorithms2 c = new GraphAlgorithms2();
    	int[][]a = c.fillGraph();
    	c.isBipartite(a);
    	
    }
    

}
