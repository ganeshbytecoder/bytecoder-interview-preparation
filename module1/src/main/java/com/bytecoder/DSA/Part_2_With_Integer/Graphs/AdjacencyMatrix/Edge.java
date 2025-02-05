package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyMatrix;

import com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix.Node;
import lombok.Data;

@Data
public class Edge<T> {
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
                "start=" + start.getData() +
                ", end=" + end.getData() +
                ", cost=" + cost +
                '}';
    }
}
