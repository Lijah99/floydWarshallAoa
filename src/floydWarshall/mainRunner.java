package floydWarshall;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;

import java.util.Scanner;

public class mainRunner
{
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);
        String menu =
                "1) Manual input\n" +
                        "2) Run Example\n" +
                        "3) Run Algorithm Unit Tests\n";
        String input = "-1";
        while (input != "0") {
            try {
                System.out.print(menu);
                System.out.print("\nChoice: ");
                input = cin.next();
                System.out.println();
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        int tempVert, tempSource, tempDest, tempWeight;
                        System.out.print("Enter # of vertices you'd like to have: ");
                        tempVert = cin.nextInt();
                        WeightedGraph graph = new WeightedGraph(tempVert);

                        String keepAdding = "y";
                        while(keepAdding.toLowerCase().equals("y") || keepAdding.toLowerCase().equals("yes"))
                        {
                            System.out.print("Enter source node, dest node, and weight value between nodes for vertice (input format is: # # #) - ");
                            tempSource = cin.nextInt(); tempDest = cin.nextInt(); tempWeight = cin.nextInt();

                            System.out.print("Directed or undirected edge (d or u)? ");
                            String direction = cin.next();
                            if(direction.toLowerCase().equals("d"))
                                graph.addDirectedEdge(tempSource, tempDest, tempWeight);
                            else if(direction.toLowerCase().equals("u"))
                                graph.addUndirectedEdge(tempSource, tempDest, tempWeight);
                            else
                                System.out.println("Wrong choice, please try again");

                            System.out.print("Would you like to add another connection (y/n or yes/no)? ");
                            keepAdding = cin.next();
                        }
                        Pathfind pathfinder = new Pathfind(graph);
                        graph.print();
                        pathfinder.floydWarshall();

                        System.out.print("Please enter two nodes to determine their shortest path: ");
                        var source = cin.nextInt(); var dest = cin.nextInt();
                        var path = pathfinder.constructPath(source, dest);
                        if(path != null) {
                            System.out.print("Path: ");
                            path.forEach((x) -> System.out.print("->" + x));
                            System.out.println();
                        }
                        break;
                    case "2":
                        test1();
                        break;
                    case "3":
                        JUnitCore junit = new JUnitCore();
                        Result result = junit.run(PathfindTest.class);
                        if(result.wasSuccessful())
                            System.out.println("Unit tests run for Floyd Warshall Algorithm. They passed successfully!\n");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;

                }
                input = "-1";
            } catch (Exception e) {
                System.out.println("Invalid option");
                cin.nextLine();
            }
        }
    }

    public static void test1() {
        int vertices = 6;
        WeightedGraph graph = new WeightedGraph(vertices);
        graph.addDirectedEdge(0, 1, 4);
        graph.addDirectedEdge(0, 2, 3);
        graph.addDirectedEdge(1, 3, 2);
        graph.addDirectedEdge(1, 2, 5);
        graph.addDirectedEdge(2, 3, 7);
        graph.addDirectedEdge(3, 4, 2);
        graph.addDirectedEdge(4, 0, 4);
        graph.addDirectedEdge(4, 1, 4);
        graph.addDirectedEdge(4, 5, 6);
        Pathfind pathfinder = new Pathfind(graph);
        graph.print();

        pathfinder.floydWarshall();
        var path = pathfinder.constructPath(5, 1);

        if(path != null)
            path.forEach((x) -> System.out.println("nodes in path " + x));
        System.out.println();
    }
}
