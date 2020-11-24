package main.java.tspgraph;

public class Edge {
    Node to, from;
    double cost;

    public Edge() {
        to = new Node();
        from = new Node();
        cost = Double.MAX_VALUE;
    }

    public Edge(Edge edge) {
        this.to = edge.to;
        this.from = edge.from;
        this.cost = edge.cost;
    }
    public Edge(Node from, Node to, double cost) {
        this.to = to;
        this.from = from;
        this.cost = cost;
    }

    public Node getTo() {
        return to;
    }

    public Node getFrom() {
        return from;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "From: " + from.getIdentifier() + " To: " + to.getIdentifier() + " With weight: " + cost;
    }
}
