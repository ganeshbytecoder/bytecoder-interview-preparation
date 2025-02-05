package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import lombok.Data;

@Data
public class Edge {
    private Node source;
    private Node destination;
    private int weight;

    public Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return source.getData() + " -> " + destination.getData() + " (" + weight + ")";
    }
}
