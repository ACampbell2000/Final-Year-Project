package main.java.geneticalgorithm;

import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;

import java.util.List;

public class Individual {

    private List<Node> tour;
    public double fitness;
    public Graph partOf;

    public Individual(List<Node> tour, Graph partOf) {
        this.tour = tour;
        this.partOf = partOf;
        calculateFitness();
    }

    public List<Node> getTour() {
        return tour;
    }

    public double getFitness() {
        calculateFitness();
        return fitness;
    }

    public void calculateFitness() {
        double tourLength = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            tourLength += partOf.getEdges()[tour.get(i).getIdentifier()][tour.get(i+1).getIdentifier()];
        }
        tourLength += partOf.getEdges()[tour.get(tour.size()-1).getIdentifier()][tour.get(0).getIdentifier()];
        fitness = tourLength;
    }

    @Override
    public String toString() {
        return "Tour: " + getTour() + " Length: " + getFitness();
    }
}
