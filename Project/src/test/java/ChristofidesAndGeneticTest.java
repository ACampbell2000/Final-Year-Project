package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;


public class ChristofidesAndGeneticTest {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/zi929.tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        Individual individual = new Individual(chrAlg.calculateChristofides(testGraph), testGraph);
        System.out.println(individual.toString());
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph, individual);
        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;
        System.out.println(totalTime + " nanoseconds");
    }
}
