package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyMatrix;


import com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Edge;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node;

import java.util.List;

public interface GraphProblems<T> {


    void addNode(com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node<T> node);

    void removeNode(com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node<T> node);

    List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node<T>> getAllNodes();

    void addEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Edge<T> edge);

    void removeEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Edge<T> edge);

    List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Edge<T>> getAllEdges();

    boolean hasEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node<T> src, com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node<T> end);

    void dfs();

    void bfs();

    boolean isCyclic();

    void printGraph();


    void implementDFSTopologicalSorting();

    void implementBFSTopologicalSorting();

    void allTopologicalSorting();

    boolean isTopologicalSortingValid(com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node<T>[] sorting);


//    prims algorithms for minimum/max spanning tree (
    void printPrimMST();

    List<Edge<T>> printKrushkalMST();

//    shortest path between two nodes

    void findShortestPathUsingDijkstra();

    void findShortestPathUsingBellmanFord();






}
