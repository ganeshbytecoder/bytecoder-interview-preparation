package com.bytecoder.DSA.Part_2_With_Integer.IntegerGraph.AdjacencyMatrix;


import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge;

import java.util.List;

public interface GraphProblems {


    void addNode(int node);

    void removeNode(int node);

    List<Integer> getAllNodes();

    void addEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge);

    void removeEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge);

    List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> getAllEdges();

    boolean hasEdge(int src, int end);

    void dfs();

    void bfs();

    boolean isCyclic();

    void printGraph();


    void implementDFSTopologicalSorting();

    void implementBFSTopologicalSorting();

    void allTopologicalSorting();

    boolean isTopologicalSortingValid(int [] sorting);


//    prims algorithms for minimum/max spanning tree (
    void printPrimMST();

    List<Edge> printKrushkalMST();

//    shortest path between two nodes

    void findShortestPathUsingDijkstra();

    void findShortestPathUsingBellmanFord();






}
