package com.bytecoder.DSA.Graphs.AdjacencyMatrix;


public class Main {


    public static void main(String[] args) {
        GraphImpl<String> matrix = new GraphImpl<>(4, true);


        String[] values = {"A", "B", "C", "D"};


        for (int i = 0; i < 4; i++) {
            matrix.addNode(new Node<>(i, values[i]));
        }

        matrix.getAllNodes().stream().forEach(System.out::println);

        matrix.getAllEdges().stream().forEach(System.out::println);

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.getAllEdges().stream().forEach(System.out::println);

        System.out.println("Matrix");

        matrix.printGraph();

        matrix.dfs();


    }
}
