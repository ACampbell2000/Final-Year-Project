package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.ArrayList;
import java.util.List;

public class PopulationTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/test.tsp");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
        List<Individual> individuals = geneticAlgorithm.getIndividuals();
        for (Individual individual : individuals) {
            System.out.println(individual.getTour());
        }
    }
}
