package com.bytecoder.DSA.Graphs.AdjacencyList;


public class AdjacencyListGraph {


    public static void main(String[] args) {
        AdjacencyList<String> adjacencyList = new AdjacencyList<>(4, true);



        String[] values = {"A", "B", "C", "D"};


        for (int i = 0; i < 4; i++) {
            adjacencyList.addNode(new Node<>(values[i]));
        }

        adjacencyList.getAllNodes().stream().forEach(System.out::println);

        adjacencyList.getAllEdges().stream().forEach(System.out::println);

        adjacencyList.addEdge(new Edge(adjacencyList.getNodeByName("A"), adjacencyList.getNodeByName("B"), 10));
        adjacencyList.addEdge(new Edge(adjacencyList.getNodeByName("B"), adjacencyList.getNodeByName("A"), 1));
        adjacencyList.addEdge(new Edge(adjacencyList.getNodeByName("B"), adjacencyList.getNodeByName("D"), 11));

        adjacencyList.getAllEdges().stream().forEach(System.out::println);

        System.out.println("Matrix");

        adjacencyList.printMatrix();
    }
}
