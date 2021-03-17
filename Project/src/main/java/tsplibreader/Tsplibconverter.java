package main.java.tsplibreader;

import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tsplibconverter {

    private List<Node> nodes = new ArrayList<>();
    private double[][] edges;
    private String name;

    public Graph buildGraph(String pathToFile) {
        name = pathToFile.substring(pathToFile.indexOf("/")+1,pathToFile.indexOf("."));
        try {
            File tspLibFile = new File(pathToFile);
            Scanner reader = new Scanner(tspLibFile);
            String nextLine;
            do {
                nextLine = reader.nextLine();
            } while (!nextLine.contains("NODE_COORD_SECTION"));

            while(!(nextLine = reader.nextLine()).contains("EOF")) {
                String[] sections = nextLine.split(" ");
                Node node = new Node(Integer.parseInt(sections[0])-1, Double.parseDouble(sections[1]), Double.parseDouble(sections[2]));
                nodes.add(node);
            }

            calculateEdges();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Graph(nodes, edges, name);
    }

    private void calculateEdges() {
        edges = new double[nodes.size()][nodes.size()];
        for(int i = 0; i < nodes.size(); i++) {
            for(int j = i; j < nodes.size(); j++) {
                edges[i][j] = euclidianDistance(nodes.get(i),nodes.get(j));
                edges[j][i] = edges[i][j];
            }
        }
    }

    public static double euclidianDistance(Node a, Node b) {
        double x1 = a.getX_coord();
        double x2 = b.getX_coord();
        double y1 = a.getY_coord();
        double y2 = b.getY_coord();

        return Math.sqrt((Math.pow(x1 - x2, 2)) + (Math.pow(y1 - y2, 2)));
    }


}
