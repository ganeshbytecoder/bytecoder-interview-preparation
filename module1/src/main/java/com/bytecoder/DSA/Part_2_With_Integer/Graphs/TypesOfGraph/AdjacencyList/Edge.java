package com.bytecoder.DSA.Part_2_With_Integer.Graphs.TypesOfGraph.AdjacencyList;

import lombok.Data;

@Data
public class Edge {
    public Node source;
    public Node destination;
    public int weight;

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
