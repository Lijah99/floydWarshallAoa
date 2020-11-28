package floydWarshall;

import java.lang.reflect.Array;

public class Pathfind {
    // all pairs shortest path O(V^3)
    final static int INF = 99999;

    // map is the adjacency matrix of weights, takes map and vertices
    void floydWarshall(WeightedGraph graph)
    {
        int V = graph.getVertices();
        System.out.println("VERTICES:" + V);
        int dist[][] = new int [V][V];
        int next[][] = new int [V][V];
        java.util.Arrays.fill(dist, INF);
        java.util.Arrays.fill(next, null);

        // path reconstruction
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                dist[i][j] = graph.adjacencyMatrix[i][j];

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        
    }


    // method to get the number of vertices(elements) in an array/map
    public static int size(Object object)
    {
        if (!object.getClass().isArray())
            return 1;

        int size = 0;
        for (int i = 0; i < Array.getLength(object); i++)
            size += size(Array.get(object, i));
        return size;
    }

    }
