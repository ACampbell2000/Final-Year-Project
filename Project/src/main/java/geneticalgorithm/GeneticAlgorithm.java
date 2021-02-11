package main.java.geneticalgorithm;

import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
        for (int i = 0; i < NUM_OF_INDIVIDUALS; i++) {
            List<Node> cities = new ArrayList<>(graph.getNodes());
            List<Node> randTour = new ArrayList<Node>();
            while(!cities.isEmpty()) {
                int next = ThreadLocalRandom.current().nextInt(cities.size());
                randTour.add(cities.get(next));
                cities.remove(next);
            }
            individuals.add(new Individual(randTour,graph));
        }
    }

    public List<Individual> orderCrossover(Individual parent1, Individual parent2) {
        int startCrossover = ThreadLocalRandom.current().nextInt(1,parent1.getTour().size()-1);
        int endCrossover = ThreadLocalRandom.current().nextInt(1, parent1.getTour().size()-1)+1;
        while (endCrossover == startCrossover)
            endCrossover = ThreadLocalRandom.current().nextInt(1,parent1.getTour().size()-1);

        if(startCrossover > endCrossover) { //this is so that startCrossover is always the smaller of the two, for more logical usage of the variable names.
            int temp = startCrossover;
            startCrossover = endCrossover;
            endCrossover = temp;
        }
        System.out.println("startCrossover: " + startCrossover + " endCrossover: " + (endCrossover-1));

        List<Node> parent1SubTour = new ArrayList<>(parent1.getTour().subList(startCrossover,endCrossover)); //getting the inclusive subtour randomly chosen
        System.out.println("Parent1 subtour: " + parent1SubTour);
        List<Node> parent2SubTour = new ArrayList<>(parent2.getTour().subList(startCrossover,endCrossover));
        System.out.println("Parent2 subtour: " + parent2SubTour);


        List<Node> parent1TransposedTour = new ArrayList<>(parent1.getTour().subList(endCrossover,parent1.getTour().size())); //getting the looped back tour
        parent1TransposedTour.addAll(parent1.getTour().subList(0,endCrossover));
        parent1TransposedTour.removeAll(parent2SubTour);
        System.out.println("Parent1 transposed tour: " + parent1TransposedTour);


        List<Node> parent2TransposedTour = new ArrayList<>(parent2.getTour().subList(endCrossover,parent2.getTour().size()));
        parent2TransposedTour.addAll(parent2.getTour().subList(0,endCrossover));
        parent2TransposedTour.removeAll(parent1SubTour);
        System.out.println("Parent2 transposed tour: " + parent2TransposedTour);

        List<Node> child1 = new ArrayList<>(parent2TransposedTour.subList(parent2.getTour().size()-endCrossover,parent2TransposedTour.size()));
        child1.addAll(parent2TransposedTour.subList(0,parent2.getTour().size()-endCrossover));
        child1.addAll(startCrossover,parent1SubTour);
        System.out.println("child1: " + child1);


        List<Node> child2 = new ArrayList<>(parent1TransposedTour.subList(parent1.getTour().size()-endCrossover,parent1TransposedTour.size()));
        child2.addAll(parent1TransposedTour.subList(0,parent1.getTour().size()-endCrossover));
        child2.addAll(startCrossover,parent2SubTour);
        System.out.println("child2: " + child2);


        List<Individual> children = new ArrayList<Individual>();
        children.add(new Individual(child1, graph));
        children.add(new Individual(child2, graph));

        return children;
    }

    public Individual RSM_Mutation(Individual individual) {
        List<Node> tour = individual.getTour();
        int startMutation = ThreadLocalRandom.current().nextInt(tour.size());
        int endMutation = ThreadLocalRandom.current().nextInt(tour.size());
        if(startMutation == endMutation)
            endMutation = ThreadLocalRandom.current().nextInt(tour.size());

        if(startMutation > endMutation) {
            int temp = startMutation;
            startMutation = endMutation;
            endMutation = temp;
        }

        System.out.println("Start: " + startMutation + " End: " + endMutation);
        while(!(startMutation >= endMutation)) {
            Node temp = tour.get(startMutation);
            tour.set(startMutation,tour.get(endMutation));
            tour.set(endMutation,temp);
            startMutation++;
            endMutation--;
        }
        System.out.println("Mutated" + tour);
        return new Individual(tour,graph);
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }
}
