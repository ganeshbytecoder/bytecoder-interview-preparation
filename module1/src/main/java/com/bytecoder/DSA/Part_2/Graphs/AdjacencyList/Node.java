package com.bytecoder.DSA.Part_2.Graphs.AdjacencyList;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
class Node<T> {

    T data;

    boolean visited;

    @ToString.Exclude
    private Map<Node<T>, Integer> neighbors = new HashMap<>();

    Node( T data) {
        this.data = data;
        this.visited = false;
    }

    public void addNeighbor(Node<T> node, int cost) {
        neighbors.put(node, cost);
    }


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", visited=" + visited +
                '}';
    }
}
