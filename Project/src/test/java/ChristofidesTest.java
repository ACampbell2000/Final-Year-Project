package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.Arrays;
import java.util.List;

public class ChristofidesTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/testMinTree.tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        List<Node> tour = chrAlg.calculateChristofides(testGraph);
        for (Node node : tour) {
            System.out.print(node.getIdentifier() + ", ");
        }
    }
}

