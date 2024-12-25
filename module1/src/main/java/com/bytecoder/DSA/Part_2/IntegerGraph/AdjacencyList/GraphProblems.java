package com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList;

import java.util.HashMap;
import java.util.List;

public interface GraphProblems<T> {


    void addNode(int node);

    void removeNode(int node);

    List<HashMap<Integer,Integer>>  getAllNodes();

    void addEdge(Edge edge);

    void removeEdge(Edge edge);

    List<Edge> getAllEdges();

    boolean hasEdge(int src, int end);

    void dfs();

    void bfs();

    boolean isCyclic();


    void printGraph();


    void implementDFSTopologicalSorting();

    void implementBFSTopologicalSorting();

    void allTopologicalSorting();

    boolean isTopologicalSortingValid(int[] sorting);

    //    prims algorithms for minimum/max spanning tree (
    void printPrimMST();

    void printKrushkalMST();

    //    shortest path between two nodes

    void findShortestPathUsingDijkstra();

    void findShortestPathUsingBellmanFord();


}
