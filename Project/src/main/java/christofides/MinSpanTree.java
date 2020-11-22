package main.java.christofides;

import main.java.tspgraph.Edge;
import main.java.tspgraph.Node;

import java.util.List;

public class MinSpanTree {
    private List<Node> nodes;
    private List<Edge> edges;

    public MinSpanTree(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
