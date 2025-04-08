package com.bytecoder.DSA.Part_2_With_Integer.Graphs.TypesOfGraph.AdjacencyMatrix;

import lombok.Data;

@Data
public class Edge {
    Node start;

    Node end;

    int cost;

    Edge(Node start, Node end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;

    }


    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start.getData() +
                ", end=" + end.getData() +
                ", cost=" + cost +
                '}';
    }
}
