package floydWarshall;


import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;


public class PathfindTest {

    @Test
    public void floydWarshall() {
        WeightedGraph graph = new WeightedGraph(3);
        graph.addUndirectedEdge(0, 1, 1);
        graph.addUndirectedEdge(1, 2, 2);
        graph.addUndirectedEdge(0, 2, 5);

        Pathfind pathfinder = new Pathfind(graph);
        pathfinder.floydWarshall();
        var test = pathfinder.constructPath(0, 2);
        Vector<Integer> answers = new Vector<Integer>();
        answers.add(0);
        answers.add(1);
        answers.add(2);
        Assert.assertEquals(test, answers);
    }

    @Test
    public void constructPath() {
        return;
    }
}