package com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphAdjMatrixImplTest {
    GraphAdjMatrixImpl<String> matrix;


    @BeforeEach
    void setUp() {
        matrix = new GraphAdjMatrixImpl<>(6, true);
        String[] values = {"A", "B", "C", "D", "E", "F"};
        for (int i = 0; i < 6; i++) {
            matrix.addNode(new Node<>(i, values[i]));
        }

    }

    @Test
    void addNode() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void getAllNodes() {

        matrix.getAllNodes().forEach(System.out::println);
    }

    @Test
    void addEdge() {
        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("D"), matrix.getNodeByName("F"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("A"), 10));

        matrix.getAllEdges().stream().forEach(System.out::println);
    }

    @Test
    void removeEdge() {
    }

    @Test
    void getAllEdges() {
        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("D"), matrix.getNodeByName("F"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("A"), 10));

        matrix.getAllEdges().stream().forEach(System.out::println);
    }

    @Test
    void hasEdge() {
        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("D"), matrix.getNodeByName("F"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("A"), 10));


        System.out.println(matrix.hasEdge(matrix.getNodeByName("A"), matrix.getNodeByName("C")));
    }

    @Test
    void printGraph() {
        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("D"), matrix.getNodeByName("F"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("A"), 10));

        matrix.printGraph();

    }

    @Test
    void dfs() {
    }

    @Test
    void bfs() {
    }

    @Test
    void detectCycleForUndirectedWithDFS() {
    }

    @Test
    void isCyclic() {


        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("D"), matrix.getNodeByName("F"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("A"), 10));

//        matrix.getAllEdges().stream().forEach(System.out::println);

        matrix.isCyclic();

    }

    @Test
    void implementDFSTopologicalSorting() {

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("B"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("A"), matrix.getNodeByName("D"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("D"), matrix.getNodeByName("F"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("B"), matrix.getNodeByName("C"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("E"), 10));

        matrix.addEdge(new Edge(matrix.getNodeByName("C"), matrix.getNodeByName("A"), 10));

        matrix.implementDFSTopologicalSorting();
    }

    @Test
    void implementBFSTopologicalSorting() {
    }




    @Test
    void printPrimMST() {
    }

    @Test
    void printKrushkalMST() {
    }

    @Test
    void getNodeByName() {
    }
}