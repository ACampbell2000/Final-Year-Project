package main.java.christofides;

import main.java.tspgraph.Edge;
import main.java.tspgraph.Node;
import main.java.tsplibreader.Tsplibconverter;
import org.jgrapht.Graph;
import org.jgrapht.GraphType;

import java.util.*;
import java.util.function.Supplier;

public class KolmogorovGraph implements Graph<Node, Edge> {
    Set<Node> nodes = new HashSet<>();
    Set<Edge> edges = new HashSet<>();

    public KolmogorovGraph(List<Node> nodes, List<Edge> edges) {
        super();
        this.nodes.addAll(nodes);
        this.edges.addAll(edges);
    }
    @Override
    public Set<Edge> getAllEdges(Node node, Node v1) {
        Set<Edge> edgeSet = new HashSet<>();
        for (Edge edge : edges) {
            if(edge.getFrom().equals(node) && edge.getTo().equals(v1))
                edgeSet.add(edge);
        }
        return edgeSet;
    }

    @Override
    public Edge getEdge(Node node, Node v1) {
        for (Edge edge : edges) {
            if(edge.getFrom().equals(node) && edge.getTo().equals(v1))
                return edge;
        }
        return null;
    }

    @Override
    public Supplier<Node> getVertexSupplier() {
        return Node::new;
    }

    @Override
    public Supplier<Edge> getEdgeSupplier() {
        return Edge::new;
    }

    @Override
    public Edge addEdge(Node node, Node v1) {
        Edge edge = new Edge(node, v1, Tsplibconverter.euclidianDistance(node, v1));
        for (Edge edge2 : edges) {
            if(edge.getTo().equals(edge2.getTo()) && edge.getFrom().equals(edge2.getFrom()) && edge.getCost()==edge2.getCost())
                return null;
        }
        edges.add(edge);
        return edge;
    }

    @Override
    public boolean addEdge(Node node, Node v1, Edge edge) {
        if(edges.contains(edge))
            return false;
        else {
            edges.add(edge);
            return true;
        }
    }

    @Override
    public Node addVertex() {

        return null;
    }

    @Override
    public boolean addVertex(Node node) {
        if(nodes.contains(node)) {
            nodes.add(node);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(Node node, Node v1) {
        return this.addEdge(node, v1) == null;
    }

    @Override
    public boolean containsEdge(Edge edge) {
        return edges.contains(edge);
    }

    @Override
    public boolean containsVertex(Node node) {
        return nodes.contains(node);
    }

    @Override
    public Set<Edge> edgeSet() {
        return edges;
    }

    @Override
    public int degreeOf(Node node) {
        int count = 0;
        for (Edge edge : edges) {
            if(edge.getTo().equals(node) || edge.getFrom().equals(node))
                count++;
        }
        return count;
    }

    @Override
    public Set<Edge> edgesOf(Node node) {
        Set<Edge> touching = new HashSet<>();
        for (Edge edge : edges) {
            if(edge.getTo().equals(node) || edge.getFrom().equals(node))
                touching.add(edge);
        }
        return touching;
    }

    @Override
    public int inDegreeOf(Node node) {
        return degreeOf(node);
    }

    @Override
    public Set<Edge> incomingEdgesOf(Node node) {
        return edgesOf(node);
    }

    @Override
    public int outDegreeOf(Node node) {
        return degreeOf(node);
    }

    @Override
    public Set<Edge> outgoingEdgesOf(Node node) {
        return edgesOf(node);
    }

    @Override
    public boolean removeAllEdges(Collection<? extends Edge> collection) {
        int count = edges.size();
        for (Edge edge : collection) {
            edges.remove(edge);
        }
        return count > edges.size();
    }

    @Override
    public Set<Edge> removeAllEdges(Node node, Node v1) {
        Set<Edge> removedEdges = new HashSet<>();
        for (Edge edge : edges) {
            if(edge.equals(new Edge(node, v1, Tsplibconverter.euclidianDistance(node, v1)))) {
                edges.remove(edge);
                removedEdges.add(edge);
            }
        }
        return removedEdges;
    }

    @Override
    public boolean removeAllVertices(Collection<? extends Node> collection) {
        int count = nodes.size();
        for (Node node : collection) {
            edges.remove(node);
        }
        return count > nodes.size();
    }

    @Override
    public Edge removeEdge(Node node, Node v1) {
        if(edges.contains(new Edge(node, v1, Tsplibconverter.euclidianDistance(node,v1)))) {
            edges.remove(new Edge(node, v1, Tsplibconverter.euclidianDistance(node,v1)));
            return new Edge(node, v1, Tsplibconverter.euclidianDistance(node,v1));
        }
        return null;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        if(edges.contains(edge)) {
            edges.remove(edge);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(Node node) {
        if(nodes.contains(node)) {
            nodes.remove(node);
            return true;
        }
        return false;
    }

    @Override
    public Set<Node> vertexSet() {
        return nodes;
    }

    @Override
    public Node getEdgeSource(Edge edge) {
        return edge.getFrom();
    }

    @Override
    public Node getEdgeTarget(Edge edge) {
        return edge.getTo();
    }

    @Override
    public GraphType getType() {

        return new GraphType() {
            @Override
            public boolean isDirected() {
                return false;
            }

            @Override
            public boolean isUndirected() {
                return true;
            }

            @Override
            public boolean isMixed() {
                return false;
            }

            @Override
            public boolean isAllowingMultipleEdges() {
                return false;
            }

            @Override
            public boolean isAllowingSelfLoops() {
                return false;
            }

            @Override
            public boolean isAllowingCycles() {
                return false;
            }

            @Override
            public boolean isWeighted() {
                return true;
            }

            @Override
            public boolean isSimple() {
                return false;
            }

            @Override
            public boolean isPseudograph() {
                return false;
            }

            @Override
            public boolean isMultigraph() {
                return false;
            }

            @Override
            public boolean isModifiable() {
                return false;
            }

            @Override
            public GraphType asDirected() {
                return null;
            }

            @Override
            public GraphType asUndirected() {
                return null;
            }

            @Override
            public GraphType asMixed() {
                return null;
            }

            @Override
            public GraphType asUnweighted() {
                return null;
            }

            @Override
            public GraphType asWeighted() {
                return null;
            }

            @Override
            public GraphType asModifiable() {
                return null;
            }

            @Override
            public GraphType asUnmodifiable() {
                return null;
            }
        };
    }

    @Override
    public double getEdgeWeight(Edge edge) {
        return edge.getCost();
    }

    @Override
    public void setEdgeWeight(Edge edge, double v) {

    }
}
