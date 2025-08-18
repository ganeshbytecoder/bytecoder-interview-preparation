package com.bytecoder.DSA.Part_4.Graphs.AdjacencyList;


public class Main {


    public static void main(String[] args) {

        Graph<String> adjGraph = new Graph<>( false);

        GraphProblemsImpl<String> graphImpl = new GraphProblemsImpl<>(adjGraph);

        String[] values = {"A", "B", "C", "D"};


        for (int i = 0; i < 4; i++) {
            graphImpl.addNode(new Node<>(i, values[i]));
        }

        graphImpl.getAllNodes().forEach(System.out::println);

        graphImpl.getAllEdges().forEach(System.out::println);

        graphImpl.addEdge(new Edge<>(graphImpl.getNodeByData("A"), graphImpl.getNodeByData("B"), 10));
        graphImpl.addEdge(new Edge<>(graphImpl.getNodeByData("B"), graphImpl.getNodeByData("A"), 1));
        graphImpl.addEdge(new Edge<>(graphImpl.getNodeByData("B"), graphImpl.getNodeByData("D"), 11));

        graphImpl.getAllEdges().forEach(System.out::println);

        System.out.println("Matrix");

        graphImpl.printGraph();
    }
}
