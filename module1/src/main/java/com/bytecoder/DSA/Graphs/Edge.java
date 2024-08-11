package com.bytecoder.DSA.Graphs;

import lombok.Data;

@Data
class Edge<T> {
    Node<T> start;

    Node<T> end;

    int cost;

    boolean directed;

    Edge(Node<T> start, Node<T> end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.directed = false;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
