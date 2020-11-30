package floydWarshall;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WeightedGraph
{
    // Graph subclass of the weighted graph
    private int vert;
    private int[][] adjacencyMatrix;
    private List<Edge> edgeList = new ArrayList<Edge>();

    WeightedGraph(int vert)
    {
        this.vert = vert;
        this.adjacencyMatrix = new int[vert][vert];
        for(int[] row : adjacencyMatrix)
            java.util.Arrays.fill(row, Pathfind.INF);
    }
    // get number of vertices from the graph
    public int getVertices() { return vert; }

    public int[][] getAdjacencyMatrix() { return adjacencyMatrix; }

    // adds an undirected edge between 2 nodes
    public void addEdge(int source, int dest, int weight)
    {
        Edge edge = new Edge(source, dest, weight);
        Edge edgeBack = new Edge(dest, source, weight);
        edgeList.add(edge);
        adjacencyMatrix[source][dest] = weight;
        // double up for undirected
        adjacencyMatrix[dest][source] = weight;
        edgeList.add(edgeBack);

    }

    // prints the edge list
    public void print()
    {
        for (Edge temp : edgeList)
            System.out.println("Vertex: " + temp.source + "->" + temp.dest + " Weight: " + temp.weight);
    }

    // Edge subclass of the weighted graph
    static class Edge
    {
        int source;
        int dest;
        int weight;

        public Edge(int source, int destination, int weight)
        {
            this.source = source;
            this.dest = destination;
            this.weight = weight;
        }
    }
}


