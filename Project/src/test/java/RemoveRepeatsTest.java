package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.christofides.MinSpanTree;
import main.java.tspgraph.Edge;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class RemoveRepeatsTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/test.tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        MinSpanTree minSpanTree = chrAlg.calculateMinimumSpanningTree(testGraph);
        List<Node> oddNodes = chrAlg.calculateOddNodes(minSpanTree);
        MinSpanTree subGraph = chrAlg.calculateSubGraph(oddNodes,testGraph);
        MinSpanTree minMatchGraph = chrAlg.calculateMinimumMatch(subGraph);
        MinSpanTree unionGraph = chrAlg.union(minSpanTree,minMatchGraph);
        List<Node> pathRepeats = chrAlg.calculateEulerCycleWithRepeats(unionGraph);
        List<Node> completePath = chrAlg.removeRepeatsFromCycle(pathRepeats);
        for (Node node : completePath) {
            System.out.println(node.getIdentifier());
        }
    }
}
