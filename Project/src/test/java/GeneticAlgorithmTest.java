package test.java;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class GeneticAlgorithmTest {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/zi929.tsp");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;
        System.out.println(totalTime + " nanoseconds");
    }
}
