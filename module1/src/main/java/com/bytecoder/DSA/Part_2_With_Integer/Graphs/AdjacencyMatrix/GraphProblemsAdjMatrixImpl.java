package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyMatrix;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of graph algorithms using Adjacency Matrix representation.
 * Optimized for dense graphs where most vertices are connected.
 * 
 * Time Complexity Analysis:
 * - Basic Operations: O(1)
 * - Graph Traversal: O(V^2)
 * - Shortest Path: O(V^2)
 * - MST: O(V^2)
 * where V is the number of vertices
 */
public class GraphProblemsAdjMatrixImpl implements GraphProblems {
    private final Graph graph;

    public GraphProblemsAdjMatrixImpl(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void addVertex(int data) {
        graph.addVertex(data);
    }

    @Override
    public void addEdge(int source, int destination, int weight) {
        graph.addEdge(source, destination, weight);
    }

    @Override
    public void removeEdge(int source, int destination) {
        graph.removeEdge(source, destination);
    }

    @Override
    public boolean hasEdge(int source, int destination) {
        return graph.hasEdge(source, destination);
    }

    @Override
    public List<Node> getAllVertices() {
        return graph.getVertices();
    }

    @Override
    public List<Integer> depthFirstSearch(int startVertex) {
        List<Integer> result = new ArrayList<>();
        Node start = graph.findNodeByData(startVertex);
        if (start == null) return result;

        boolean[] visited = new boolean[graph.getVertexCount()];
        dfsUtil(start.getId(), visited, result);
        return result;
    }

    private void dfsUtil(int vertex, boolean[] visited, List<Integer> result) {
        visited[vertex] = true;
        result.add(graph.getVertices().get(vertex).getData());

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!visited[i] && graph.getAdjacencyMatrix()[vertex][i] != Integer.MAX_VALUE) {
                dfsUtil(i, visited, result);
            }
        }
    }

    @Override
    public List<Integer> breadthFirstSearch(int startVertex) {
        List<Integer> result = new ArrayList<>();
        Node start = graph.findNodeByData(startVertex);
        if (start == null) return result;

        boolean[] visited = new boolean[graph.getVertexCount()];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start.getId());
        visited[start.getId()] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(graph.getVertices().get(vertex).getData());

            for (int i = 0; i < graph.getVertexCount(); i++) {
                if (!visited[i] && graph.getAdjacencyMatrix()[vertex][i] != Integer.MAX_VALUE) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return result;
    }

    @Override
    public boolean isCyclic() {
        boolean[] visited = new boolean[graph.getVertexCount()];
        boolean[] recursionStack = new boolean[graph.getVertexCount()];

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (isCyclicUtil(i, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int vertex, boolean[] visited, boolean[] recursionStack) {
        if (recursionStack[vertex]) return true;
        if (visited[vertex]) return false;

        visited[vertex] = true;
        recursionStack[vertex] = true;

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (graph.getAdjacencyMatrix()[vertex][i] != Integer.MAX_VALUE) {
                if (isCyclicUtil(i, visited, recursionStack)) {
                    return true;
                }
            }
        }

        recursionStack[vertex] = false;
        return false;
    }

    @Override
    public boolean isConnected() {
        if (graph.getVertices().isEmpty()) return true;

        boolean[] visited = new boolean[graph.getVertexCount()];
        dfsUtil(0, visited, new ArrayList<>());

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    @Override
    public int getVertexCount() {
        return graph.getVertexCount();
    }

    @Override
    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            for (int j = 0; j < graph.getVertexCount(); j++) {
                if (graph.getAdjacencyMatrix()[i][j] != Integer.MAX_VALUE) {
                    count++;
                }
            }
        }
        return graph.isDirected() ? count : count / 2;
    }

    @Override
    public List<Integer> findShortestPath(int source, int destination) {
        Node sourceNode = graph.findNodeByData(source);
        Node destNode = graph.findNodeByData(destination);
        if (sourceNode == null || destNode == null) return new ArrayList<>();

        int[] distances = new int[graph.getVertexCount()];
        int[] previous = new int[graph.getVertexCount()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        distances[sourceNode.getId()] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(
            Comparator.comparingInt(v -> distances[v])
        );
        pq.add(sourceNode.getId());

        while (!pq.isEmpty()) {
            int current = pq.poll();
            if (current == destNode.getId()) break;

            for (int i = 0; i < graph.getVertexCount(); i++) {
                if (graph.getAdjacencyMatrix()[current][i] != Integer.MAX_VALUE) {
                    int newDist = distances[current] + graph.getAdjacencyMatrix()[current][i];
                    if (newDist < distances[i]) {
                        distances[i] = newDist;
                        previous[i] = current;
                        pq.add(i);
                    }
                }
            }
        }

        if (distances[destNode.getId()] == Integer.MAX_VALUE) return new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        for (int at = destNode.getId(); at != -1; at = previous[at]) {
            path.add(0, graph.getVertices().get(at).getData());
        }
        return path;
    }

    @Override
    public Map<Integer, Integer> findAllShortestPaths(int source) {
        Node sourceNode = graph.findNodeByData(source);
        if (sourceNode == null) return new HashMap<>();

        int[][] distances = new int[graph.getVertexCount()][graph.getVertexCount()];
        for (int i = 0; i < graph.getVertexCount(); i++) {
            System.arraycopy(graph.getAdjacencyMatrix()[i], 0, distances[i], 0, graph.getVertexCount());
        }

        // Floyd-Warshall Algorithm
        for (int k = 0; k < graph.getVertexCount(); k++) {
            for (int i = 0; i < graph.getVertexCount(); i++) {
                for (int j = 0; j < graph.getVertexCount(); j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && 
                        distances[k][j] != Integer.MAX_VALUE && 
                        distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }

        Map<Integer, Integer> result = new HashMap<>();
        int sourceId = sourceNode.getId();
        for (Node node : graph.getVertices()) {
            result.put(node.getData(), distances[sourceId][node.getId()]);
        }
        return result;
    }

    @Override
    public List<int[]> findMinimumSpanningTree() {
        List<int[]> mst = new ArrayList<>();
        if (graph.getVertices().isEmpty()) return mst;

        boolean[] visited = new boolean[graph.getVertexCount()];
        int[] key = new int[graph.getVertexCount()];
        int[] parent = new int[graph.getVertexCount()];
        
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;

        for (int count = 0; count < graph.getVertexCount() - 1; count++) {
            int u = minKey(key, visited);
            visited[u] = true;

            for (int v = 0; v < graph.getVertexCount(); v++) {
                if (graph.getAdjacencyMatrix()[u][v] != Integer.MAX_VALUE && 
                    !visited[v] && 
                    graph.getAdjacencyMatrix()[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph.getAdjacencyMatrix()[u][v];
                }
            }
        }

        for (int i = 1; i < graph.getVertexCount(); i++) {
            if (parent[i] != -1) {
                mst.add(new int[]{
                    graph.getVertices().get(parent[i]).getData(),
                    graph.getVertices().get(i).getData(),
                    graph.getAdjacencyMatrix()[parent[i]][i]
                });
            }
        }
        return mst;
    }

    private int minKey(int[] key, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < graph.getVertexCount(); v++) {
            if (!visited[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    @Override
    public List<Integer> topologicalSort() {
        if (!graph.isDirected() || isCyclic()) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertexCount()];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            result.add(graph.getVertices().get(stack.pop()).getData());
        }
        return result;
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (graph.getAdjacencyMatrix()[v][i] != Integer.MAX_VALUE && !visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(v);
    }

    @Override
    public List<List<Integer>> findConnectedComponents() {
        List<List<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertexCount()];

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfsForComponents(i, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    private void dfsForComponents(int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(graph.getVertices().get(v).getData());

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (graph.getAdjacencyMatrix()[v][i] != Integer.MAX_VALUE && !visited[i]) {
                dfsForComponents(i, visited, component);
            }
        }
    }

    @Override
    public boolean hasBridge() {
        return !findBridges().isEmpty();
    }

    @Override
    public List<int[]> findBridges() {
        List<int[]> bridges = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertexCount()];
        int[] disc = new int[graph.getVertexCount()];
        int[] low = new int[graph.getVertexCount()];
        int[] parent = new int[graph.getVertexCount()];
        Arrays.fill(parent, -1);

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!visited[i]) {
                bridgeUtil(i, visited, disc, low, parent, bridges, new int[]{0});
            }
        }
        return bridges;
    }

    private void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent,
                          List<int[]> bridges, int[] time) {
        visited[u] = true;
        disc[u] = low[u] = ++time[0];

        for (int v = 0; v < graph.getVertexCount(); v++) {
            if (graph.getAdjacencyMatrix()[u][v] != Integer.MAX_VALUE) {
                if (!visited[v]) {
                    parent[v] = u;
                    bridgeUtil(v, visited, disc, low, parent, bridges, time);
                    low[u] = Math.min(low[u], low[v]);

                    if (low[v] > disc[u]) {
                        bridges.add(new int[]{
                            graph.getVertices().get(u).getData(),
                            graph.getVertices().get(v).getData(),
                            graph.getAdjacencyMatrix()[u][v]
                        });
                    }
                } else if (v != parent[u]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }
    }

    @Override
    public int getDiameter() {
        if (graph.getVertices().isEmpty()) return 0;

        int maxDistance = 0;
        for (Node node : graph.getVertices()) {
            Map<Integer, Integer> distances = findAllShortestPaths(node.getData());
            int maxDist = distances.values().stream()
                .filter(d -> d != Integer.MAX_VALUE)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
            maxDistance = Math.max(maxDistance, maxDist);
        }
        return maxDistance;
    }

    @Override
    public double getAveragePathLength() {
        if (graph.getVertices().size() <= 1) return 0.0;

        long totalDistance = 0;
        long pathCount = 0;

        for (Node source : graph.getVertices()) {
            Map<Integer, Integer> distances = findAllShortestPaths(source.getData());
            for (int distance : distances.values()) {
                if (distance != Integer.MAX_VALUE && distance > 0) {
                    totalDistance += distance;
                    pathCount++;
                }
            }
        }

        return pathCount > 0 ? (double) totalDistance / pathCount : 0.0;
    }

    @Override
    public int getVertexDegree(int vertex) {
        Node node = graph.findNodeByData(vertex);
        if (node == null) return 0;

        int degree = 0;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (graph.getAdjacencyMatrix()[node.getId()][i] != Integer.MAX_VALUE) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        return graph.getAdjacencyMatrix();
    }

    @Override
    public List<Node> getNeighbors(int vertex) {
        return graph.getNeighbors(vertex);
    }

    @Override
    public void resetGraph() {
        graph.resetVisitedStatus();
    }

    @Override
    public String printGraph() {
        return graph.toString();
    }
}
