package com.bytecoder.DSA.Part_2.Graphs.AdjacencyList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphProblemsImplTest {
    GraphProblemsImpl<Integer> graphProblems = new GraphProblemsImpl<>(null);
    @BeforeEach
    void setUp() {
        Graph<Integer> graph = new Graph<>(true );
        Node<Integer> node1 = new Node<>(0,0 );
        Node<Integer> node2 = new Node<>(1,1 );
        Node<Integer> node3 = new Node<>(2,2 );
        Node<Integer> node4 = new Node<>(3,3 );

        node1.getNeighbors().put(node2,  5);
        node1.getNeighbors().put(node3,  10);
        node4.getNeighbors().put(node3,  10);
        node4.getNeighbors().put(node1,  10);

        graph.getVertices().add(node1);
        graph.getVertices().add(node2);
        graph.getVertices().add(node3);
        graph.getVertices().add(node4);

        graphProblems = new GraphProblemsImpl<>(graph);

    }

    @Test
    void addNode() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void getAllNodes() {
    }

    @Test
    void addEdge() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void getAllEdges() {
    }

    @Test
    void hasEdge() {
    }

    @Test
    void dfs() {
    }

    @Test
    void bfs() {
    }

    @Test
    void isCyclic() {
    }

    @Test
    void printGraph() {
    }

    @Test
    void implementDFSTopologicalSorting() {
        graphProblems.implementDFSTopologicalSorting();

    }

    @Test
    void implementBFSTopologicalSorting() {
    }

    @Test
    void allTopologicalSorting() {
    }

    @Test
    void isTopologicalSortingValid() {
    }

    @Test
    void printPrimMST() {
    }

    @Test
    void printKrushkalMST() {
    }

    @Test
    void getNodeByData() {
    }

    @Test
    void findShortestPathUsingDijkstra() {
    }

    @Test
    void findShortestPathUsingBellmanFord() {
    }
}