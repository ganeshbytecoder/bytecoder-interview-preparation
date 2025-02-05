package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import java.util.List;
import java.util.Map;

public interface GraphProblems {
    // Basic graph operations
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
    
    // Graph properties
    boolean isCyclic();
    boolean isConnected();
    int getVertexCount();
    int getEdgeCount();
    
    // Path finding
    List<Integer> findShortestPath(int source, int destination);
    Map<Integer, Integer> findAllShortestPaths(int source);
    
    // Minimum Spanning Tree
    List<Edge> findMinimumSpanningTree();
    
    // Topological Sort (for directed acyclic graphs)
    List<Integer> topologicalSort();
    
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
