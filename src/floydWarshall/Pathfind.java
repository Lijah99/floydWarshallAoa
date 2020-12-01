package floydWarshall;

import java.lang.reflect.Array;
import java.util.Vector;

public class Pathfind {
    // all pairs shortest path O(V^3)
    final static int INF = 99999;
    private int dist[][];
    private int next[][];
    private int tempAdjMatrix[][];
    private int vertices;
    private WeightedGraph graph;

    // pathfind class constructor
    Pathfind(WeightedGraph graph)
    {
        // grab weighted graph information and store
        this.graph = graph;
        tempAdjMatrix = graph.getAdjacencyMatrix();
        vertices = graph.getVertices();
        // grab and set dist and next matrices
        dist = new int [vertices][vertices];
        next = new int [vertices][vertices];
        for(int[] row : dist)
            java.util.Arrays.fill(row, INF);
        for(int[] row : next)
            java.util.Arrays.fill(row, -1);
    }

    // map is the adjacency matrix of weights, takes map and vertices
    void floydWarshall()
    {
        // path reconstruction
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
            {
                // set base distance to adj matrix
                dist[i][j] = tempAdjMatrix[i][j];
                // if there is no connection, set next connect to -1
                if (tempAdjMatrix[i][j] == INF)
                    next[i][j] = -1;
                // else set next to direct connection
                else
                    next[i][j] = j;
            }
        // distance O(n^3) algorithm for finding all pairs shortest path
        for (int k = 0; k < vertices; k++)
            for (int i = 0; i < vertices; i++)
                for (int j = 0; j < vertices; j++)
                {
                    // if no connection, ignore
                    if (dist[i][k] == INF || dist[k][j] == INF)
                        continue;
                    // if there is a smaller distance found, set dist with new smaller dist
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
        
    }

    // construct path from u to v if possible using next
    public Vector<Integer> constructPath(int u, int v)
    {
        Vector<Integer> path = new Vector<Integer>();
        if (next[u][v] == -1)
        {
            System.out.println("Won't work, no valid path");
            return null;
        }

        // add initial node, keep adding until you reach the end
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
        // check to see if the object is an array
        if (!object.getClass().isArray())
            return 1;

        int size = 0;
        for (int i = 0; i < Array.getLength(object); i++)
            size += size(Array.get(object, i));
        return size;
    }

    }
