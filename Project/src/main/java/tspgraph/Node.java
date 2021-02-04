package main.java.tspgraph;


public class Node {

    int identifier;
    double x_coord;
    double y_coord;

    public Node() {
        this.identifier = 0;
        x_coord = 0;
        y_coord = 0;
    }

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

    public String getData() { return "City Number: " + identifier + " X: " + x_coord + " Y: " + y_coord; }

    @Override
    public String toString() {
        return String.valueOf(identifier);
    }
}
