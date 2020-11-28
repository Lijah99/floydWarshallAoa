import java.lang.reflect.Array;

public class Pathfind {
    //all pairs shortest path O(V^3)
    final static int INF = 99999, V = 4;

    void floydWarshall(int map[][])
    {
        int V = size(map);
        int dist[][] = new int [V][V];
        int next
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
