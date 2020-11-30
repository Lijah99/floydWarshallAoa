package floydWarshall;

public class mainRunner
{
    public static void main(String[] args)
    {
        int vertices = 6;
        WeightedGraph graph = new WeightedGraph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 0, 4);
        graph.addEdge(4, 1, 4);
        graph.addEdge(4, 5, 6);
        Pathfind pathfinder = new Pathfind(graph);
        graph.print();

        pathfinder.floydWarshall(graph);
        var path = pathfinder.constructPath(5, 1);

        if(path != null)
            path.forEach((x) -> System.out.println("nodes in path " + x));
    }
}
