package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Graph;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.GraphProblems;
import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node;

import java.util.*;
import java.util.stream.Collectors;

class GraphProblemsImpl<T> implements GraphProblems<T> {


    private final com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Graph<T> graph;

    public GraphProblemsImpl(Graph<T> graph) {
        this.graph = graph;
    }


    @Override
    public void addNode(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node) {
        graph.getVertices().add(node);
    }

    @Override
    public void removeNode(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node) {
        graph.getVertices().remove(node);

        for (com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node1 : graph.getVertices()) {
            if (node1.getNeighbors().get(node) != null) {
                node1.getNeighbors().remove(node);
            }
        }
    }

    @Override
    public List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>> getAllNodes() {
        return graph.getVertices();
    }


    @Override
    public void addEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T> edge) {
        if (graph.getVertices().contains(edge.getStart()) && graph.getVertices().contains(edge.getEnd())) {
            edge.getStart().addNeighbor(edge.getEnd(), edge.getCost());
            if (!graph.isDirected()) {
                edge.getEnd().addNeighbor(edge.getStart(), edge.getCost());
            }
        }
    }

    @Override
    public void removeEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T> edge) {

    }

    @Override
    public List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T>> getAllEdges() {
        List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T>> edges = new ArrayList<>();

        for (com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node : graph.getVertices()) {
            for (Map.Entry<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>, Integer> neighbor : node.getNeighbors().entrySet()) {
                edges.add(new com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<>(node, neighbor.getKey(), neighbor.getValue()));
            }
        }

        return edges;
    }

    @Override
    public boolean hasEdge(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node src, com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node end) {
        return src.getNeighbors().get(end) != null;
    }

    @Override
    public void dfs() {

    }

    @Override
    public void bfs() {

    }

    @Override
    public boolean isCyclic() {
        return false;
    }

    @Override
    public void printGraph() {
        for (com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node : getAllNodes()) {
            System.out.println(node.data.toString() + "   ");

            for (Map.Entry<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>, Integer> neighbor : node.getNeighbors().entrySet()) {
                System.out.println(node.data + " -> " + neighbor.getKey().getData() + " " + neighbor.getValue());
            }
            System.out.println();
        }
    }

    private void topologicalSortUtil(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node, Stack<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>> stack){
        node.setVisited(true);
        for(Map.Entry<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>, Integer> entry : node.getNeighbors().entrySet()){
            if(!entry.getKey().isVisited()){
              topologicalSortUtil(entry.getKey(), stack);
            }
        }
        stack.add(node);
    }

    @Override
    public void implementDFSTopologicalSorting() {

        Stack<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>> stack= new Stack<>();

        for(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> node : getAllNodes()){
            if(!node.isVisited()){
                topologicalSortUtil(node, stack);
            }
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }

    @Override
    public void implementBFSTopologicalSorting() {

    }

    @Override
    public void allTopologicalSorting() {

    }

    @Override
    public boolean isTopologicalSortingValid(com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>[] sorting) {
        return false;
    }

    @Override
    public void printPrimMST() {

        List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>> vertices = getAllNodes();

        PriorityQueue<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T>> queue =  new PriorityQueue<>((e1, e2)-> e1.getCost()-e2.getCost());

        queue.add(new com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<>(vertices.get(0), vertices.get(0), 0));
        int minCost = 0;
        while(!queue.isEmpty()){
          com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Edge<T> edge = queue.poll();
          if(edge.getEnd().isVisited()){
              continue;
          }

          minCost+=edge.getCost();
           for( Map.Entry<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>, Integer> entry:  edge.getEnd().getNeighbors().entrySet()){
               if(!entry.getKey().isVisited()){
                   queue.add(new Edge<>(edge.getEnd(), entry.getKey(), entry.getValue()));
               }

           }
        }


    }

    @Override
    public void printKrushkalMST() {

    }


    public com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T> getNodeByData(T data) {

        List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>> nodes = graph.getVertices().stream().filter(node -> node.getData() == data).collect(Collectors.toList());
        if (nodes.size() != 1) {
            throw new RuntimeException("ID either does not exists or duplicate");
        }
        return nodes.get(0);
    }

    @Override
    public void findShortestPathUsingDijkstra() {

    }

    @Override
    public void findShortestPathUsingBellmanFord() {

    }


}
