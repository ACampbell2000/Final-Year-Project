package test.java;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;

import java.util.Arrays;
import java.util.List;

public class IndividualTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/test.tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        List<Node> tour = chrAlg.calculateChristofides(testGraph);
        tour.remove(tour.size()-1);
        Individual christofidesIndividual = new Individual(tour,testGraph);
        System.out.println("Tour: " + christofidesIndividual.getTour() + " Length: " + christofidesIndividual.getFitness());
    }
}

