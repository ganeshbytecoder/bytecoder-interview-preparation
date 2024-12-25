package com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Graph {

    private final List<Integer> vertices = new ArrayList<>();

    private final int[][] matrix;

    private final boolean[] visited;

    private final int numberOfNode;

    private final boolean directed;

    public Graph(int totalNodes, boolean directed){
        this.numberOfNode = totalNodes;
        this.directed = directed;
        this.visited = new boolean[totalNodes];
        this.matrix = new int[numberOfNode][numberOfNode];
    }

}
