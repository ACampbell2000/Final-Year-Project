package test.java.trajectory;

import main.java.christofides.ChristofidesAlgorithm;
import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;


public class ChristofidesAndGeneticTrajectoryTestChoose {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/"+args[0]+".tsp");
        ChristofidesAlgorithm chrAlg = new ChristofidesAlgorithm();
        Individual individual = new Individual(chrAlg.calculateChristofides(testGraph), testGraph);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph, individual,400);

    }
}
