package test.java;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

public class GeneticAlgorithmTestAll {
    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();
        String[] datasets = {"qa194.tsp","zi929.tsp","mu1979.tsp"};
        for (String dataset : datasets) {
            Graph testGraph = test.buildGraph("dataset/"+dataset);
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
            System.out.println(dataset+geneticAlgorithm.getBest().toString());
        }
    }
}
