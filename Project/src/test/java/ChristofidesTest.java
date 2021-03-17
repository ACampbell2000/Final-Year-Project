package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ChristofidesTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/qa194.tsp");
        long startTime = System.nanoTime();
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        List<Node> tour = chrAlg.calculateChristofides(testGraph);
        Individual individual = new Individual(tour, testGraph);
        /*for (Node node : tour) {
            System.out.print(node.getIdentifier() + ", ");
        }*/
        System.out.println(individual.toString());
        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;
        System.out.println(totalTime + " nanoseconds");
    }
}

