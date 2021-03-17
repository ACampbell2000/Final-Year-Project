package test.java.trajectory;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

public class GeneticAlgorithmTrajectoryTest {
    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/qa194.tsp");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph,200);
    }
}
