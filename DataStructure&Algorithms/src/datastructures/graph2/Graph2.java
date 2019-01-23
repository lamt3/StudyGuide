package datastructures.graph2;

public class Graph2 {

	private final int MAX_VERTS = 20;
	private Vertex2 vertexList[]; // array of verticies
	private int adjMat[][]; // adjacency matrix
	private int nVerts;

	public Graph2() {
		vertexList = new Vertex2[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int j = 0; j < MAX_VERTS; j++) {
			for (int k = 0; k < MAX_VERTS; k++) {
				adjMat[j][k] = 0;// matrix to 0
			}
		}
	}

	public void addVertex(char lab) // argument is label

	{

		vertexList[nVerts++] = new Vertex2(lab);

	}

	// -------------------------------------------------------------

	public void addEdge(int start, int end)

	{

		adjMat[start][end] = 1;

		adjMat[end][start] = 1;

	}

	// -------------------------------------------------------------

	public void displayVertex(int v)

	{

		System.out.print(vertexList[v].label);

	}

}
