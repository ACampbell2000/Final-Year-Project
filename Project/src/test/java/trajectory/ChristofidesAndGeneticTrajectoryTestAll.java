package test.java.trajectory;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;


public class ChristofidesAndGeneticTrajectoryTestAll {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();
        String[] datasets = {"qa194.tsp","zi929.tsp","mu1979.tsp"};
        for (String dataset : datasets) {

            Graph testGraph = test.buildGraph("dataset/"+dataset);
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph,400);
            ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
            Individual individual = new Individual(chrAlg.calculateChristofides(testGraph), testGraph);
            GeneticAlgorithm geneticAlgorithmChr = new GeneticAlgorithm(testGraph, individual, 400);
        }
    }
}
