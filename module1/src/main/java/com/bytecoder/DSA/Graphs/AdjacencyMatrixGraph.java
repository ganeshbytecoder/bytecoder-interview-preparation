package com.bytecoder.DSA.Graphs;


public class AdjacencyMatrixGraph {


    public static void main(String[] args) {
        AdjacencyMatrix<String> matrix = new AdjacencyMatrix<>(4);

        String[] values = {"A", "B", "C", "D"};
        for (int i = 0; i < 4; i++) {
            matrix.addVertex(i, values[i]);
        }
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
        matrix.addEdge(new Edge(matrix.getAllNodes("A"), matrix.getAllNodes("B"), 10));
        matrix.addEdge( new Edge(matrix.getAllNodes("B"), matrix.getAllNodes("C"), 20));
        matrix.addEdge( new Edge(matrix.getAllNodes("B"), matrix.getAllNodes("A"), 10));
        System.out.println("Matrix");
        matrix.printMatrix();



    }
}
