package test.java;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

public class GeneticAlgorithmTestChoose {
    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/"+args[0]+".tsp");
        long startTime = System.nanoTime();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
        System.out.println(geneticAlgorithm.getBest().toString());
        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;
        System.out.println(totalTime + " nanoseconds");
    }
}
