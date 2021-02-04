package main.java.geneticalgorithm;

import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm {

    private static final int  MAX_GENERATIONS = 1000;
    private static final int NUM_OF_INDIVIDUALS = 100;
    private static final double MUTATION_CHANCE = 0.3;
    private static final double CROSSOVER_CHANCE = 0.5;

    private List<Individual> individuals = new ArrayList<Individual>();
    private Graph graph;

    public GeneticAlgorithm(Graph graph) {
        this.graph = graph;
        generateIndividuals();
    }

    private void generateIndividuals() {
        Random random = new Random();
        for (int i = 0; i < NUM_OF_INDIVIDUALS; i++) {
            List<Node> cities = new ArrayList<>(graph.getNodes());
            List<Node> randTour = new ArrayList<Node>();
            while(!cities.isEmpty()) {
                int next = random.nextInt(cities.size());
                randTour.add(cities.get(next));
                cities.remove(next);
            }
            individuals.add(new Individual(randTour,graph));
        }
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }
}
