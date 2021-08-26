package datastructures.graph3.connected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinSumConnectedComponents {
	
	
	public static void main(String[] args) {
		
		Map<Integer, List<Integer>> g = createGraph();
		System.out.println(findMinSum(g));
		
		
		
	}
	
	public static Map<Integer, List<Integer>> createGraph(){
		Map<Integer, List<Integer>>  g = new HashMap<Integer, List<Integer>>();
		g.put(100, new ArrayList<Integer>());
		g.put(6, new ArrayList<Integer>());
		g.put(2, new ArrayList<Integer>());
		g.put(7, new ArrayList<Integer>());
		g.put(3, new ArrayList<Integer>());
		g.put(8, new ArrayList<Integer>());
		g.put(4, new ArrayList<Integer>());
		g.put(9, new ArrayList<Integer>());
		g.put(5, new ArrayList<Integer>());
		g.put(10, new ArrayList<Integer>());
		
		createEdges(g, 6, 100);
		createEdges(g, 2, 7);
		createEdges(g, 3, 8);
		createEdges(g, 4, 9);
		createEdges(g, 5, 10);

		return g;
		
	}
	
	public static void createEdges(Map<Integer, List<Integer>> m, int v, int u) {
		m.get(v).add(u);
		m.get(u).add(v);
	}
	
	
	public static int findMinSum(Map<Integer, List<Integer>> graph) {
		Set<Integer>visited = new HashSet<Integer>();
		int sum = 0;
		for(Map.Entry<Integer, List<Integer>> m : graph.entrySet()) {
			if(!visited.contains(m.getKey())) {
				Integer min = m.getKey();
				min = minHelper(graph, visited, min, m.getKey());
				sum+=min;
			}
		}
		return sum;
		
		
	}
	
	public static Integer minHelper(Map<Integer, List<Integer>> graph, Set<Integer> visited, Integer min, int nodeValue) {
		visited.add(nodeValue);
		for(Integer neighbor: graph.get(nodeValue)) {
			if(!visited.contains(neighbor)) {
				if(min > neighbor) {
					min = neighbor;
				}
				minHelper(graph, visited, min, neighbor);
			}
		}
		return min;
		
	}
	
	

}
