package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import java.util.*;
import java.util.stream.Collectors;

public class GraphProblemsImpl implements GraphProblems {
    private final Graph graph;

    public GraphProblemsImpl(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void addVertex(int data) {
        graph.addVertex(data);
    }

    @Override
    public void removeVertex(int data) {
        graph.removeVertex(data);
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
    public List<Edge> getAllEdges() {
        return graph.getAllEdges();
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

        Set<Node> visited = new HashSet<>();
        dfsUtil(start, visited, result);
        return result;
    }

    private void dfsUtil(Node node, Set<Node> visited, List<Integer> result) {
        visited.add(node);
        result.add(node.getData());
        
        for (Node neighbor : node.getNeighbors().keySet()) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited, result);
            }
        }
    }

    @Override
    public List<Integer> breadthFirstSearch(int startVertex) {
        List<Integer> result = new ArrayList<>();
        Node start = graph.findNodeByData(startVertex);
        if (start == null) return result;

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current.getData());
            
            for (Node neighbor : current.getNeighbors().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

    @Override
    public boolean isCyclic() {
        Set<Node> visited = new HashSet<>();
        Set<Node> recursionStack = new HashSet<>();

        for (Node node : graph.getVertices()) {
            if (isCyclicUtil(node, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicUtil(Node node, Set<Node> visited, Set<Node> recursionStack) {
        if (recursionStack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        recursionStack.add(node);

        for (Node neighbor : node.getNeighbors().keySet()) {
            if (isCyclicUtil(neighbor, visited, recursionStack)) {
                return true;
            }
        }
        recursionStack.remove(node);
        return false;
    }

    @Override
    public boolean isConnected() {
        if (graph.getVertices().isEmpty()) return true;
        
        Set<Node> visited = new HashSet<>();
        dfsUtil(graph.getVertices().get(0), visited, new ArrayList<>());
        
        return visited.size() == graph.getVertices().size();
    }

    @Override
    public int getVertexCount() {
        return graph.getVertices().size();
    }

    @Override
    public int getEdgeCount() {
        return getAllEdges().size();
    }

    class DistancePair {
        Node node;
        Integer distance;

        public DistancePair(Node source, int distance) {
            this.node = source;
            this.distance = distance;
        }
    }

    @Override
    public List<Integer> findShortestPath(int source, int destination) {
        Node sourceNode = graph.findNodeByData(source);
        Node destNode = graph.findNodeByData(destination);
        if (sourceNode == null || destNode == null) return new ArrayList<>();

        Map<Node, Node> previousMap = new HashMap<>();

        Map<Node, Integer> distances = new HashMap<>();

        PriorityQueue<DistancePair> pq = new PriorityQueue<>(
            Comparator.comparingInt(n -> n.distance)
        );
        Set<Node> visited = new HashSet<>();

        // Initialize distances
        for (Node node : graph.getVertices()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(sourceNode, 0);

        pq.add(new DistancePair(sourceNode, 0));

        while (!pq.isEmpty()) {
            DistancePair current = pq.poll();
            if (current.node == destNode) break;

            if (visited.contains(current.node)) continue;

            visited.add(current.node);

            for (Map.Entry<Node, Integer> entry : current.node.getNeighbors().entrySet()) {
                Node neighbor = entry.getKey();
                int newDist = current.distance + entry.getValue();

                if (newDist < distances.get(neighbor)) {
//                    updating (relaxing distance)
                    distances.put(neighbor, newDist);

                    previousMap.put(neighbor, current.node);

//                    adding in queue to precess next time
                    pq.add(new DistancePair(entry.getKey(), newDist));
                }
            }
        }

        if (!previousMap.containsKey(destNode)) return new ArrayList<>();
// this is where path is discovered
        List<Integer> path = new ArrayList<>();
        Node current = destNode;
        while (current != null) {
            path.add(0, current.getData());
            current = previousMap.get(current);
        }
        return path;
    }

    @Override
    public Map<Integer, Integer> findAllShortestPaths(int source) {
        Node sourceNode = graph.findNodeByData(source);
        if (sourceNode == null) return new HashMap<>();

        Map<Node, Integer> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(
            Comparator.comparingInt(n -> distances.getOrDefault(n, Integer.MAX_VALUE))
        );

        // Initialize distances
        for (Node node : graph.getVertices()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(sourceNode, 0);
        pq.add(sourceNode);

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Map.Entry<Node, Integer> entry : current.getNeighbors().entrySet()) {
                Node neighbor = entry.getKey();
                int newDist = distances.get(current) + entry.getValue();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(neighbor);
                }
            }
        }

        return distances.entrySet().stream()
            .collect(Collectors.toMap(
                entry -> entry.getKey().getData(),
                Map.Entry::getValue
            ));
    }

// start with 0 edge and priority queue -> using graph
    @Override
    public List<Edge> findMinimumSpanningTree() {
        if (graph.getVertices().isEmpty()) return new ArrayList<>();

        List<Edge> mst = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(
            Comparator.comparing(Edge::getWeight)
        );

        // Start with the first vertex
        Node start = graph.getVertices().get(0);
        visited.add(start);

        // Add all edges from the start vertex
        for (Map.Entry<Node, Integer> entry : start.getNeighbors().entrySet()) {
            pq.add(new Edge(start, entry.getKey(), entry.getValue()));
        }

        while (!pq.isEmpty() && visited.size() < graph.getVertices().size()) {
            Edge edge = pq.poll();
            if (visited.contains(edge.getDestination())) continue;

            visited.add(edge.getDestination());
            mst.add(edge);

            for (Map.Entry<Node, Integer> entry : edge.getDestination().getNeighbors().entrySet()) {
                if (!visited.contains(entry.getKey())) {
                    pq.add(new Edge(edge.getDestination(), entry.getKey(), entry.getValue()));
                }
            }
        }

        return mst;
    }

//    find(x) with path compression: O(α(N)) ≈ O(1)
//    union(x, y) with rank optimization: O(α(N)) ≈ O(1)
//    get all edges sorted and applied DSU -> using edges list
    @Override
    public List<Edge> findMinimumSpanningTree_UsingKurukal() {
        List<Edge> result = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(getAllEdges());
        
        // Sort edges by weight
        edges.sort(Comparator.comparingInt(e -> e.getWeight()));
        
        // Initialize disjoint set for each vertex
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();
        
        // Initialize each vertex as its own parent
        for (Node vertex : getAllVertices()) {
            parent.put(vertex.getData(), vertex.getData());
            rank.put(vertex.getData(), 0);
        }
        
        for (Edge edge : edges) {
            int sourceRoot = find(edge.getSource().getData(), parent);
            int destRoot = find(edge.getDestination().getData(), parent);
            
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
//            meaning parent[x] = y -> x is added to y ->  // Attach smaller tree to larger tree
        } else if (rank.get(rootX) > rank.get(rootY)) {
            parent.put(rootY, rootX);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rank.get(rootX) + 1);
        }
    }

//    MST -> PK -> P -> graph starts PriorityQueue 0 edge +  BFS and K -> PriorityQueue + all edges + DSU
//    note -> use only if you know graph is DAG for this (stack and DFS) else use kahn's algorithm (in-degree and queue)
    @Override
    public List<Integer> topologicalSort() {
        if (!graph.isDirected() || isCyclic()) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        for (Node node : graph.getVertices()) {
            if (!visited.contains(node)) {
                topologicalSortUtil(node, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop().getData());
        }
        return result;
    }

    private void topologicalSortUtil(Node node, Set<Node> visited, Stack<Node> stack) {
        visited.add(node);
        for (Node neighbor : node.getNeighbors().keySet()) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }

    @Override
    public List<Integer> topologicalSort_UsingKahns() {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Initialize in-degree for all vertices
        for (Node vertex : getAllVertices()) {
            inDegree.put(vertex.getData(), 0);
        }
        
        // Calculate in-degree for each vertex
        for (Edge edge : getAllEdges()) {
            inDegree.put(edge.getDestination().getData(), 
                        inDegree.get(edge.getDestination().getData()) + 1);
        }
        
        // Add vertices with 0 in-degree to queue
        for (Node vertex : getAllVertices()) {
            if (inDegree.get(vertex.getData()) == 0) {
                queue.offer(vertex.getData());
            }
        }
        
        // Process vertices in topological order
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            // Reduce in-degree of adjacent vertices
            Node currentNode = graph.findNodeByData(vertex);
            for (Node neighbour : currentNode.neighbors.keySet()) {
                int dest = neighbour.getData();
                inDegree.put(dest, inDegree.get(dest) - 1);
                
                // If in-degree becomes 0, add to queue
                if (inDegree.get(dest) == 0) {
                    queue.offer(dest);
                }
            }
        }
        
        // If result size is less than total vertices, graph has a cycle
        if (result.size() != getAllVertices().size()) {
            return new ArrayList<>(); // Return empty list for cyclic graph
        }
        
        return result;
    }



    @Override
    public List<List<Integer>> findConnectedComponents() {
        List<List<Integer>> components = new ArrayList<>();
        Set<Node> visited = new HashSet<>();

        for (Node node : graph.getVertices()) {
            if (!visited.contains(node)) {
                List<Integer> component = new ArrayList<>();
                dfsUtil(node, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    @Override
    public boolean hasBridge() {
        return !findBridges().isEmpty();
    }

    @Override
    public List<Edge> findBridges() {
        List<Edge> bridges = new ArrayList<>();
        Map<Node, Integer> discoveryTime = new HashMap<>();
        Map<Node, Integer> lowTime = new HashMap<>();
        Set<Node> visited = new HashSet<>();
        int[] time = {0};

        for (Node node : graph.getVertices()) {
            if (!visited.contains(node)) {
                findBridgesUtil(node, null, visited, discoveryTime, lowTime, time, bridges);
            }
        }

        return bridges;
    }

    private void findBridgesUtil(Node node, Node parent, Set<Node> visited,
                               Map<Node, Integer> discoveryTime, Map<Node, Integer> lowTime,
                               int[] time, List<Edge> bridges) {
        visited.add(node);
        discoveryTime.put(node, time[0]);
        lowTime.put(node, time[0]);
        time[0]++;

        for (Map.Entry<Node, Integer> entry : node.getNeighbors().entrySet()) {
            Node neighbor = entry.getKey();
            if (!visited.contains(neighbor)) {
                findBridgesUtil(neighbor, node, visited, discoveryTime, lowTime, time, bridges);
                lowTime.put(node, Math.min(lowTime.get(node), lowTime.get(neighbor)));

                if (lowTime.get(neighbor) > discoveryTime.get(node)) {
                    bridges.add(new Edge(node, neighbor, entry.getValue()));
                }
            } else if (neighbor != parent) {
                lowTime.put(node, Math.min(lowTime.get(node), discoveryTime.get(neighbor)));
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
        return node.getNeighbors().size();
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
