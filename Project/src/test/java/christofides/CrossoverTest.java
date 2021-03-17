package test.java.christofides;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class CrossoverTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/test.tsp");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
        Individual parent1 = geneticAlgorithm.getIndividuals().get(0);
        Individual parent2 = geneticAlgorithm.getIndividuals().get(1);
        System.out.println("Original: " + parent1.getTour());
        System.out.println("Original: " + parent2.getTour());
        List<Individual> children = geneticAlgorithm.orderCrossover(parent1,parent2);

    }
}
