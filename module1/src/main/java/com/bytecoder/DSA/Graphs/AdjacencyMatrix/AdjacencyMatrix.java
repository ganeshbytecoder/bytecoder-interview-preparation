package com.bytecoder.DSA.Graphs.AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdjacencyMatrix<T> {

    private final List<Node<T>> vertices = new ArrayList<>();

    private final int[][] matrix;

    private final boolean directed;

    AdjacencyMatrix(int n, boolean directed) {

        matrix = new int[n][n];
        this.directed = directed;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -1;
            }
        }
    }

    public List<Node<T>> getAllNodes() {
        return vertices;
    }

    public void addVertex(Node<T> node) {
        vertices.add(node);
    }

    public void addEdge(Edge edge) {
        int i = edge.start.id;
        int j = edge.end.id;
        matrix[i][j] = edge.cost;

        if (!this.directed) {
            matrix[j][i] = edge.cost;

        }
    }

    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] != -1) {
                    edges.add(new Edge<>(getNodeById(i), getNodeById(j), matrix[i][j]));
                }
            }
        }

        return edges;
    }

    private Node<T> getNodeById(int id) {

        List<Node<T>> nodes = vertices.stream().filter(node -> node.getId() == id).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


    public Node<T> getNodeByName(T data) {

        List<Node<T>> nodes = vertices.stream().filter(node -> node.getData() == data).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


    void printMatrix() {
        for (Node<T> node : vertices) {
            System.out.print(node.data.toString() + "   ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
