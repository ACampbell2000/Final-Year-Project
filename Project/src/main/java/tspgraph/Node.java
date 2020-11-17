package main.java.tspgraph;

public class Node {

    int identifier;
    double x_coord;
    double y_coord;

    public Node(int identifier, double x_coord, double y_coord) {
        this.identifier = identifier;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }

    public int getIdentifier() {
        return identifier;
    }

    public double getX_coord() {
        return x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }
}
