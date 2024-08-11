package com.bytecoder.DSA.Graphs.AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdjacencyMatrix<T> implements Graph {

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



    public void addNode(Node<T> node) {
        vertices.add(node);
    }

    @Override
    public List<Node<T>> getAllNodes() {
        return vertices;
    }


    @Override
    public void addEdge(Edge edge) {
        int i = edge.start.id;
        int j = edge.end.id;
        matrix[i][j] = edge.cost;

        if (!this.directed) {
            matrix[j][i] = edge.cost;

        }
    }

    @Override
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


    public boolean hasEdge(Node<T> src, Node<T> end) {
        return matrix[src.id][end.id] != -1 || matrix[end.id][src.id] != -1;
    }


    public Node<T> getNodeByName(T data) {

        List<Node<T>> nodes = vertices.stream().filter(node -> node.getData() == data).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


    @Override
    public void printGraph() {
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


    @Override
    public void dfs() {

        for (int i = 0; i < vertices.size(); i++) {
            if (!vertices.get(i).visited) {
                dfsTraversal(vertices.get(i));
            }
        }

    }


    public void dfsTraversal(Node<T> node) {
        System.out.println(node.data.toString());
        node.setVisited(true);

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[node.id][i] != -1 && !getNodeById(i).isVisited()) {
                dfsTraversal(getNodeById(i));
            }
        }


    }


}
