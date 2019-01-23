package datastructures.graph;

import java.util.List;

/*
 * Weighted graph for Dijkstra implementation 
 */
public class TomGraph2 {
	
	List<Vertex> vertices; 
	List<Edge> edges;
	
	public TomGraph2(List<Vertex> vertices, List<Edge> edges){
		this.vertices = vertices;
		this.edges = edges;
	}

	
	
}
//
//class Vertex implements Comparable<Vertex>{
//	public final String name;
//	public Edge[] adjacencies;
//	public double minDistance = Double.POSITIVE_INFINITY;
//	public Vertex previous;
//	public Vertex(String argName){ 
//		name = argName; 
//	}
//	public String toString(){ 
//		return name; 
//	}
//	@Override
//	public int compareTo(Vertex o) {
//		return Double.compare(minDistance, o.minDistance);
//	}
//
//	
//	
//}

//class Edge{
//	public final Vertex target;
//	public final double weight;
//	public Edge(Vertex argTarget, double argWeight){ 
//		target = argTarget; 
//		weight = argWeight; 
//	}
//}
