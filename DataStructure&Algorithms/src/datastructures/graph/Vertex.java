package datastructures.graph;

public class Vertex implements Comparable<Vertex> {
	public final String name;
	public Edge[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;
	public Vertex secondPrevious; 
	public Vertex secondMinDistance; 
	
	public Vertex(String argName){ 
		name = argName; 
	}
	public String toString(){ 
		return name; 
	}
	@Override
	public int compareTo(Vertex o) {
		return Double.compare(minDistance, o.minDistance);
	}
}
