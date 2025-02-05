package com.bytecoder.DSA.Part_2_With_Integer.IntegerGraph.AdjacencyMatrix;

import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge;
import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Graph;
import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.GraphProblems;

import java.util.*;
import java.util.stream.Collectors;

public class GraphProblemsAdjMatrixImpl implements GraphProblems {

    private final com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Graph graph;

    public GraphProblemsAdjMatrixImpl(Graph graph){
        this.graph= graph;
    }


    @Override
    public void addNode(int node) {
        graph.getVertices().add(node);
    }


    @Override
    public void removeNode(int node) {
        for (int i = 0; i < graph.getMatrix().length; i++) {
            graph.getMatrix()[node][i] = -1;
        }
        graph.getVertices().remove(node);

    }

    @Override
    public List<Integer> getAllNodes() {
        return graph.getVertices();
    }


    @Override
    public void addEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge) {
        int i = edge.start;
        int j = edge.end;
        graph.getMatrix()[i][j] = edge.cost;

        if (!this.graph.isDirected()) {
            graph.getMatrix()[j][i] = edge.cost;
        }
    }

    @Override
    public void removeEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge) {
        int i = edge.start;
        int j = edge.end;
        graph.getMatrix()[i][j] = -1;

        if (!this.graph.isDirected()) {
            graph.getMatrix()[j][i] = -1;
        }
    }

    @Override
    public List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> getAllEdges() {
        List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> edges = new ArrayList<>();

        for (int i = 0; i < graph.getMatrix().length; i++) {
            for (int j = 0; j < graph.getMatrix()[0].length; j++) {

                if (graph.getMatrix()[i][j] != -1) {
                    edges.add(new com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge<>(i, j, graph.getMatrix()[i][j]));
                }
            }
        }

        return edges;
    }


    @Override
    public void printGraph() {
        for (int node : graph.getVertices()) {
            System.out.print("  " + node + " ");
        }
        System.out.println();
        for (int i = 0; i < graph.getMatrix().length; i++) {
            System.out.print(graph.getVertices().get(i).toString() + " ");

            for (int j = 0; j < graph.getMatrix().length; j++) {
                System.out.print(graph.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("Printing adjacency list graph");

        for (int i = 0; i < graph.getNumberOfNode(); i++) {

            System.out.print("\n " + i + " -> ");

            for (int j = 0; j < graph.getNumberOfNode(); j++) {
                if (graph.getMatrix()[i][j] != -1) {
                    System.out.print(j + " ,");
                }

            }
        }
        System.out.println();

        getAllEdges().stream().forEach(System.out::println);
    }


    @Override
    public void dfs() {
        System.out.println("Traversing DFS");


        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (!graph.getVisited()[i]) {
                dfsTraversal(i);
            }
        }


//        using stack
        Stack<Integer> stack = new Stack<>();
        stack.add(graph.getVertices().get(0));

        while (!stack.empty()) {
            Integer node = stack.pop();
            System.out.println(" " + node);
            graph.getVisited()[node] = true;
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (!graph.getVisited()[i] && graph.getMatrix()[node][i] != -1) {
                    stack.add(i);
                }
            }
        }


    }

    @Override
    public void bfs() {

        //        using queue
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[getAllNodes().size()];

        queue.add(graph.getVertices().get(0));
        System.out.println();
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            System.out.println(" " + node);
            visited[node] = true;
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (!visited[i] && graph.getMatrix()[node][i] != -1) {
                    queue.add(i);
                }
            }
        }

        System.out.println(" \n Traversing BFS");
        graph.getVertices().forEach(vertex -> graph.getVisited()[vertex]=false);
        queue = new LinkedList<>();
        queue.add(0);
        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (!graph.getVisited()[i]) {
                bfsTraversal(queue);
            }
        }


    }

    private boolean detectCycleForUndirectedWithDFS(Integer node, Integer parent) {
        System.out.println(node + " - " + parent);

        graph.getVisited()[node] =true;
        boolean ans = false;
        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            int connect = graph.getMatrix()[node][i];
            if (connect != -1) {
                if (graph.getVisited()[i] && i != parent) {
                    return true;
                }
                if (!graph.getVisited()[i]) {
                    ans = detectCycleForUndirectedWithDFS(i, node);
                }

            }
            if (ans) {
                return true;
            }
        }
        return ans;

    }


    private boolean detectCycleForDirectedWithDFS(Integer node, List<Integer> recCallStack) {

        graph.getVisited()[node] = true;
        recCallStack.add(node);
        boolean ans = false;
        for (int neighbourId = 0; neighbourId < graph.getNumberOfNode(); neighbourId++) {
            int connect = graph.getMatrix()[node][neighbourId];
            if (connect != -1) {
                if (graph.getVisited()[neighbourId] && recCallStack.contains(neighbourId)) {
                    return true;
                }
                if (!graph.getVisited()[neighbourId]) {
                    ans = detectCycleForUndirectedWithDFS(neighbourId, node);
                }

            }
            if (ans) {
                return true;
            }
        }
        recCallStack.remove(node);
        return ans;

    }


    private boolean detectCycleForUndirectedGraphWithBFS(Integer node) {


        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Integer curr_node = queue.poll();
            graph.getVisited()[curr_node]=true;
            System.out.println("parent - " + curr_node);
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (graph.getMatrix()[curr_node][i] != -1) {
                    System.out.println(i);
                    if (graph.getVisited()[i] && i != curr_node) {
                        return true;
                    } else if (!graph.getVisited()[i]) {
                        queue.add(i);
                    }
                }
            }
        }

        return false;

    }


    private boolean detectCycleForDirectedGraphWithBFS(Integer node) {

        System.out.println("Calling detectCycleForDirectedGraphWithBFS");


        Queue<Integer> queue = new LinkedList<>();

        List<Integer> recCallStack = new ArrayList<>();

        queue.add(node);

        while (!queue.isEmpty()) {
            Integer curr_node = queue.poll();
            graph.getVisited()[curr_node] = true;
            recCallStack.add(curr_node);

            System.out.println("parent - " + curr_node);
            for (int i = 0; i < graph.getNumberOfNode(); i++) {
                if (graph.getMatrix()[curr_node][i] != -1) {
                    System.out.println(i);
                    if (graph.getVisited()[i] && recCallStack.contains(i)) {
                        return true;
                    } else if (!graph.getVisited()[i]) {
                        queue.add(i);
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
                for (Integer node : getAllNodes()) {
                    if (!graph.getVisited()[node]) {
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
                getAllNodes().forEach(node -> graph.getVisited()[node]=false);
                for (Integer node : getAllNodes()) {
                    if (!graph.getVisited()[node]) {
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
                for (Integer node : getAllNodes()) {
                    if (!graph.getVisited()[node]) {
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

                getAllNodes().forEach(node -> graph.getVisited()[node] = false);
                for (Integer node : getAllNodes()) {
                    if (!graph.getVisited()[node]) {
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

    private void implementDFSTopologicalSortingUtil(Integer node, Stack<Integer> stack) {

        graph.getVisited()[node]=true;

        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (graph.getMatrix()[node][i] != -1) {
                if (!graph.getVisited()[node]) {
                    implementDFSTopologicalSortingUtil(i, stack);
                }
            }
        }
        stack.push(node);

    }

    @Override
    public void implementDFSTopologicalSorting() {
        Stack<Integer> stack = new Stack<>();

        for (Integer node : getAllNodes()) {
            if (!graph.getVisited()[node]) {
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

        for(Integer vertex : getAllNodes()){
            for(int neighbour_index =0; neighbour_index< graph.getNumberOfNode() ; neighbour_index++ ){
                if(graph.getMatrix()[vertex][neighbour_index] != -1)
                    in_degree[neighbour_index]+=1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i =0 ; i< graph.getNumberOfNode() ; i++){
            if(in_degree[i]==0){
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        int count =0;
        while (!queue.isEmpty()){

            Integer node = queue.poll();
            result.add(node);

            for(int neighbour_index =0; neighbour_index< graph.getNumberOfNode() ; neighbour_index++ ){
                if(graph.getMatrix()[node][neighbour_index] != -1){
                    if(--in_degree[neighbour_index] ==0) {
                        queue.add(neighbour_index);
                    }
                }
            }
            count++;
        }

        if(count == graph.getNumberOfNode()){
            for (Integer node : result){
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
    public boolean isTopologicalSortingValid(int[] sorting) {
        return false;
    }

    @Override
    public void printPrimMST() {

        Comparator<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> comparator = (edge1, edge2) -> edge1.getCost() - edge2.getCost();
        PriorityQueue<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> priorityQueue = new PriorityQueue<>(comparator);

//         pick start node
        priorityQueue.add(new com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge<>(0, 0, 0));

        List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> ansEdges = new ArrayList<>();

        while (!priorityQueue.isEmpty()) {
            com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge = priorityQueue.poll();

            if (graph.getVisited()[edge.getEnd()]) {
                continue;
            }
            graph.getVisited()[edge.getEnd()]=true;
            ansEdges.add(edge);

            for (Integer neighbour : getAllAdjacent(edge.getEnd())) {
                priorityQueue.add(getEdgeBetween(edge.getEnd(), neighbour));
            }
        }

        for (com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge : ansEdges) {
            System.out.println(edge);
        }


    }

    Integer find(Integer node, HashMap<Integer, Integer> parent) {
        if (node == parent.get(node)) {
            return parent.get(node);
        }
        return find(parent.get(node), parent);
    }

    void union(Integer src, Integer dst, HashMap<Integer, Integer> parent, int[] rank) {
        Integer srcRoot = find(src, parent);
        Integer dstRoot = find(dst, parent);

        if (rank[srcRoot] > rank[dstRoot]) {
            parent.put(dstRoot, srcRoot);
        } else if (rank[srcRoot] < rank[dstRoot]) {
            parent.put(srcRoot, dstRoot);
        } else {
            parent.put(srcRoot, dstRoot);
            rank[srcRoot]++;
        }
    }


    @Override
    public List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> printKrushkalMST() {

        List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> edges = getAllEdges();
        edges.sort((e1, e2) -> e1.getCost() - e2.getCost());


        List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> result = new ArrayList<>();

        int[] rank = new int[graph.getNumberOfNode()];

        HashMap<Integer, Integer> parent = new HashMap<>();

        for (com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge : edges) {
            parent.put(edge.getStart(), edge.getStart());
            parent.put(edge.getEnd(), edge.getEnd());
        }


        int e = 0;
        int i = 0;
        while (e < graph.getNumberOfNode() - 1) {
            com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge = edges.get(i);
            Integer srcRoot = find(edge.getStart(), parent);
            Integer dstRoot = find(edge.getEnd(), parent);

            if (srcRoot != dstRoot) {
                result.add(edge);
                union(edge.getStart(), edge.getEnd(), parent, rank);
                e++;
            }
            i++;
        }

        for (com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge : result) {
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


    public List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> printKrushkalMST_M2() {

        PriorityQueue<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> edges = new PriorityQueue<>((e1, e2) -> e1.getCost() - e2.getCost());
        edges.addAll(getAllEdges());

        List<com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge> result = new ArrayList<>();

        int[] rank = new int[graph.getNumberOfNode()];

        HashMap<Integer, Integer> parent = new HashMap<>();

        for (com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge : edges) {
            parent.put(edge.getStart(), edge.getStart());
            parent.put(edge.getEnd(), edge.getEnd());
        }


        int e = 0;
        while (e < graph.getNumberOfNode() - 1) {
            com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge = edges.poll();
            Integer srcRoot = find(edge.getStart(), parent);
            Integer dstRoot = find(edge.getEnd(), parent);

            if (srcRoot != dstRoot) {
                result.add(edge);
                union(edge.getStart(), edge.getEnd(), parent, rank);
                e++;
            }

        }

        for (com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge edge : result) {
            System.out.println(edge);
        }


        return result;


    }




    private void dfsTraversal(int node) {
        graph.getVisited()[node]= true;
        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (graph.getMatrix()[node][i] != -1 && !graph.getVisited()[node]) {
                dfsTraversal(i);
            }
        }
        System.out.print(node);

    }

    private void bfsTraversal(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Integer node = queue.poll();

        System.out.print(node);
        graph.getVisited()[node]= true;

        for (int i = 0; i < graph.getNumberOfNode(); i++) {
            if (graph.getMatrix()[node][i] != -1 && !graph.getVisited()[node]) {
                queue.add(i);
            }
        }

        bfsTraversal(queue);
    }


    private com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix.Edge getEdgeBetween(int start, int end) {

        List<Edge> edges = getAllEdges().stream().filter(edge -> edge.getStart()==(start) && edge.getEnd()==(end)).collect(Collectors.toList());
        if (!edges.isEmpty()) {
            return edges.get(0);
        }
        return null;
    }

    @Override
    public boolean hasEdge(int src, int end) {
        return graph.getMatrix()[src][end] != -1 || graph.getMatrix()[end][src] != -1;
    }


    //    get all neighbours
    public List<Integer> getAllAdjacent(Integer node) {
        List<Integer> neighbours = new ArrayList<>();

        for (int j = 0; j < graph.getMatrix()[node].length; j++) {
            if (graph.getMatrix()[node][j] != -1) {
                neighbours.add(j);
            }
        }


        return neighbours;
    }


}
