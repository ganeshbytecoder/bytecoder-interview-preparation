package com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix;

import lombok.Data;

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
