package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.christofides.MinSpanTree;
import main.java.tspgraph.Edge;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class MinSpanTreeTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/wi29.tsp");
        ChristofidesAlgorithm minTree = new ChristofidesAlgorithm();
        MinSpanTree minSpanTree = minTree.calculateMinimumSpanningTree(testGraph);
        double totalLength = 0;
        for (Edge edge: minSpanTree.getEdges()) {
            System.out.println("From: " + edge.getFrom().getIdentifier() +  " To: " + edge.getTo().getIdentifier() + " Cost: " + edge.getCost());
            totalLength += edge.getCost();
        }
        System.out.println(totalLength);
    }
}
