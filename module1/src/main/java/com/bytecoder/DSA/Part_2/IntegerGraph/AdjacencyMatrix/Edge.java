package com.bytecoder.DSA.Part_2.IntegerGraph.AdjacencyMatrix;

import lombok.Data;

@Data
public class Edge<T> {
    int start;

    int end;

    int cost;

    Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;

    }


    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }
}
