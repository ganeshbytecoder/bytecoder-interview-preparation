package com.bytecoder.DSA.Part_2.Graphs.TypesOfGraph.AdjacencyMatrix;

import java.util.*;

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
    public List<Edge> findMinimumSpanningTree() {
        List<Edge> mst = new ArrayList<>();
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
                mst.add(new Edge(
                    graph.getVertices().get(parent[i]),
                    graph.getVertices().get(i),
                    graph.getAdjacencyMatrix()[parent[i]][i]
                ));
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

    @Override
    public List<Edge> findMinimumSpanningTree_UsingKurukal() {
        List<Edge> result = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        int[][] matrix = graph.getAdjacencyMatrix();
        
        // Collect all edges from adjacency matrix
        for (int i = 0; i < graph.getVertexCount(); i++) {
            for (int j = i + 1; j < graph.getVertexCount(); j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    Node source = graph.getVertices().get(i);
                    Node destination = graph.getVertices().get(j);
                    edges.add(new Edge(source, destination, matrix[i][j]));
                }
            }
        }
        
        // Sort edges by weight (cost)
        edges.sort(Comparator.comparingInt(Edge::getCost));
        
        // Initialize disjoint set for each vertex
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();
        
        // Initialize each vertex as its own parent
        for (Node vertex : graph.getVertices()) {
            parent.put(vertex.getData(), vertex.getData());
            rank.put(vertex.getData(), 0);
        }
        
        // Process edges in ascending order of weight
        for (Edge edge : edges) {
            int sourceRoot = find(edge.getStart().getData(), parent);
            int destRoot = find(edge.getEnd().getData(), parent);
            
            // If including this edge doesn't create a cycle
            if (sourceRoot != destRoot) {
                result.add(edge);
                union(sourceRoot, destRoot, parent, rank);
            }
        }
        
        return result;
    }
    
    // Helper method for Kruskal's algorithm to find the root of a set
    private int find(int vertex, Map<Integer, Integer> parent) {
        if (parent.get(vertex) != vertex) {
            parent.put(vertex, find(parent.get(vertex), parent));
        }
        return parent.get(vertex);
    }
    
    // Helper method for Kruskal's algorithm to merge two sets
    private void union(int x, int y, Map<Integer, Integer> parent, Map<Integer, Integer> rank) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);
        
        if (rank.get(rootX) < rank.get(rootY)) {
            parent.put(rootX, rootY);
        } else if (rank.get(rootX) > rank.get(rootY)) {
            parent.put(rootY, rootX);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rank.get(rootX) + 1);
        }
    }

    @Override
    public List<Integer> topologicalSort_UsingKahns() {
        List<Integer> result = new ArrayList<>();
        int[][] matrix = graph.getAdjacencyMatrix();
        int vertices = graph.getVertexCount();
        
        // Calculate in-degree for each vertex
        int[] inDegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrix[j][i] != Integer.MAX_VALUE) {
                    inDegree[i]++;
                }
            }
        }
        
        // Add vertices with 0 in-degree to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Process vertices in topological order
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(graph.getVertices().get(vertex).getData());
            
            // Reduce in-degree of adjacent vertices
            for (int i = 0; i < vertices; i++) {
                if (matrix[vertex][i] != Integer.MAX_VALUE) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }
        
        // If result size is less than total vertices, graph has a cycle
        if (result.size() != vertices) {
            return new ArrayList<>(); // Return empty list for cyclic graph
        }
        
        return result;
    }
}
