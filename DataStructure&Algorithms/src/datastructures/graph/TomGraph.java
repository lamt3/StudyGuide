package datastructures.graph;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TomGraph {
	public Map<Integer, Set<Integer>> edges = new TreeMap<>();
	
	public void addNode(int node){
		if(!edges.containsKey(node)){
			edges.put(node, new TreeSet<Integer>());
		}
		
	}
	
	 public void removeNode(int u) {
	    if (!edges.containsKey(u)) {
	      return;
	    }
	    for (int v : edges.get(u)) {
	      edges.get(v).remove(u);
	    }
	    edges.remove(u);
	  }

	 public void addEdge(int u, int v) {
	    addNode(u);
	    addNode(v);
	    edges.get(u).add(v);
	    edges.get(v).add(u);
	 }

	 public void removeEdge(int u, int v) {
	    edges.get(u).remove(v);
	    edges.get(v).remove(u);
	 }
	 
	 public static TomGraph createRandomGraph(){
		 TomGraph g = new TomGraph();
		 g.addEdge(1, 2);
		 g.addEdge(2, 5);
		 g.addEdge(6, 7);
		 g.addEdge(9, 7);
		 g.addEdge(8, 4);
		 g.addEdge(4, 1);
		 g.addEdge(1, 9);
		 g.addEdge(2, 4);
		 g.addEdge(6, 9);
		 return g;
	 }
	
	 
	 public class TomNode{
		public int data;
		public boolean visited = false;
		public TomNode(int data){
			this.data = data;
		}
			
	}
	public static void main(String[] args){
		TomGraph g = TomGraph.createRandomGraph();
		List<Integer> dfs = GraphAlgorithms.bfsUsingQueue(2, g);
		for (int i : dfs){
			System.out.println(i);
		}
		
	}
	 
}


