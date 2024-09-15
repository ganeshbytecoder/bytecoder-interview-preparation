package com.bytecoder.DSA.Graphs.AdjacencyList;


import java.util.List;

public interface Graph<T> {


    void addNode(Node node);


    void removeNode(Node<T> node);

    List<Node<T>> getAllNodes();

    void addEdge(Edge edge);

    void removeEdge(Edge edge);

    List<Edge> getAllEdges();

    boolean hasEdge(Node<T> src, Node<T> end);

    void dfs();

    void bfs();

    void printGraph();

}
