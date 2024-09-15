package com.bytecoder.DSA.Graphs.AdjacencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class GraphImpl<T> implements Graph {
    // No. of vertices
    private int n;

    private boolean directed;

    private List<Node<T>> adjacencyList = new ArrayList<>();

    public GraphImpl(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
    }

    @Override
    public void addNode(Node node) {
        adjacencyList.add(node);
    }

    @Override
    public void removeNode(Node node) {
        adjacencyList.remove(node);

        for(Node node1: adjacencyList){
            if(node1.getNeighbors().get(node) != null){
                node1.getNeighbors().remove(node);
            }
        }
    }

    @Override
    public List<Node<T>> getAllNodes() {
        return adjacencyList;
    }


    @Override
    public void addEdge(Edge edge) {
        adjacencyList.stream().filter(node -> node.getData() == edge.getStart().getData()).forEach(node -> node.addNeighbor(edge.getEnd(), edge.getCost()));
        if (!directed) {
            adjacencyList.stream().filter(node -> node.getData() == edge.getEnd().getData()).forEach(node -> node.addNeighbor(edge.getStart(), edge.getCost()));
        }
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    @Override
    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();

        for (Node<T> node : adjacencyList) {
            for (Map.Entry<Node<T>, Integer> neighbor : node.getNeighbors().entrySet()) {
                edges.add(new Edge<>(node, neighbor.getKey(), neighbor.getValue()));
            }
        }

        return edges;
    }

    @Override
    public boolean hasEdge(Node src, Node end) {
        return src.getNeighbors().get(end) != null;
    }

    @Override
    public void dfs() {

    }

    @Override
    public void bfs() {

    }

    @Override
    public void printGraph() {

    }


    public Node<T> getNodeByName(T data) {

        List<Node<T>> nodes = adjacencyList.stream().filter(node -> node.getData() == data).collect(Collectors.toList());
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
