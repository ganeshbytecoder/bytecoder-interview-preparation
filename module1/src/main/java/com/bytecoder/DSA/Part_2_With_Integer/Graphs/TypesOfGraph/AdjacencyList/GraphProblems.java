package com.bytecoder.DSA.Part_2_With_Integer.Graphs.TypesOfGraph.AdjacencyList;

import java.util.List;
import java.util.Map;

public interface GraphProblems {
    // Basic graph operations - are part of graph only
    void addVertex(int data);
    void removeVertex(int data);
    void addEdge(int source, int destination, int weight);
    void removeEdge(int source, int destination);
    boolean hasEdge(int source, int destination);
    List<Edge> getAllEdges();
    List<Node> getAllVertices();


    // Graph traversal
    List<Integer> depthFirstSearch(int startVertex);
    List<Integer> breadthFirstSearch(int startVertex);
    int getVertexCount();
    int getEdgeCount();

    // Graph properties
    boolean isCyclic();
    boolean isConnected();

    
    // Path finding
    List<Integer> findShortestPath(int source, int destination);
    Map<Integer, Integer> findAllShortestPaths(int source);
    
    // Minimum Spanning Tree using prim's and  kuruskal's algorithm
    List<Edge> findMinimumSpanningTree();
    List<Edge> findMinimumSpanningTree_UsingKurukal();

    // Topological Sort (for directed acyclic graphs)
    List<Integer> topologicalSort();
    List<Integer> topologicalSort_UsingKahns();

    // Component analysis
    List<List<Integer>> findConnectedComponents();
    boolean hasBridge();
    List<Edge> findBridges();
    
    // Graph metrics
    int getDiameter();
    double getAveragePathLength();
    int getVertexDegree(int vertex);
    
    // Utility methods
    void resetGraph();
    String printGraph();
}
