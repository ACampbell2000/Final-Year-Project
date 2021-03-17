package main.java.tspgraph;

import org.jgrapht.GraphType;

import java.util.List;

public class Graph {

    private List<Node> nodes;
    private double[][] edges;
    private String name;


    public Graph(List<Node> nodes, double[][] edges, String name) {
        this.nodes = nodes;
        this.edges = edges;
        this.name = name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public double[][] getEdges() {
        return edges;
    }

    public String getName() {
        return name;
    }
}
