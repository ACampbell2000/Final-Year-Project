package main.java.tspgraph;

public class Edge {
    int to, from;
    double cost;

    public Edge(int from, int to, double cost) {
        this.to = to;
        this.from = from;
        this.cost = cost;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public double getCost() {
        return cost;
    }
}
