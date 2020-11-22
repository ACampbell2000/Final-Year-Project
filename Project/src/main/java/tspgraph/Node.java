package main.java.tspgraph;

import java.util.Comparator;

public class Node {

    int identifier;
    double x_coord;
    double y_coord;

    public Node() {}

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

    @Override
    public String toString() {
        return "City Number: " + identifier + " X: " + x_coord + " Y: " + y_coord;
    }
}
