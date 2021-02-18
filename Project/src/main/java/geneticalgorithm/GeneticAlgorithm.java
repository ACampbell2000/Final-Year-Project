package main.java.geneticalgorithm;

import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {

    private static final int  MAX_GENERATIONS = 10000;
    private static final int NUM_OF_INDIVIDUALS = 1000;
    private static final double MUTATION_CHANCE = 0.01;
    private static final double CROSSOVER_CHANCE = 0.8;

    private List<Individual> individuals = new ArrayList<Individual>();
    private Graph graph;

    public GeneticAlgorithm(Graph graph) {
        this.graph = graph;
        generateIndividuals();
        for (int i = 0; i < MAX_GENERATIONS; i++)
            generationLoop();
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
        individuals.sort(Comparator.comparingDouble(Individual::getFitness)); //a lower fitness is better

    }

    private void generationLoop() {
        List<Individual> parents = new ArrayList<>(individuals.subList(0,NUM_OF_INDIVIDUALS));
        List<Individual> children = new ArrayList<>();
        for(int i = 0; i<parents.size()/2; i++) {
            List<Individual> temp = orderCrossover(individuals.get(ThreadLocalRandom.current().nextInt(parents.size())),individuals.get(ThreadLocalRandom.current().nextInt(parents.size())));
            for(int j=0; j<temp.size();j++) {
                temp.set(j,RSM_Mutation(temp.get(j)));
            }
            children.addAll(temp);
        }
        children.sort(Comparator.comparingDouble(Individual::getFitness));

        for(int i = individuals.size()-1; i>=0; i--) {
            boolean added = false;
            while (!children.isEmpty() && !added) {
                if (individuals.get(i).getFitness() > children.get(children.size() - 1).getFitness()) {
                    individuals.set(i, children.get(children.size() - 1));
                    added = true;
                }
                children.remove(children.size()-1);
            }
        }
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        System.out.println("Best Individual in generation: " + individuals.get(0).toString());
    }

    public List<Individual> orderCrossover(Individual parent1, Individual parent2) {
        if(CROSSOVER_CHANCE >= ThreadLocalRandom.current().nextDouble()) {
            int startCrossover = ThreadLocalRandom.current().nextInt(1, parent1.getTour().size() - 1);
            int endCrossover = ThreadLocalRandom.current().nextInt(1, parent1.getTour().size() - 1) + 1;
            while (endCrossover == startCrossover)
                endCrossover = ThreadLocalRandom.current().nextInt(1, parent1.getTour().size() - 1);

            if (startCrossover > endCrossover) { //this is so that startCrossover is always the smaller of the two, for more logical usage of the variable names.
                int temp = startCrossover;
                startCrossover = endCrossover;
                endCrossover = temp;
            }

            List<Node> parent1SubTour = new ArrayList<>(parent1.getTour().subList(startCrossover, endCrossover)); //getting the inclusive subtour randomly chosen
            List<Node> parent2SubTour = new ArrayList<>(parent2.getTour().subList(startCrossover, endCrossover));


            List<Node> parent1TransposedTour = new ArrayList<>(parent1.getTour().subList(endCrossover, parent1.getTour().size())); //getting the looped back tour
            parent1TransposedTour.addAll(parent1.getTour().subList(0, endCrossover));
            parent1TransposedTour.removeAll(parent2SubTour);


            List<Node> parent2TransposedTour = new ArrayList<>(parent2.getTour().subList(endCrossover, parent2.getTour().size()));
            parent2TransposedTour.addAll(parent2.getTour().subList(0, endCrossover));
            parent2TransposedTour.removeAll(parent1SubTour);

            List<Node> child1 = new ArrayList<>(parent2TransposedTour.subList(parent2.getTour().size() - endCrossover, parent2TransposedTour.size()));
            child1.addAll(parent2TransposedTour.subList(0, parent2.getTour().size() - endCrossover));
            child1.addAll(startCrossover, parent1SubTour);

            List<Node> child2 = new ArrayList<>(parent1TransposedTour.subList(parent1.getTour().size() - endCrossover, parent1TransposedTour.size()));
            child2.addAll(parent1TransposedTour.subList(0, parent1.getTour().size() - endCrossover));
            child2.addAll(startCrossover, parent2SubTour);


            List<Individual> children = new ArrayList<Individual>();
            children.add(new Individual(child1, graph));
            children.add(new Individual(child2, graph));

            return children;
        } else {
            List<Individual> temp = new ArrayList<>();
            temp.add(parent1);
            temp.add(parent2);
            return temp;
        }
    }

    public Individual RSM_Mutation(Individual individual) {
        if(MUTATION_CHANCE >= ThreadLocalRandom.current().nextDouble()) {
            List<Node> tour = individual.getTour();
            int startMutation = ThreadLocalRandom.current().nextInt(tour.size());
            int endMutation = ThreadLocalRandom.current().nextInt(tour.size());
            if (startMutation == endMutation)
                endMutation = ThreadLocalRandom.current().nextInt(tour.size());

            if (startMutation > endMutation) {
                int temp = startMutation;
                startMutation = endMutation;
                endMutation = temp;
            }

            while (!(startMutation >= endMutation)) {
                Node temp = tour.get(startMutation);
                tour.set(startMutation, tour.get(endMutation));
                tour.set(endMutation, temp);
                startMutation++;
                endMutation--;
            }
            return new Individual(tour, graph);
        } else
            return individual;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }
}
