package main.java.christofides;

import main.java.tspgraph.Edge;
import main.java.tspgraph.Graph;
import main.java.tspgraph.Node;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.blossom.v5.KolmogorovWeightedPerfectMatching;
import org.jgrapht.alg.matching.blossom.v5.ObjectiveSense;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;

import java.util.*;

public class ChristofidesAlgorithm {


    public List<Node> calculateChristofides(Graph fullGraph) { //finds the singular christofides individual
        MinSpanTree minSpanTree = calculateMinimumSpanningTree(fullGraph);
        List<Node> oddNodes = calculateOddNodes(minSpanTree);
        MinSpanTree subGraph = calculateSubGraph(oddNodes, fullGraph);
        MinSpanTree minMatching = calculateMinimumMatch(subGraph);
        MinSpanTree unionGraph = union(minSpanTree, minMatching);
        List<Node> withRepeats = calculateEulerCycleWithRepeats(unionGraph);
        return removeRepeatsFromCycle(withRepeats);
    }

    public List<List<Node>> calculateMultChristofides(Graph fullGraph) { //finds all of the possible individuals which have lower quality than the singular individual
        MinSpanTree minSpanTree = calculateMinimumSpanningTree(fullGraph);
        List<Node> oddNodes = calculateOddNodes(minSpanTree);
        MinSpanTree subGraph = calculateSubGraph(oddNodes, fullGraph);
        MinSpanTree minMatching = calculateMinimumMatch(subGraph);
        MinSpanTree unionGraph = union(minSpanTree, minMatching);
        List<Node> withRepeats = calculateEulerCycleWithRepeats(unionGraph);
        return findAllPossibleCycles(withRepeats);
    }

    public MinSpanTree calculateMinimumSpanningTree(Graph fullGraph) {
        List<Node> nodesReached = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        nodesReached.add(fullGraph.getNodes().get(0));
        while (nodesReached.size() != fullGraph.getNodes().size()) {
            Edge minLength = new Edge(new Node(), new Node(), Double.MAX_VALUE);
            for (Node node_i : nodesReached) {
                for (Node node_j : fullGraph.getNodes()) {
                    if (fullGraph.getEdges()[node_i.getIdentifier()][node_j.getIdentifier()] < minLength.getCost() && !nodesReached.contains(node_j)) {
                        //if the edge between nodes i and j is smaller and j isnt already part of the min spanning tree then this if is used
                        minLength = new Edge(node_i, node_j, fullGraph.getEdges()[node_j.getIdentifier()][node_i.getIdentifier()]);
                    }
                }
            }
            nodesReached.add(minLength.getTo());
            edges.add(minLength);
        }
        return new MinSpanTree(fullGraph.getNodes(), edges);
    }

    public List<Node> calculateOddNodes(MinSpanTree minSpanTree) { //find the nodes with odd degree (i.e an odd number of edges connect to it
        List<Node> oddNodes = new ArrayList<>();
        for (Node node : minSpanTree.getNodes()) {
            int numOfEdges = 0;
            for (int i = 0; i < minSpanTree.getEdges().size(); i++) {
                if (minSpanTree.getEdges().get(i).getFrom() == node || minSpanTree.getEdges().get(i).getTo() == node)
                    numOfEdges++;
            }
            if (numOfEdges % 2 != 0) oddNodes.add(node);
        }

        oddNodes.sort((node1, node2) -> { //no need for a case of equal identifiers as this is impossible
            if (node1.getIdentifier() < node2.getIdentifier())
                return -1;
            else
                return 1;
        });

        return oddNodes;
    }

    public MinSpanTree calculateSubGraph(List<Node> oddNodes, Graph fullGraph) { //find the subgraph which only consists of the odd nodes
        List<Edge> oddEdges = new ArrayList<>();
        List<Node> leftToUse = new ArrayList<>(oddNodes);
        for (Node oddNode : oddNodes) {
            leftToUse.remove(0);
            for (Node node : leftToUse) {
                oddEdges.add(new Edge(oddNode, node, fullGraph.getEdges()[oddNode.getIdentifier()][node.getIdentifier()]));
            }
        }
        return new MinSpanTree(oddNodes, oddEdges);
    }

    public MinSpanTree calculateMinimumMatch(MinSpanTree subGraph) { //uses a library to calculate the minimum weight perfect match
        List<Edge> edges = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        KolmogorovGraph kolGraph = new KolmogorovGraph(subGraph.getNodes(), subGraph.getEdges()); //converts to structure used by library
        KolmogorovWeightedPerfectMatching<Node, Edge> minMatch = new KolmogorovWeightedPerfectMatching<Node, Edge>(kolGraph, ObjectiveSense.MINIMIZE);
        MatchingAlgorithm.Matching<Node, Edge> matching = minMatch.getMatching();
        for (Edge edge : matching.getEdges()) { //converts back to original data structure
            nodes.add(edge.getFrom());
            nodes.add(edge.getTo());
            edges.add(edge);
        }
        return new MinSpanTree(nodes, edges);
    }

    public MinSpanTree union(MinSpanTree minSpanTree, MinSpanTree minMatching) { //combines the minimum spanning tree and min matching
        minSpanTree.getEdges().addAll(minMatching.getEdges());
        return new MinSpanTree(minSpanTree.getNodes(), minSpanTree.getEdges());
    }

    public List<Node> calculateEulerCycleWithRepeats(MinSpanTree union) { //once again uses the same library to calculate the euler cycle with repeats
        List<Node> pathList = new ArrayList<>();
        KolmogorovGraph kolGraph = new KolmogorovGraph(union.getNodes(), union.getEdges());
        HierholzerEulerianCycle<Node, Edge> eulerianCycle = new HierholzerEulerianCycle<Node, Edge>();
        GraphPath<Node, Edge> path = eulerianCycle.getEulerianCycle(kolGraph);
        for (Edge edge : path.getEdgeList()) {
            if (pathList.isEmpty()) { //if we dont have a city in the list yet we need to add one immediately
                pathList.add(path.getStartVertex());
                if (pathList.get(0).equals(path.getEdgeList().get(0).getFrom())) //we then need to check for the other city and add that
                    pathList.add(edge.getTo());
                else
                    pathList.add(edge.getFrom());
            } else if (pathList.get(pathList.size() - 1).equals(edge.getFrom()))
                //each edge will have an identical city to the edge before it, we need to take the other city from this so there are no duplicates
                pathList.add(edge.getTo());
            else if (pathList.get(pathList.size() - 1).equals(edge.getTo()))
                pathList.add(edge.getFrom());
        }
        return pathList;
    }

    public List<Node> removeRepeatsFromCycle(List<Node> cycle) { //removes repeats from the cycle
        List<Node> cleanedCycle = new ArrayList<>();
        for (int i = 0; i < cycle.size(); i++) {
            if (i != 0 && i != cycle.size() - 1) {
                if (!cleanedCycle.contains(cycle.get(i)))
                    cleanedCycle.add(cycle.get(i));
            } else {
                cleanedCycle.add(cycle.get(i));
            }
        }
        return cleanedCycle;
    }

    public List<List<Node>> findAllPossibleCycles(List<Node> initialCycle) { //from any given repeat we can create 4 different tours, this subroutine can calculate all of them given enough time
        final int FREQUENCY_LIMIT = initialCycle.size()/12;
        List<List<Node>> allCycles = new ArrayList<>();
        List<List<Node>> cyclesStack = new ArrayList<>();
        int frequency = FREQUENCY_LIMIT; //the growth of cycles is incredibly large, only taking every nth one of this allows for a much shorter running time
        cyclesStack.add(initialCycle);
        List<Node> reverseCycle = new ArrayList<Node>(initialCycle);
        Collections.reverse(reverseCycle);
        cyclesStack.add(reverseCycle);
        while (!cyclesStack.isEmpty() && allCycles.size() < 10) { //we only want a max of 10 individuals
            List<Node> cycle = cyclesStack.get(cyclesStack.size()-1);
            cyclesStack.remove(cycle);
            List<Node> cleanedCycle = new ArrayList<>(); //cycle without repeats
            for (int i = 0; i < cycle.size(); i++) {
                if (i != 0 && i != cycle.size() - 1) { //the first and last node have to be repeats by definition of hamiltonian cycle
                    if (!cleanedCycle.contains(cycle.get(i))) {
                        cleanedCycle.add(cycle.get(i));

                    } else {
                        if(frequency++ >= FREQUENCY_LIMIT) { //every nth cycle we will add to the stack to generate more off of
                            List<Node> cleanedCycle2 = new ArrayList<>(cleanedCycle);
                            Collections.reverse(cleanedCycle2.subList(cleanedCycle2.indexOf(cycle.get(i)) + 1, cleanedCycle2.size()));
                            cleanedCycle2.addAll(cycle.subList(i + 1, cycle.size()));
                            cyclesStack.add(cleanedCycle2);
                            frequency = 0;
                        }
                    }
                } else {
                    cleanedCycle.add(cycle.get(i));
                }
            }
            if(!allCycles.contains(cleanedCycle))
                allCycles.add(cleanedCycle);
        }
        return allCycles;
    }

}
