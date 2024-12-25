package com.bytecoder.DSA.Part_2.Graphs.AdjacencyList;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Graph<T> {

    private  boolean directed;

//    Note for weighted graph is will be hashMap
    private  List<Node<T>> vertices = new ArrayList<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }
}
