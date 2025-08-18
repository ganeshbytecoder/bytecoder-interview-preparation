package com.bytecoder.DSA.Part_4.Graphs.AdjacencyMatrix;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Graph<T> {

    private final List<Node<T>> vertices = new ArrayList<>();

    private final int[][] matrix;

    private final int numberOfNode;

    private final boolean directed;

    public Graph(int totalNodes, boolean directed){
        this.numberOfNode = totalNodes;
        this.directed = directed;
        this.matrix = new int[numberOfNode][numberOfNode];
    }

}
