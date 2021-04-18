package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;


public class ChristofidesAndGeneticTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/qa194.tsp");
        long startTime = System.nanoTime();
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        Individual individual = new Individual(chrAlg.calculateChristofides(testGraph), testGraph);
        System.out.println(individual.toString());
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph, individual);
        System.out.println(geneticAlgorithm.getBest().toString());
        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;

        System.out.println(totalTime + " nanoseconds");
    }
}
