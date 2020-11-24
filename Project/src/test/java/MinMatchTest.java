package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.christofides.MinSpanTree;
import main.java.tspgraph.Edge;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class MinMatchTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/wi29.tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        MinSpanTree minSpanTree = chrAlg.calculateMinimumSpanningTree(testGraph);
        List<Node> oddNodes = chrAlg.calculateOddNodes(minSpanTree);
        MinSpanTree subGraph = chrAlg.calculateSubGraph(oddNodes,testGraph);
        MinSpanTree minMatchGraph = chrAlg.calculateMinimumMatch(subGraph);
        for (Edge edge : minMatchGraph.getEdges()) {
            System.out.println(edge.toString());
        }
    }
}
