package com.bytecoder.DSA.Part_2.Graphs.AdjacencyList;

import java.util.List;

public interface GraphProblems<T> {


    void addNode(Node<T> node);

    void removeNode(Node<T> node);

    List<Node<T>> getAllNodes();

    void addEdge(Edge<T> edge);

    void removeEdge(Edge<T> edge);

    List<Edge<T>> getAllEdges();

    boolean hasEdge(Node<T> src, Node<T> end);

    void dfs();

    void bfs();

    boolean isCyclic();


    void printGraph();


    void implementDFSTopologicalSorting();

    void implementBFSTopologicalSorting();

    void allTopologicalSorting();

    boolean isTopologicalSortingValid(Node<T>[] sorting);

    //    prims algorithms for minimum/max spanning tree (
    void printPrimMST();

    void printKrushkalMST();

    //    shortest path between two nodes

    void findShortestPathUsingDijkstra();

    void findShortestPathUsingBellmanFord();


}
