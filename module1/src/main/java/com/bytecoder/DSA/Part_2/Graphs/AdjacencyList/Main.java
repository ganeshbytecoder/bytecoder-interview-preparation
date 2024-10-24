package com.bytecoder.DSA.Part_2.Graphs.AdjacencyList;


public class Main {


    public static void main(String[] args) {
        GraphImpl<String> graphImpl = new GraphImpl<>(4, true);



        String[] values = {"A", "B", "C", "D"};


        for (int i = 0; i < 4; i++) {
            graphImpl.addNode(new Node<>(i, values[i]));
        }

        graphImpl.getAllNodes().stream().forEach(System.out::println);

        graphImpl.getAllEdges().stream().forEach(System.out::println);

        graphImpl.addEdge(new Edge(graphImpl.getNodeByName("A"), graphImpl.getNodeByName("B"), 10));
        graphImpl.addEdge(new Edge(graphImpl.getNodeByName("B"), graphImpl.getNodeByName("A"), 1));
        graphImpl.addEdge(new Edge(graphImpl.getNodeByName("B"), graphImpl.getNodeByName("D"), 11));

        graphImpl.getAllEdges().forEach(System.out::println);

        System.out.println("Matrix");

        graphImpl.printGraph();
    }
}
