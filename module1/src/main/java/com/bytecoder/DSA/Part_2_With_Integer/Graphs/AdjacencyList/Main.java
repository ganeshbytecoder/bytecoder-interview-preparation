package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;


import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Graph;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.GraphProblemsImpl;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node;

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

        graphImpl.addEdge(new com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<>(graphImpl.getNodeByData("A"), graphImpl.getNodeByData("B"), 10));
        graphImpl.addEdge(new com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<>(graphImpl.getNodeByData("B"), graphImpl.getNodeByData("A"), 1));
        graphImpl.addEdge(new Edge<>(graphImpl.getNodeByData("B"), graphImpl.getNodeByData("D"), 11));

        graphImpl.getAllEdges().forEach(System.out::println);

        System.out.println("Matrix");

        graphImpl.printGraph();
    }
}
