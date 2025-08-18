package com.bytecoder.DSA.Part_4.Graphs.AdjacencyMatrix;

import java.util.*;
import java.util.stream.Collectors;

public class GraphProblemsAdjMatrixImpl<T> implements GraphProblems<T> {

    private final Graph<T> graph;

    public GraphProblemsAdjMatrixImpl(Graph<T> graph){
        this.graph= graph;
    }


    @Override
    public void addNode(Node<T> node) {
        graph.getVertices().add(node);
    }


    @Override
    public void removeNode(Node<T> node) {
        for (int i = 0; i < graph.getMatrix().length; i++) {
            graph.getMatrix()[node.id][i] = -1;
        }
        graph.getVertices().remove(node);

    }

    @Override
    public List<Node<T>> getAllNodes() {
        return graph.getVertices();
    }


    @Override
    public void addEdge(Edge edge) {
        int i = edge.start.id;
        int j = edge.end.id;
        graph.getMatrix()[i][j] = edge.cost;

        if (!this.graph.isDirected()) {
            graph.getMatrix()[j][i] = edge.cost;
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        int i = edge.start.id;
        int j = edge.end.id;
        graph.getMatrix()[i][j] = -1;

        if (!this.graph.isDirected()) {
            graph.getMatrix()[j][i] = -1;
        }
    }

    @Override
    public List<Edge<T>> getAllEdges() {
        List<Edge<T>> edges = new ArrayList<>();

        for (int i = 0; i < graph.getMatrix().length; i++) {
            for (int j = 0; j < graph.getMatrix()[0].length; j++) {

                if (graph.getMatrix()[i][j] != -1) {
                    edges.add(new Edge<>(getNodeById(i), getNodeById(j), graph.getMatrix()[i][j]));
                }
            }
        }

        return edges;
    }


    @Override
    public void printGraph() {
        for (Node<T> node : graph.getVertices()) {
            System.out.print("  " + node.data.toString() + " ");
        }
        System.out.println();
        for (int i = 0; i < graph.getMatrix().length; i++) {
            System.out.print(graph.getVertices().get(i).data.toString() + " ");

            for (int j = 0; j < graph.getMatrix().length; j++) {
                System.out.print(graph.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("Printing adjacency list graph");

        for (int i = 0; i < graph.getNumberOfNode(); i++) {

            System.out.print("\n " + getNodeById(i).getData() + " -> ");

            for (int j = 0; j < graph.getNumberOfNode(); j++) {
                if (graph.getMatrix()[i][j] != -1) {
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

        graph.getVertices().forEach(vertex -> vertex.setVisited(false));
        // or
        boolean[] visited = new boolean[getAllNodes().size()];

        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (!graph.getVertices().get(i).visited) {
                dfsTraversal(graph.getVertices().get(i));
            }
        }


//        using stack
        Stack<Node<T>> stack = new Stack<>();
        stack.add(graph.getVertices().get(0));

        while (!stack.empty()) {
            Node<T> node = stack.pop();
            System.out.println(" " + node);
            visited[node.id] = true;
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (!visited[i] && graph.getMatrix()[node.id][i] != -1) {
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

        queue.add(graph.getVertices().get(0));
        System.out.println();
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            System.out.println(" " + node);
            visited[node.id] = true;
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (!visited[i] && graph.getMatrix()[node.id][i] != -1) {
                    queue.add(getNodeById(i));
                }
            }
        }

        System.out.println(" \n Traversing BFS");
        graph.getVertices().forEach(vertex -> vertex.setVisited(false));
        queue = new LinkedList<>();
        queue.add(getNodeById(0));
        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (!graph.getVertices().get(i).visited) {
                bfsTraversal(queue);
            }
        }


    }

    private boolean detectCycleForUndirectedWithDFS(Node<T> node, Node<T> parent) {
        System.out.println(node + " - " + parent);

        node.setVisited(true);
        boolean ans = false;
        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            int connect = graph.getMatrix()[node.getId()][i];
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
        for (int neighbourId = 0; neighbourId < graph.getNumberOfNode(); neighbourId++) {
            int connect = graph.getMatrix()[node.getId()][neighbourId];
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
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (graph.getMatrix()[curr_node.id][i] != -1) {
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
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (graph.getMatrix()[curr_node.id][i] != -1) {
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

        if (graph.isDirected()) {

            System.out.println("directed graphs");

            if (dfs) {
//        Detect Cycle in Undirected GraphProblems using BFS/DFS Algorithm
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
//        Detect Cycle in Undirected GraphProblems using BFS/DFS Algorithm
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

    private void implementDFSTopologicalSortingUtil(Node<T> node, Stack<Node<T>> stack) {

        node.setVisited(true);

        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (graph.getMatrix()[node.id][i] != -1) {
                if (!getNodeById(i).isVisited()) {
                    implementDFSTopologicalSortingUtil(getNodeById(i), stack);
                }
            }
        }
        stack.push(node);

    }

    @Override
    public void implementDFSTopologicalSorting() {
        Stack<Node<T>> stack = new Stack<>();

        for (Node<T> node : getAllNodes()) {
            if (!node.isVisited()) {
                implementDFSTopologicalSortingUtil(node, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    @Override
    public void implementBFSTopologicalSorting() {

        int[] in_degree = new int[graph.getNumberOfNode()];

        for(Node<T> vertex : getAllNodes()){
            for(int neighbour_index =0; neighbour_index< graph.getNumberOfNode() ; neighbour_index++ ){
                if(graph.getMatrix()[vertex.id][neighbour_index] != -1)
                    in_degree[neighbour_index]+=1;
            }
        }

        Queue<Node<T>> queue = new LinkedList<>();
        for(int i =0 ; i< graph.getNumberOfNode() ; i++){
            if(in_degree[i]==0){
                queue.add(getNodeById(i));
            }
        }

        List<Node<T>> result = new ArrayList<>();
        int count =0;
        while (!queue.isEmpty()){

            Node<T> node = queue.poll();
            result.add(node);

            for(int neighbour_index =0; neighbour_index< graph.getNumberOfNode() ; neighbour_index++ ){
                if(graph.getMatrix()[node.id][neighbour_index] != -1){
                    if(--in_degree[neighbour_index] ==0) {
                        queue.add(getNodeById(neighbour_index));
                    }
                }
            }
            count++;
        }

        if(count == graph.getNumberOfNode()){
            for (Node<T> node : result){
                System.out.println(node);
            }
        }else {
            System.out.println("Not possible");
        }

    }

    @Override
    public void allTopologicalSorting() {

    }

    @Override
    public boolean isTopologicalSortingValid(Node<T>[] sorting) {
        return false;
    }

    @Override
    public void printPrimMST() {

        Comparator<Edge<T>> comparator = (edge1, edge2) -> edge1.getCost() - edge2.getCost();
        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>(comparator);

//         pick start node
        priorityQueue.add(new Edge<>(getNodeById(0), getNodeById(0), 0));

        List<Edge<T>> ansEdges = new ArrayList<>();

        while (!priorityQueue.isEmpty()) {
            Edge<T> edge = priorityQueue.poll();

            if (edge.getEnd().isVisited()) {
                continue;
            }
            edge.getEnd().setVisited(true);
            ansEdges.add(edge);

            for (Node<T> neighbour : getAllAdjacent(edge.getEnd())) {
                priorityQueue.add(getEdgeBetween(edge.getEnd(), neighbour));
            }
        }

        for (Edge<T> edge : ansEdges) {
            System.out.println(edge);
        }


    }

    Node<T> find(Node<T> node, HashMap<Node<T>, Node<T>> parent) {
        if (node == parent.get(node)) {
            return parent.get(node);
        }
        return find(parent.get(node), parent);
    }

    void union(Node<T> src, Node<T> dst, HashMap<Node<T>, Node<T>> parent, int[] rank) {
        Node<T> srcRoot = find(src, parent);
        Node<T> dstRoot = find(dst, parent);

        if (rank[srcRoot.id] > rank[dstRoot.id]) {
            parent.put(dstRoot, srcRoot);
        } else if (rank[srcRoot.id] < rank[dstRoot.id]) {
            parent.put(srcRoot, dstRoot);
        } else {
            parent.put(srcRoot, dstRoot);
            rank[srcRoot.id]++;
        }
    }


    @Override
    public List<Edge<T>> printKrushkalMST() {

        List<Edge<T>> edges = getAllEdges();
        edges.sort((e1, e2) -> e1.getCost() - e2.getCost());


        List<Edge<T>> result = new ArrayList<>();

        int[] rank = new int[graph.getNumberOfNode()];

        HashMap<Node<T>, Node<T>> parent = new HashMap<>();

        for (Edge<T> edge : edges) {
            parent.put(edge.getStart(), edge.getStart());
            parent.put(edge.getEnd(), edge.getEnd());
        }


        int e = 0;
        int i = 0;
        while (e < graph.getNumberOfNode() - 1) {
            Edge<T> edge = edges.get(i);
            Node<T> srcRoot = find(edge.getStart(), parent);
            Node<T> dstRoot = find(edge.getEnd(), parent);

            if (srcRoot != dstRoot) {
                result.add(edge);
                union(edge.getStart(), edge.getEnd(), parent, rank);
                e++;
            }
            i++;
        }

        for (Edge<T> edge : result) {
            System.out.println(edge);
        }


        return result;


    }

    @Override
    public void findShortestPathUsingDijkstra() {

    }

    @Override
    public void findShortestPathUsingBellmanFord() {

    }


    public List<Edge<T>> printKrushkalMST_M2() {

        PriorityQueue<Edge<T>> edges = new PriorityQueue<>((e1, e2) -> e1.getCost() - e2.getCost());
        edges.addAll(getAllEdges());

        List<Edge<T>> result = new ArrayList<>();

        int[] rank = new int[graph.getNumberOfNode()];

        HashMap<Node<T>, Node<T>> parent = new HashMap<>();

        for (Edge<T> edge : edges) {
            parent.put(edge.getStart(), edge.getStart());
            parent.put(edge.getEnd(), edge.getEnd());
        }


        int e = 0;
        while (e < graph.getNumberOfNode() - 1) {
            Edge<T> edge = edges.poll();
            Node<T> srcRoot = find(edge.getStart(), parent);
            Node<T> dstRoot = find(edge.getEnd(), parent);

            if (srcRoot != dstRoot) {
                result.add(edge);
                union(edge.getStart(), edge.getEnd(), parent, rank);
                e++;
            }

        }

        for (Edge<T> edge : result) {
            System.out.println(edge);
        }


        return result;


    }


    private void processNode(Node<T> node) {
        System.out.print(node.data.toString() + "  ");
        node.setVisited(true);

    }

    private void dfsTraversal(Node<T> node) {
        node.setVisited(true);
        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (graph.getMatrix()[node.id][i] != -1 && !getNodeById(i).isVisited()) {
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

        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (graph.getMatrix()[node.id][i] != -1 && !getNodeById(i).isVisited()) {
                queue.add(getNodeById(i));
            }
        }

        bfsTraversal(queue);


    }

    private Node<T> getNodeById(int id) {

        List<Node<T>> nodes = graph.getVertices().stream().filter(node -> node.getId() == id).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


    public Node<T> getNodeByName(T data) {

        List<Node<T>> nodes = graph.getVertices().stream().filter(node -> node.getData() == data).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }


    private Edge<T> getEdgeBetween(Node<T> start, Node<T> end) {

        List<Edge<T>> edges = getAllEdges().stream().filter(edge -> edge.getStart().equals(start) && edge.getEnd().equals(end)).collect(Collectors.toList());
        if (!edges.isEmpty()) {
            return edges.get(0);
        }
        return null;
    }

    @Override
    public boolean hasEdge(Node src, Node end) {
        return graph.getMatrix()[src.id][end.id] != -1 || graph.getMatrix()[end.id][src.id] != -1;
    }


    //    get all neighbours
    public List<Node<T>> getAllAdjacent(Node<T> node) {
        List<Node<T>> neighbours = new ArrayList<>();

        for (int j = 0; j < graph.getMatrix()[node.getId()].length; j++) {
            if (graph.getMatrix()[node.getId()][j] != -1) {
                neighbours.add(getNodeById(j));
            }
        }


        return neighbours;
    }


}
