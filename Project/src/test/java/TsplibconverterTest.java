package test.java;

import main.java.tspgraph.Graph;
import main.java.tsplibreader.Tsplibconverter;

import java.util.Arrays;

public class TsplibconverterTest {

    public static void main(String[] args) {
        Tsplibconverter test = new Tsplibconverter();

        Graph testGraph = test.buildGraph("dataset/test.tsp");
        System.out.println(testGraph.getNodes().toString());
        System.out.println(Arrays.deepToString(testGraph.getEdges()));
    }

}
