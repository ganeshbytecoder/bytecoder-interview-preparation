package com.bytecoder.DSA.Part_4.Graphs.AdjacencyMatrix;


public class Main {


    public static void main(String[] args) {
        GraphProblemsAdjMatrixImpl<String>   matrix = new GraphProblemsAdjMatrixImpl<>(new Graph<>(6, false));
;


        String[] values = {"A", "B", "C", "D", "E", "F"};


        for (int i = 0; i < 6; i++) {
            matrix.addNode(new Node<>(i, values[i]));
        }

        matrix.getAllNodes().stream().forEach(System.out::println);

        matrix.getAllEdges().stream().forEach(System.out::println);

//        A->B,D
//        B-> C
//        C->E
//        D-> F
//

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("D"), matrix.getNodeByName("F"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.getAllEdges().stream().forEach(System.out::println);

        System.out.println("Matrix");

        matrix.printGraph();

        matrix.dfs();

        matrix.bfs();


    }
}
