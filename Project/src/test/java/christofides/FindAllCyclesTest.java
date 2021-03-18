package test.java.christofides;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.christofides.MinSpanTree;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class FindAllCyclesTest {
    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/mu1979.tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        MinSpanTree minSpanTree = chrAlg.calculateMinimumSpanningTree(testGraph);
        List<Node> oddNodes = chrAlg.calculateOddNodes(minSpanTree);
        MinSpanTree subGraph = chrAlg.calculateSubGraph(oddNodes,testGraph);
        MinSpanTree minMatchGraph = chrAlg.calculateMinimumMatch(subGraph);
        MinSpanTree unionGraph = chrAlg.union(minSpanTree,minMatchGraph);
        List<Node> pathRepeats = chrAlg.calculateEulerCycleWithRepeats(unionGraph);
        List<List<Node>> allPaths = chrAlg.findAllPossibleCycles(pathRepeats);
        for (List<Node> path : allPaths) {
            System.out.println(new Individual(path,testGraph).toString());
        }
    }
}
