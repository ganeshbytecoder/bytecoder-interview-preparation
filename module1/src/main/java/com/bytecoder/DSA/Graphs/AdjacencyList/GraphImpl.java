package com.bytecoder.DSA.Graphs.AdjacencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class GraphImpl<T> {
    // No. of vertices
    private int n;

    private boolean directed;

    private List<Node<T>> adjacencyNeighbors = new ArrayList<>();

    public GraphImpl(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
    }

    public List<Node<T>> getAllNodes() {
        return adjacencyNeighbors;
    }

    public void addNode(Node<T> node) {
        adjacencyNeighbors.add(node);
    }

    public void addEdge(Edge edge) {
        adjacencyNeighbors.stream().filter(node -> node.getData() == edge.getStart().getData()).forEach(node -> node.addNeighbor(edge.getEnd(), edge.getCost()));
        if (!directed) {
            adjacencyNeighbors.stream().filter(node -> node.getData() == edge.getEnd().getData()).forEach(node -> node.addNeighbor(edge.getStart(), edge.getCost()));
        }
    }

    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();

        for (Node<T> node : adjacencyNeighbors) {
            for (Map.Entry<Node<T>, Integer> neighbor : node.getNeighbors().entrySet()) {
                edges.add(new Edge<>(node, neighbor.getKey(), neighbor.getValue()));
            }
        }

        return edges;
    }


    public Node<T> getNodeByName(T data) {

        List<Node<T>> nodes = adjacencyNeighbors.stream().filter(node -> node.getData() == data).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


    void printMatrix() {
        for (Node<T> node : getAllNodes()) {
            System.out.println(node.data.toString() + "   ");

            for (Map.Entry<Node<T>, Integer> neighbor : node.getNeighbors().entrySet()) {
                System.out.println(node.data + " -> " + neighbor.getKey().getData() + " " + neighbor.getValue());
            }
            System.out.println();

        }

    }


}
