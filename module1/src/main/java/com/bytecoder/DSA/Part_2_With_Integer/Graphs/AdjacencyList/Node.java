package com.bytecoder.DSA.Part_2_With_Integer.Graphs.AdjacencyList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Node {
    public int data;
    public boolean visited;

//    weighted graph
    @ToString.Exclude
    public Map<Node, Integer> neighbors;

    public Node(int data) {
        this.data = data;
        this.visited = false;
        this.neighbors = new HashMap<>();
    }

    public void addNeighbor(Node node, int weight) {
        neighbors.put(node, weight);
    }

    public void removeNeighbor(Node node) {
        neighbors.remove(node);
    }

    public Integer getEdgeWeight(Node node) {
        return neighbors.get(node);
    }

    public boolean hasNeighbor(Node node) {
        return neighbors.containsKey(node);
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}
