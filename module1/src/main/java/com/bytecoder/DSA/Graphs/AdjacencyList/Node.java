package com.bytecoder.DSA.Graphs.AdjacencyList;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
class Node<T> {
    int id;

    T data;

    boolean visited;

    @ToString.Exclude
    private Map<Node<T>, Integer> neighbors = new HashMap<>();

    Node(int index, T data) {
        this.id = index;
        this.data = data;
        this.visited = false;
    }

    public void addNeighbor(Node<T> node, int cost) {
        neighbors.put(node, cost);
    }


}
