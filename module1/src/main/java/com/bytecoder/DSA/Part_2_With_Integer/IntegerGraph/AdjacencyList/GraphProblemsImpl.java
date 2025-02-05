package com.bytecoder.DSA.Part_2_With_Integer.IntegerGraph.AdjacencyList;

import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Edge;
import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Graph;
import com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.GraphProblems;

import java.util.*;

class GraphProblemsImpl implements GraphProblems {


    private final com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Graph graph;

    public GraphProblemsImpl(Graph graph) {
        this.graph = graph;
    }


    @Override
    public void addNode(int node) {
//        insert at index node since index is node
        graph.getVertices().add(node, new HashMap<>());
    }

    @Override
    public void removeNode(int node) {
//        graph.getVertices().remove(node);
//
//        for (List<Integer> node1 : graph.getVertices()) {
//            if (node1.get(node) != null) {
//                node1.remove( Integer.valueOf(node));
//            }
//        }
    }

    @Override
    public List<HashMap<Integer,Integer>> getAllNodes() {
        return graph.getVertices();
    }


    @Override
    public void addEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Edge edge) {
//        if (graph.getVertices().contains(edge.getStart()) && graph.getVertices().contains(edge.getEnd())) {
//            edge.getStart()(edge.getEnd(), edge.getCost());
//            if (!graph.isDirected()) {
//                edge.getEnd().addNeighbor(edge.getStart(), edge.getCost());
//            }
//        }
    }

    @Override
    public void removeEdge(com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyList.Edge edge) {

    }

    @Override
    public List<Edge> getAllEdges() {
//        List<Edge> edges = new ArrayList<>();
//
//        for (List<Integer> neighbors : graph.getVertices()) {
//            for (Map.Entry<Node<T>, Integer> neighbor : node.getNeighbors().entrySet()) {
//                edges.add(new Edge<>(node, neighbor.getKey(), neighbor.getValue()));
//            }
//        }
//
        return null;
    }

    @Override
    public boolean hasEdge(int src, int end) {
        return graph.getVertices().get(src).get(end) != null;
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
        for (int i =0; i< graph.getVertices().size(); i++) {
            System.out.println(i + "   ");

            for (Map.Entry<Integer, Integer> neighbor : graph.getVertices().get(i).entrySet()) {
                System.out.println(i + " -> " + neighbor.getKey() + " " + neighbor.getValue());
            }
            System.out.println();
        }
    }


    @Override
    public void implementDFSTopologicalSorting() {

    }

    @Override
    public void implementBFSTopologicalSorting() {

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

    }

    @Override
    public void printKrushkalMST() {

    }


    @Override
    public void findShortestPathUsingDijkstra() {

    }

    @Override
    public void findShortestPathUsingBellmanFord() {

    }


}
