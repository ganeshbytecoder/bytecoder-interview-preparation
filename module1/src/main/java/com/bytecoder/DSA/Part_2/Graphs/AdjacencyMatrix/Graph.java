package com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix;


import java.util.List;

public interface Graph<T> {


    void addNode(Node node);

    void removeNode(Node<T> node);

    List<Node<T>> getAllNodes();

    void addEdge(Edge edge);

    void removeEdge(Edge edge);

    List<Edge<T>> getAllEdges();

    boolean hasEdge(Node<T> src, Node<T> end);

    void dfs();

    void bfs();

    boolean isCyclic();



    void implementDFSTopologicalSorting();

    void implementBFSTopologicalSorting();

    void allTopologicalSorting();

    boolean isTopologicalSortingValid(Node<T> [] sorting);



//    prims algorithms for minimum/max spanning tree (
    void printPrimMST();

    List<Edge<T>> printKrushkalMST();




//    shortest path between two nodes





    void printGraph();

}
