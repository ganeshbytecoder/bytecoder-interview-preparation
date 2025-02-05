package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import com.bytecoder.DSA.Part_2.Graphs.AdjacencyList.Node;
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
