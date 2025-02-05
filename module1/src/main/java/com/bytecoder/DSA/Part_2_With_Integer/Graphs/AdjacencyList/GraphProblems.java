package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node;

import java.util.List;

public interface GraphProblems<T> {


    void addNode(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node);

    void removeNode(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node);

    List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>> getAllNodes();

    void addEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T> edge);

    void removeEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T> edge);

    List<Edge<T>> getAllEdges();

    boolean hasEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> src, com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> end);

    void dfs();

    void bfs();

    //  using DFS and BFS
    boolean searchData(T data);

    //  using DFS and BFS
    int getLevel(T data);

    //  using DFS and BFS
    int getMax();

    //  using DFS and BFS
    int getMin();

    boolean isCyclic();


    void printGraph();


    void implementDFSTopologicalSorting();

    void implementBFSTopologicalSorting();

    void allTopologicalSorting();

    boolean isTopologicalSortingValid(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>[] sorting);

    //    prims algorithms for minimum/max spanning tree (
    void printPrimMST();

    void printKrushkalMST();

    //  shortest path between two nodes
    void findShortestPathUsingDijkstra();

    void findShortestPathUsingBellmanFord();


}
