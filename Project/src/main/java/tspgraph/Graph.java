package main.java.tspgraph;

import java.util.List;

public class Graph {

    private List<Node> nodes;
    private double[][] edges;


    public Graph(List<Node> nodes, double[][] edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public double[][] getEdges() {
        return edges;
    }
}
