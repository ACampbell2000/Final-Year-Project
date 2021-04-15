package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MultChristofidesAndGeneticTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/mu1979.tsp");
        long startTime = System.nanoTime();
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        List<List<Node>> cycles = chrAlg.calculateMultChristofides(testGraph);
        List<Individual> individuals = new ArrayList<>();
        for (List<Node> cycle : cycles) {
            individuals.add(new Individual(cycle,testGraph));
        }
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        /*try {
            individuals = individuals.subList(0, GeneticAlgorithm.NUM_OF_INDIVIDUALS / 10);
        } catch (Exception ignored) {}
        for (Individual individual : individuals) {
            System.out.println(individual);
        }*/
        System.out.println("-------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph, individuals);
        System.out.println(geneticAlgorithm.getBest().toString());
        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;

        System.out.println(totalTime + " nanoseconds");
    }
}
