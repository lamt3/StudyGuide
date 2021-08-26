package datastructures.graph3.connected;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Max # OF Edges among different components in an undirected graph  
 * https://www.geeksforgeeks.org/maximum-number-of-edges-among-all-connected-components-of-an-undirected-graph/
 */
public class MaxNumberOfEdgesAmongComponents {
	public static void main(String[] args){ 
        // Create a graph given in the above diagram  
        Graph3 g = new Graph3(5); // 5 vertices numbered from 0 to 4  
          
        g.addEdge(1, 0);  
        g.addEdge(2, 3);
        g.addEdge(2, 4);  
        g.addEdge(3, 4); 
        System.out.println("Following are connected components"); 
        System.out.println(g.maxEdgesInConnectedComponents());  
    } 

}

class Graph3 { 
    // A user define class to represent a graph. 
    // A graph is an array of adjacency lists. 
    // Size of array will be V (number of vertices 
    // in graph) 
    int V; 
    LinkedList<Integer>[] adjListArray; 
      
    // constructor 
    Graph3(int V) { 
        this.V = V; 
        // define the size of array as 
        // number of vertices 
        adjListArray = new LinkedList[V]; 
  
        // Create a new list for each vertex 
        // such that adjacent nodes can be stored 
  
        for(int i = 0; i < V ; i++){ 
            adjListArray[i] = new LinkedList<Integer>(); 
        } 
    } 
      
    // Adds an edge to an undirected graph 
    void addEdge( int src, int dest) { 
        // Add an edge from src to dest. 
        adjListArray[src].add(dest); 
  
        // Since graph is undirected, add an edge from dest 
        // to src also 
        adjListArray[dest].add(src); 
    } 
      
    void DFSUtil(int v, boolean[] visited, List<Integer> nodesInComponent) { 
        // Mark the current node as visited and print it 
        visited[v] = true; 
        
       
        // Recur for all the vertices 
        // adjacent to this vertex 
        for (int x : adjListArray[v]) { 
            if(!visited[x]) {
            	DFSUtil(x,visited, nodesInComponent); 
            	nodesInComponent.add(x);
            }
            	
            	
        } 
  
    } 
    int maxEdgesInConnectedComponents() { 
        // Mark all the vertices as not visited 
        boolean[] visited = new boolean[V];
        List<Integer> numberOfEdgesInComponent = new ArrayList<Integer>();
        for(int v = 0; v < V; ++v) { 
            if(!visited[v]) { 
                // print all reachable vertices 
                // from v 
            	List<Integer> nodesInComponent = new ArrayList<>();
            	nodesInComponent.add(v);
                DFSUtil(v,visited, nodesInComponent);
                numberOfEdgesInComponent.add(getNumOfEdgesFromInDegreeOfNodes(nodesInComponent));
            } 
        } 
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<numberOfEdgesInComponent.size();i++) {
        	max = Math.max(max, numberOfEdgesInComponent.get(i));
        }
        return max;
    }
    
    /*
     *  total number of edges in a connected component of an undirected graph 
     *  is equal to half of the total sum of the degrees of all of its vertices.
     *  Total # of Edges = total sum of indegrees / 2
     */
    int getNumOfEdgesFromInDegreeOfNodes(List<Integer> nodesInComponent) {
    	int inDegree = 0;
    	for(int i = 0; i< nodesInComponent.size(); i++) {
    		int inDegreeCount = adjListArray[nodesInComponent.get(i)].size();
    		inDegree = inDegree+inDegreeCount;
    	}
    	return inDegree/2;
    }
    
}
