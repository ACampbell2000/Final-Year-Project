package test.java.genetic;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class MutationTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/test.tsp");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
        Individual individual = geneticAlgorithm.getIndividuals().get(0);
        System.out.println("Original: " + individual.getTour());
        for(int i = 0; i < 10; i++) {
            Individual mutatedInvidual = geneticAlgorithm.RSM_Mutation(individual);
        }
    }
}
