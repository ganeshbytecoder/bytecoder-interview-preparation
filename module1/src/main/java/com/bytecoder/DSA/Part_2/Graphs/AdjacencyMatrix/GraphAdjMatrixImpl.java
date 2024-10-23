package com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix;

import java.util.*;
import java.util.stream.Collectors;

public class GraphAdjMatrixImpl<T> implements Graph<T> {

    private final List<Node<T>> vertices = new ArrayList<>();

    private final int[][] matrix;

    private final int numberOfNode;

    private final boolean directed;

    GraphAdjMatrixImpl(int n, boolean directed) {
        this.numberOfNode = n;
        this.matrix = new int[n][n];
        this.directed = directed;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                it can be 0 as well
                matrix[i][j] = -1;
            }
        }
    }


    @Override
    public void addNode(Node node) {
        vertices.add(node);
    }


    @Override
    public void removeNode(Node node) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[node.id][i] = -1;
        }
        vertices.remove(node);

    }

    @Override
    public List<Node<T>> getAllNodes() {
        return vertices;
    }


    @Override
    public void addEdge(Edge edge) {
        int i = edge.start.id;
        int j = edge.end.id;
        matrix[i][j] = edge.cost;

        if (!this.directed) {
            matrix[j][i] = edge.cost;
        }
    }

    @Override
    public void removeEdge(Edge edge) {
        int i = edge.start.id;
        int j = edge.end.id;
        matrix[i][j] = -1;

        if (!this.directed) {
            matrix[j][i] = -1;
        }
    }

    @Override
    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] != -1) {
                    edges.add(new Edge<>(getNodeById(i), getNodeById(j), matrix[i][j]));
                }
            }
        }

        return edges;
    }

    @Override
    public boolean hasEdge(Node src, Node end) {
        return matrix[src.id][end.id] != -1 || matrix[end.id][src.id] != -1;
    }


    @Override
    public void printGraph() {
        for (Node<T> node : vertices) {
            System.out.print("  " + node.data.toString() + " ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(vertices.get(i).data.toString() + " ");

            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("Printing adjacency list graph");

        for (int i = 0; i < numberOfNode; i++) {

            System.out.print("\n " + getNodeById(i).getData() + " -> ");

            for (int j = 0; j < numberOfNode; j++) {
                if (matrix[i][j] != -1) {
                    System.out.print(getNodeById(j).getData() + " ,");
                }

            }
        }
        System.out.println();

        getAllEdges().stream().forEach(System.out::println);
    }


    @Override
    public void dfs() {
        System.out.println("Traversing DFS");

        vertices.forEach(vertex -> vertex.setVisited(false));
        // or
        boolean[] visited = new boolean[getAllNodes().size()];

        for (int i = 0; i < numberOfNode; i++) {
            if (!vertices.get(i).visited) {
                dfsTraversal(vertices.get(i));
            }
        }


//        using stack
        Stack<Node<T>> stack = new Stack<>();
        stack.add(vertices.get(0));

        while (!stack.empty()) {
            Node<T> node = stack.pop();
            System.out.println(" " + node);
            visited[node.id] = true;
            for (int i = 0; i < numberOfNode; i++) {
                if (!visited[i] && matrix[node.id][i] != -1) {
                    stack.add(getNodeById(i));
                }
            }
        }


    }

    @Override
    public void bfs() {

        //        using queue
        Queue<Node<T>> queue = new LinkedList<>();
        boolean[] visited = new boolean[getAllNodes().size()];

        queue.add(vertices.get(0));
        System.out.println();
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            System.out.println(" " + node);
            visited[node.id] = true;
            for (int i = 0; i < numberOfNode; i++) {
                if (!visited[i] && matrix[node.id][i] != -1) {
                    queue.add(getNodeById(i));
                }
            }
        }

        System.out.println(" \n Traversing BFS");
        vertices.forEach(vertex -> vertex.setVisited(false));
        queue = new LinkedList<>();
        queue.add(getNodeById(0));
        for (int i = 0; i < numberOfNode; i++) {
            if (!vertices.get(i).visited) {
                bfsTraversal(queue);
            }
        }


    }

    private boolean detectCycleForUndirectedWithDFS(Node<T> node, Node<T> parent) {
        System.out.println(node + " - " + parent);

        node.setVisited(true);
        boolean ans = false;
        for (int i = 0; i < numberOfNode; i++) {
            int connect = matrix[node.getId()][i];
            if (connect != -1) {
                if (getNodeById(i).isVisited() && getNodeById(i) != parent) {
                    return true;
                }
                if (!getNodeById(i).isVisited()) {
                    ans = detectCycleForUndirectedWithDFS(getNodeById(i), node);
                }

            }
            if (ans) {
                return true;
            }
        }
        return ans;

    }


    private boolean detectCycleForDirectedWithDFS(Node<T> node, List<Node<T>> recCallStack) {

        node.setVisited(true);
        recCallStack.add(node);
        boolean ans = false;
        for (int neighbourId = 0; neighbourId < numberOfNode; neighbourId++) {
            int connect = matrix[node.getId()][neighbourId];
            if (connect != -1) {
                if (getNodeById(neighbourId).isVisited() && recCallStack.contains(getNodeById(neighbourId))) {
                    return true;
                }
                if (!getNodeById(neighbourId).isVisited()) {
                    ans = detectCycleForUndirectedWithDFS(getNodeById(neighbourId), node);
                }

            }
            if (ans) {
                return true;
            }
        }
        recCallStack.remove(node);
        return ans;

    }


    private boolean detectCycleForUndirectedGraphWithBFS(Node<T> node) {


        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node<T> curr_node = queue.poll();
            curr_node.setVisited(true);
            System.out.println("parent - " + curr_node);
            for (int i = 0; i < numberOfNode; i++) {
                if (matrix[curr_node.id][i] != -1) {
                    System.out.println(getNodeById(i));
                    if (getNodeById(i).isVisited() && getNodeById(i) != curr_node) {
                        return true;
                    } else if (!getNodeById(i).isVisited()) {
                        queue.add(getNodeById(i));
                    }
                }
            }
        }

        return false;

    }


    private boolean detectCycleForDirectedGraphWithBFS(Node<T> node) {

        System.out.println("Calling detectCycleForDirectedGraphWithBFS");


        Queue<Node<T>> queue = new LinkedList<>();

        List<Node<T>> recCallStack = new ArrayList<>();

        queue.add(node);

        while (!queue.isEmpty()) {
            Node<T> curr_node = queue.poll();
            curr_node.setVisited(true);
            recCallStack.add(curr_node);

            System.out.println("parent - " + curr_node);
            for (int i = 0; i < numberOfNode; i++) {
                if (matrix[curr_node.id][i] != -1) {
                    System.out.println(getNodeById(i));
                    if (getNodeById(i).isVisited() && recCallStack.contains(getNodeById(i))) {
                        return true;
                    } else if (!getNodeById(i).isVisited()) {
                        queue.add(getNodeById(i));
                    }
                }
            }

        }


        return false;

    }


    @Override
    public boolean isCyclic() {
        boolean dfs = false;
        boolean ans = false;

        if (directed) {

            System.out.println("directed graphs");

            if (dfs) {
//        Detect Cycle in Undirected Graph using BFS/DFS Algorithm
                for (Node<T> node : getAllNodes()) {
                    if (!node.isVisited()) {
                        ans = detectCycleForDirectedWithDFS(node, new ArrayList<>());
                    }
                    if (ans) {
                        System.out.println("Cycle is detected with detectCycleForDirectedWithDFS");
                        return true;
                    }
                }

                System.out.println("Cycle is not detected with detectCycleForDirectedWithDFS");
                return false;
            } else {
                getAllNodes().stream().forEach(node -> node.setVisited(false));
                for (Node<T> node : getAllNodes()) {
                    if (!node.isVisited()) {
                        ans = detectCycleForDirectedGraphWithBFS(node);
                        if (ans) {
                            System.out.println("Cycle is detected with detectCycleForDirectedGraphWithBFS");
                            return true;
                        }
                    }

                }

                System.out.println("Cycle is not detected with detectCycleForDirectedGraphWithBFS");


            }

        } else {
            if (dfs) {
//        Detect Cycle in Undirected Graph using BFS/DFS Algorithm
                for (Node<T> node : getAllNodes()) {
                    if (!node.isVisited()) {
                        ans = detectCycleForUndirectedWithDFS(node, null);
                    }
                    if (ans) {
                        System.out.println("Cycle is detected with DFS");
                        return true;
                    }
                }

                System.out.println("Cycle is not detected");
                return false;
            } else {

                getAllNodes().stream().forEach(node -> node.setVisited(false));
                for (Node<T> node : getAllNodes()) {
                    if (!node.isVisited()) {
                        ans = detectCycleForUndirectedGraphWithBFS(node);
                        if (ans) {
                            System.out.println("Cycle is detected with detectCycleForUndirectedGraphWithBFS");
                            return true;
                        }
                    }

                }

                System.out.println("Cycle is not detected with detectCycleForUndirectedGraphWithBFS");

            }
        }


        return false;
    }


    @Override
    public void implementDFSTopologicalSorting() {

    }

    @Override
    public void implementBFSTopologicalSorting() {

    }

    @Override
    public void printPrimMST() {

    }

    @Override
    public void printKrushkalMST() {

    }

    private void processNode(Node<T> node) {
        System.out.print(node.data.toString() + "  ");
        node.setVisited(true);

    }

    private void dfsTraversal(Node<T> node) {
        node.setVisited(true);
        for (int i = 0; i < numberOfNode; i++) {
            if (matrix[node.id][i] != -1 && !getNodeById(i).isVisited()) {
                dfsTraversal(getNodeById(i));
            }
        }
        processNode(node);

    }

    private void bfsTraversal(Queue<Node<T>> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Node<T> node = queue.poll();

        processNode(node);
        node.setVisited(true);

        for (int i = 0; i < numberOfNode; i++) {
            if (matrix[node.id][i] != -1 && !getNodeById(i).isVisited()) {
                queue.add(getNodeById(i));
            }
        }

        bfsTraversal(queue);


    }

    private Node<T> getNodeById(int id) {

        List<Node<T>> nodes = vertices.stream().filter(node -> node.getId() == id).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


    public Node<T> getNodeByName(T data) {

        List<Node<T>> nodes = vertices.stream().filter(node -> node.getData() == data).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


}
