package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;



public class Main {


    public static void main(String[] args) {

        Graph adjGraph = new Graph( false);

        GraphProblemsImpl graphImpl = new GraphProblemsImpl(adjGraph);

        int[] values = {1, 2, 3, 4};


        for (int i = 0; i < 4; i++) {
            graphImpl.addNode(new Node(i));
        }

        graphImpl.getAllNodes().forEach(System.out::println);

        graphImpl.getAllEdges().forEach(System.out::println);


        graphImpl.getAllEdges().forEach(System.out::println);

        System.out.println("Matrix");

        graphImpl.printGraph();
    }
}
