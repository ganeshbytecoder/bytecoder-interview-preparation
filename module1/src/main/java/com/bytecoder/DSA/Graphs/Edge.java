package com.bytecoder.DSA.Graphs;

import lombok.Data;
import lombok.ToString;

@Data
class Edge<T> {
    Node<T> start;

    Node<T> end;

    int cost;


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
