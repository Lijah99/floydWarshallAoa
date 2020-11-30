package floydWarshall;

import java.lang.reflect.Array;
import java.util.Vector;

public class Pathfind {
    // all pairs shortest path O(V^3)
    final static int INF = 99999;
    private int dist[][];
    private int next[][];
    public int V;

    Pathfind(WeightedGraph graph)
    {
        V = graph.getVertices();
        System.out.println("VERTICES:" + V);
        dist = new int [V][V];
        next = new int [V][V];
        for(int[] row : dist)
            java.util.Arrays.fill(row, INF);
        for(int[] row : next)
            java.util.Arrays.fill(row, -1);
    }


    // map is the adjacency matrix of weights, takes map and vertices
    void floydWarshall(WeightedGraph graph)
    {
        // path reconstruction
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
            {
                dist[i][j] = graph.adjacencyMatrix[i][j];
                if (graph.adjacencyMatrix[i][j] == INF)
                    next[i][j] = -1;
                else
                    next[i][j] = j;
            }

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                {
                    //if (dist[i][k] + dist[k][j] < dist[i][j])
                    // old    dist[i][j] = dist[i][k] + dist[k][j];

                    if (dist[i][k] == INF || dist[k][j] == INF)
                        continue;

                    if (dist[i][j] > dist[i][k] + dist[k][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
        
    }

    public Vector<Integer> constructPath(int u, int v)
    {
        if (next[u][v] == -1)
        {
            System.out.println("Won't work, no valid path");
            return null;
        }

        // Storing the path in a vector
        Vector<Integer> path = new Vector<Integer>();
        path.add(u);

        while (u != v)
        {
            u = next[u][v];
            path.add(u);
        }
        return path;
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
