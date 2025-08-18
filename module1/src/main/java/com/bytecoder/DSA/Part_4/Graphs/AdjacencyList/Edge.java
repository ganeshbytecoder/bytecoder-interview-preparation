package com.bytecoder.DSA.Part_4.Graphs.AdjacencyList;

import lombok.Data;

@Data
public class Edge<T> {
    private Node<T> start;

    private Node<T> end;

    private int cost;


    Edge(Node<T> start, Node<T> end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;

    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                ", start=" + start.getData() +
                ", end=" + end.getData() +
                '}';
    }
}
