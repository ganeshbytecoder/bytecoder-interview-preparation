package com.bytecoder.DSA.Part_4.Graphs.AdjacencyList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
// we can call this as vertex as well
class Node<T> {

    T data;

    boolean visited;

//    we can call it adjacencyList as well
    @ToString.Exclude
    private Map<Node<T>, Integer> neighbors = new HashMap<>();

    public Node(int index, T data) {
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
