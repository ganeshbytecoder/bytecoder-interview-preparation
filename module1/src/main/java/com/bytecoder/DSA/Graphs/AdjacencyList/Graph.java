package com.bytecoder.DSA.Graphs.AdjacencyList;


import java.util.List;

public interface Graph<T> {


    List<Node<T>> getAllNodes();

    void addEdge(Edge edge);

    List<Edge> getAllEdges();

//    boolean hasEdge(Node src, Node end);


    void printGraph();

    void dfs();

    void bfs();
}
