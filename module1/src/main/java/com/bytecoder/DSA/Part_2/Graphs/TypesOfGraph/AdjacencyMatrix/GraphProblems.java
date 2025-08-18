package com.bytecoder.DSA.Part_2.Graphs.TypesOfGraph.AdjacencyMatrix;

import java.util.List;
import java.util.Map;

/**
 * Interface defining graph operations using Adjacency Matrix representation.
 * This provides a standard set of operations that can be performed on a graph.
 * 
 * Time Complexity Analysis:
 * - Basic Operations (addVertex, addEdge): O(1)
 * - Graph Traversal (DFS, BFS): O(V^2)
 * - Shortest Path (Dijkstra's): O(V^2)
 * - MST (Prim's): O(V^2)
 * where V is the number of vertices
 */
public interface GraphProblems {
    // Basic graph operations
    void addVertex(int data);
    void addEdge(int source, int destination, int weight);
    void removeEdge(int source, int destination);
    boolean hasEdge(int source, int destination);
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
    
    // Minimum Spanning Tree using prim's and  kuruskal's algorithm
    List<Edge> findMinimumSpanningTree();
    List<Edge> findMinimumSpanningTree_UsingKurukal();

    // Topological Sort (for directed acyclic graphs)
    List<Integer> topologicalSort();
    List<Integer> topologicalSort_UsingKahns();
    
    // Component analysis
    List<List<Integer>> findConnectedComponents();
    boolean hasBridge();
    List<int[]> findBridges();
    
    // Graph metrics
    int getDiameter();
    double getAveragePathLength();
    int getVertexDegree(int vertex);
    
    // Matrix-specific operations
    int[][] getAdjacencyMatrix();
    List<Node> getNeighbors(int vertex);
    
    // Utility methods
    void resetGraph();
    String printGraph();
}
