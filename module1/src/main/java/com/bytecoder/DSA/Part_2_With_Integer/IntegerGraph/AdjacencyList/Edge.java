package com.bytecoder.DSA.Part_2_With_Integer.IntegerGraph.AdjacencyList;

import lombok.Data;

@Data
public class Edge {
    private int start;

    private int end;

    private int cost;


    Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;

    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
