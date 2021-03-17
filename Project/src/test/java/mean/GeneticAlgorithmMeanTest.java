package test.java.mean;

import main.java.geneticalgorithm.GeneticAlgorithm;
import main.java.geneticalgorithm.Individual;
import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithmMeanTest {
    public static void main(String[] args) {
        List<Individual> endSolutions = new ArrayList<Individual>();
        List<Long> endTimes = new ArrayList<Long>();
        Tsplibconverter test = new Tsplibconverter();
        Graph testGraph = test.buildGraph("dataset/qa194.tsp");

        for(int i = 0; i<30;i++) {
            long startTime = System.nanoTime();
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(testGraph);
            System.out.println(geneticAlgorithm.getBest().toString());
            endSolutions.add(geneticAlgorithm.getBest());
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            endTimes.add(totalTime);
        }
        long meanSolution = 0;
        long meanTime = 0;
        for(int i = 0; i<endSolutions.size(); i++) {
            meanSolution += endSolutions.get(i).fitness;
            meanTime += endTimes.get(i);
        }
        meanSolution /= endSolutions.size();
        meanTime /= endTimes.size();

        long stanDevSolution = 0;
        for (Individual endSolution : endSolutions) {
            stanDevSolution += Math.pow(endSolution.fitness - meanSolution, 2);
        }
        stanDevSolution /= endSolutions.size();
        stanDevSolution = (long) Math.sqrt(stanDevSolution);

        long stanDevTime = 0;
        for (Long time: endTimes) {
            stanDevTime += Math.pow(time-meanTime,2);
        }
        stanDevTime /= endTimes.size();
        stanDevTime = (long) Math.sqrt(stanDevTime);
        System.out.println("Mean solution: " + meanSolution + " with standard deviation " + stanDevSolution);
        System.out.println("Mean time: " + meanTime + " with standard deviation " + stanDevTime);
    }
}
