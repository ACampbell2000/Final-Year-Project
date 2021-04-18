package test.java.trajectory;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MultChristofidesAndGeneticTrajectoryTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/mu1979.tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        List<List<Node>> cycles = chrAlg.calculateMultChristofides(testGraph);
        List<Individual> individuals = new ArrayList<>();
        for (List<Node> cycle : cycles) {
            individuals.add(new Individual(cycle,testGraph));
        }
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        GeneticAlgorithm geneticAlgorithmChr = new GeneticAlgorithm(testGraph, individuals, 200);
    }
}
