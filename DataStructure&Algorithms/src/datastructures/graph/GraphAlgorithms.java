package datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphAlgorithms {

	public static List<Integer> dfsUsingStack(int start, TomGraph g){
		if(!g.edges.containsKey(start)){
			return null;
		}
		
		Set<Integer> visited = new HashSet<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		List<Integer> dfsPath = new ArrayList<Integer>();
		stack.add(start);
		visited.add(start);
		
		while(!stack.isEmpty()){
			int currentNode = stack.pop();
			dfsPath.add(currentNode);
			Set<Integer> neighbors = g.edges.get(currentNode);
			for(int i: neighbors){
				if(!visited.contains(i)){
					stack.add(i);
					visited.add(i);
				}
			}
		}
		
		return dfsPath;
	}
	
	public static List<Integer> bfsUsingQueue(int start, TomGraph g){
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		Set<Integer> visited = new HashSet<Integer>();
		List<Integer> bfsPath = new ArrayList<Integer>();
		visited.add(start);
		
		while(!q.isEmpty()){
			int currentNode = q.remove();
			bfsPath.add(currentNode);
			Set<Integer> neighbors = g.edges.get(currentNode);
			for(int i: neighbors){
				if(!visited.contains(i)){
					q.add(i);
					visited.add(i);
				}
			}
			
			
		}
		return bfsPath;
	}
	
	
	/*
	 * Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes.
	 * Mark all nodes unvisited. Set the initial node as current. Create a set of the unvisited nodes called the unvisited set consisting of all the nodes.
	 * For the current node, consider all of its unvisited neighbors and calculate their tentative distances. For example, if the current node A is marked with a distance of 6, and the edge connecting it with a neighbor B has length 2, then the distance to B (through A) will be 6 + 2 = 8.
	 * When we are done considering all of the neighbors of the current node, mark the current node as visited and remove it from the unvisited set. A visited node will never be checked again.
	 * If the destination node has been marked visited (when planning a route between two specific nodes) or if the smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal; occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm has finished.
	 * Select the unvisited node that is marked with the smallest tentative distance, and set it as the new "current node" then go back to step 3.
	 */
	public static void dijkstra(Vertex source){
		source.minDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);
		Set<Vertex> visitedVertex = new HashSet<Vertex>();
		
		while(!vertexQueue.isEmpty()){
			Vertex current = vertexQueue.poll();
			for(Edge e : current.adjacencies){
				Vertex target = e.target;
				if(visitedVertex.contains(target)){
					continue;
				}
				double weight = e.weight;
				double distanceThroughCurrent = current.minDistance + weight; 
			
				if(distanceThroughCurrent < target.minDistance){
					//Remove target vertex first to update min distance and its previous vertex and readd to queue.
					vertexQueue.remove(target);
					target.minDistance = distanceThroughCurrent;
					target.previous = current;
					vertexQueue.add(target);
				}
			}
			visitedVertex.add(current);
		}
		
		
		
	}
	
	public static List<Vertex> getShortestPathTo(Vertex target){
		List<Vertex> path = new ArrayList<Vertex>();
		for(Vertex vertex = target; vertex !=null; vertex = vertex.previous){
			path.add(vertex);
		}
		Collections.reverse(path);
		return path;
		
	}
	
	
	public static void main(String[] args){
		// mark all the vertices 
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex D = new Vertex("D");
        Vertex F = new Vertex("F");
        Vertex K = new Vertex("K");
        Vertex J = new Vertex("J");
        Vertex M = new Vertex("M");
        Vertex O = new Vertex("O");
        Vertex P = new Vertex("P");
        Vertex R = new Vertex("R");
        Vertex Z = new Vertex("Z");

        // set the edges and weight
        A.adjacencies = new Edge[]{ new Edge(M, 8),  new Edge(D, 1) };
        B.adjacencies = new Edge[]{ new Edge(D, 11), new Edge(A, 13) };
        D.adjacencies = new Edge[]{ new Edge(Z, 11) };
        F.adjacencies = new Edge[]{ new Edge(K, 23) };
        K.adjacencies = new Edge[]{ new Edge(O, 40) };
        J.adjacencies = new Edge[]{ new Edge(K, 25) };
        M.adjacencies = new Edge[]{ new Edge(R, 8) };
        O.adjacencies = new Edge[]{ new Edge(K, 40) };
        P.adjacencies = new Edge[]{ new Edge(Z, 18) };
        R.adjacencies = new Edge[]{ new Edge(P, 15) };
        Z.adjacencies = new Edge[]{ new Edge(P, 18), new Edge(A, 3) };


        dijkstra(A); // run Dijkstra
        System.out.println("Distance to " + Z + ": " + Z.minDistance);
        List<Vertex> path = getShortestPathTo(Z);
        for(Vertex a : path){
        	 System.out.println(a.name);
        }
       
	}
	
}
