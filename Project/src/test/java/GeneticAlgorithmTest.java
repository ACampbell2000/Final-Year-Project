package test.java;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

import java.util.List;

public class GeneticAlgorithmTest {
    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/qa194.tsp");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
    }
}
