package com.bytecoder.DSA.Part_2.Graphs.TypesOfGraph.AdjacencyMatrix;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Graph implementation using Adjacency Matrix representation.
 * This implementation is more suitable for dense graphs where most vertices are connected.
 * Space Complexity: O(V^2) where V is the number of vertices
 * Time Complexity:
 * - Adding/Removing Edge: O(1)
 * - Adding Vertex: O(V^2) due to matrix resizing
 * - Checking if two vertices are connected: O(1)
 */
@Getter
public class Graph {
    private final boolean directed;
    private final List<Node> vertices;
    private int[][] adjacencyMatrix;
    private int vertexCount;

    public Graph(boolean directed, int capacity) {
        this.directed = directed;
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = new int[capacity][capacity];
        this.vertexCount = 0;
        initializeMatrix();
    }

    private void initializeMatrix() {
        for (int[] matrix : adjacencyMatrix) {
            Arrays.fill(matrix, Integer.MAX_VALUE);
        }
    }

    public void addVertex(int data) {
        ensureCapacity();
        vertices.add(new Node(vertexCount, data));
        vertexCount++;
    }

    private void ensureCapacity() {
        if (vertexCount == adjacencyMatrix.length) {
            int newSize = adjacencyMatrix.length * 2;
            int[][] newMatrix = new int[newSize][newSize];
            
            // Copy existing values
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, adjacencyMatrix.length);
                // Initialize new elements
                for (int j = adjacencyMatrix.length; j < newSize; j++) {
                    newMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
            
            // Initialize new rows
            for (int i = adjacencyMatrix.length; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    newMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
            
            adjacencyMatrix = newMatrix;
        }
    }

    public void addEdge(int sourceData, int destData, int weight) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);
        
        if (source != null && destination != null) {
            adjacencyMatrix[source.getId()][destination.getId()] = weight;
            if (!directed) {
                adjacencyMatrix[destination.getId()][source.getId()] = weight;
            }
        }
    }

    public void removeEdge(int sourceData, int destData) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);
        
        if (source != null && destination != null) {
            adjacencyMatrix[source.getId()][destination.getId()] = Integer.MAX_VALUE;
            if (!directed) {
                adjacencyMatrix[destination.getId()][source.getId()] = Integer.MAX_VALUE;
            }
        }
    }

    public boolean hasEdge(int sourceData, int destData) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);
        
        if (source != null && destination != null) {
            return adjacencyMatrix[source.getId()][destination.getId()] != Integer.MAX_VALUE;
        }
        return false;
    }

    public int getEdgeWeight(int sourceData, int destData) {
        Node source = findNodeByData(sourceData);
        Node destination = findNodeByData(destData);
        
        if (source != null && destination != null) {
            return adjacencyMatrix[source.getId()][destination.getId()];
        }
        return Integer.MAX_VALUE;
    }

    public List<Node> getNeighbors(int data) {
        List<Node> neighbors = new ArrayList<>();
        Node node = findNodeByData(data);
        
        if (node != null) {
            for (int i = 0; i < vertexCount; i++) {
                if (adjacencyMatrix[node.getId()][i] != Integer.MAX_VALUE) {
                    neighbors.add(vertices.get(i));
                }
            }
        }
        return neighbors;
    }

    public Node findNodeByData(int data) {
        return vertices.stream()
                .filter(node -> node.getData() == data)
                .findFirst()
                .orElse(null);
    }

    public void resetVisitedStatus() {
        vertices.forEach(vertex -> vertex.setVisited(false));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph (").append(directed ? "directed" : "undirected").append("):\n");
        sb.append("Adjacency Matrix:\n");
        
        // Print column headers
        sb.append("    ");
        for (int i = 0; i < vertexCount; i++) {
            sb.append(String.format("%4d", vertices.get(i).getData()));
        }
        sb.append("\n");
        
        // Print matrix with row headers
        for (int i = 0; i < vertexCount; i++) {
            sb.append(String.format("%4d", vertices.get(i).getData()));
            for (int j = 0; j < vertexCount; j++) {
                if (adjacencyMatrix[i][j] == Integer.MAX_VALUE) {
                    sb.append("   ∞");
                } else {
                    sb.append(String.format("%4d", adjacencyMatrix[i][j]));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
