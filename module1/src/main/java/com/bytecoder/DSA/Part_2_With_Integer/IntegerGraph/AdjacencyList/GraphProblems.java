package com.bytecoder.DSA.Part_2_With_Integer.IntegerGraph.AdjacencyList;

import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Edge;

import java.util.HashMap;
import java.util.List;

public interface GraphProblems<T> {


    void addNode(int node);

    void removeNode(int node);

    List<HashMap<Integer,Integer>>  getAllNodes();

    void addEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Edge edge);

    void removeEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Edge edge);

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
