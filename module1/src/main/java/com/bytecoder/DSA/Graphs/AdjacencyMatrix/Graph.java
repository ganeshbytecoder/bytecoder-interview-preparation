package com.bytecoder.DSA.Graphs.AdjacencyMatrix;


import java.util.List;

public interface Graph <T> {


//    void addNode(Node<T> node);

    List<Node<T>> getAllNodes();

    void addEdge(Edge edge);

    List<Edge> getAllEdges();

//    boolean hasEdge(Node src, Node end);

//    Node<T> getNodeByName(T data);


    void printGraph();

    void dfs();
}
