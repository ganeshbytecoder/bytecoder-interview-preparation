package com.bytecoder.DSA.Part_2_With_Integer.Graphs.TypesOfGraph.AdjacencyList;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Graph {
    private final boolean directed;
    private final List<Node> vertices;

//    or
//    used to create graph as well
    private final Map<Node, List<Node>> graph = new HashMap<>();



    public Graph(boolean directed) {
        this.directed = directed;
        this.vertices = new ArrayList<>();
    }

    public void addVertex(int data) {
        vertices.add(new Node(data));
    }

    public void addEdge(int sourceData, int destData, int weight) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);

        if (source != null && destination != null) {
            source.addNeighbor(destination, weight);
            if (!directed) {
                destination.addNeighbor(source, weight);
            }
        }
    }

    public void removeVertex(int data) {
        Node nodeToRemove = findNodeByData(data);
        if (nodeToRemove != null) {
            // Remove all edges pointing to this vertex
            for (Node vertex : vertices) {
                vertex.removeNeighbor(nodeToRemove);
            }
            vertices.remove(nodeToRemove);
        }
    }

    public void removeEdge(int sourceData, int destData) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);

        if (source != null && destination != null) {
            source.removeNeighbor(destination);
            if (!directed) {
                destination.removeNeighbor(source);
            }
        }
    }

    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();
        for (Node vertex : vertices) {
            for (var entry : vertex.getNeighbors().entrySet()) {
                edges.add(new Edge(vertex, entry.getKey(), entry.getValue()));
            }
        }
        return edges;
    }

    public Node findNodeByData(int data) {
        return vertices.stream()
                .filter(node -> node.getData() == data)
                .findFirst()
                .orElse(null);
    }

    public boolean hasEdge(int sourceData, int destData) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);
        return source != null && destination != null && source.hasNeighbor(destination);
    }

    public int getEdgeWeight(int sourceData, int destData) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);
        if (source != null && destination != null) {
            Integer weight = source.getEdgeWeight(destination);
            return weight != null ? weight : -1;
        }
        return -1;
    }

    public void resetVisitedStatus() {
        vertices.forEach(vertex -> vertex.setVisited(false));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph (").append(directed ? "directed" : "undirected").append("):\n");
        for (Node vertex : vertices) {
            sb.append(vertex.getData()).append(" -> ");
            vertex.getNeighbors().forEach((neighbor, weight) ->
                sb.append("[").append(neighbor.getData()).append(":").append(weight).append("] "));
            sb.append("\n");
        }
        return sb.toString();
    }
}
