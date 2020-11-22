package main.java.christofides;

import main.java.tspgraph.Edge;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChristofidesAlgorithm {

    public void calculateChristofides(Graph fullGraph) {
        MinSpanTree minSpanTree = calculateMinimumSpanningTree(fullGraph);
        List<Node> oddNodes = calculateOddNodes(minSpanTree);
    }

    public MinSpanTree calculateMinimumSpanningTree(Graph fullGraph) {
        List<Integer> nodesReached = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        nodesReached.add(fullGraph.getNodes().get(0).getIdentifier());
        while (nodesReached.size() != fullGraph.getNodes().size()) {
            Edge minLength = new Edge(0,0,Double.MAX_VALUE);
            for (int node : nodesReached) {
                for (int i = 0; i < fullGraph.getNodes().size(); i++) {
                    if (fullGraph.getEdges()[node][i] < minLength.getCost() && !nodesReached.contains(i)) {
                        minLength = new Edge(node, i, fullGraph.getEdges()[i][node]);
                    }
                }
            }
            nodesReached.add(minLength.getTo());
            edges.add(minLength);
        }
        return new MinSpanTree(fullGraph.getNodes(), edges);
    }

    public List<Node> calculateOddNodes(MinSpanTree minSpanTree) {
        List<Node> oddNodes = new ArrayList<>();
        for (Node node : minSpanTree.getNodes()) {
            int numOfEdges = 0;
            for(int i = 0; i < minSpanTree.getEdges().size(); i++) {
                if (minSpanTree.getEdges().get(i).getFrom() == node.getIdentifier() || minSpanTree.getEdges().get(i).getTo() == node.getIdentifier())
                    numOfEdges++;
            }
            if (numOfEdges % 2 != 0) oddNodes.add(node);
        }

        oddNodes.sort((node1, node2) -> {
            if (node1.getIdentifier() < node2.getIdentifier())
                return -1;
            else
                return 1;
        });

        return oddNodes;
    }

    public MinSpanTree calculateSubGraph(List<Node> oddNodes, Graph fullGraph) {
        List<Edge> oddEdges = new ArrayList<>();
        List<Node> leftToUse;
        leftToUse = oddNodes;
        for(int i = 0; i < oddNodes.size(); i++) {
            leftToUse.remove(i);
            for (Node node : leftToUse) {
                oddEdges.add(new Edge(oddNodes.get(i).getIdentifier(), node.getIdentifier(), fullGraph.getEdges()[oddNodes.get(i).getIdentifier()][node.getIdentifier()]));
            }
        }


        return new MinSpanTree(oddNodes,oddEdges);
    }
}
