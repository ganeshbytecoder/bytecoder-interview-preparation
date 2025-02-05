package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Graph<T> {

    private  boolean directed;

//    Note for weighted graph is will be hashMap
    private  List<com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node<T>> vertices = new ArrayList<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }
}
