package com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix;


public class Main {


    public static void main(String[] args) {
        GraphAdjMatrixImpl<String> matrix = new GraphAdjMatrixImpl<>(5, false);


        String[] values = {"A", "B", "C", "D", "E"};


        for (int i = 0; i < 5; i++) {
            matrix.addNode(new Node<>(i, values[i]));
        }

        matrix.getAllNodes().stream().forEach(System.out::println);

        matrix.getAllEdges().stream().forEach(System.out::println);

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.getAllEdges().stream().forEach(System.out::println);

        System.out.println("Matrix");

        matrix.printGraph();

        matrix.dfs();

        matrix.bfs();


    }
}
