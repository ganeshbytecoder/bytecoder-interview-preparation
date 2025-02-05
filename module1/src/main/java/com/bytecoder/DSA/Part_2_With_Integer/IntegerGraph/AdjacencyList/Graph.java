package com.bytecoder.DSA.Part_2_With_Integer.IntegerGraph.AdjacencyList;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Graph {

    private  boolean directed;
// 0 to nodes -> as integers

//    Note for unweighted graph is will be List<List<Integer>>
    private  List<HashMap<Integer, Integer>> vertices = new ArrayList<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }
}
