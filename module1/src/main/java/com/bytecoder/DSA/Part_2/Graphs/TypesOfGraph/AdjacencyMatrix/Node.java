package com.bytecoder.DSA.Part_2.Graphs.TypesOfGraph.AdjacencyMatrix;

import lombok.Data;

@Data
class Node {

    int id;

    int data;

    boolean visited;

    public Node(int index) {
        this.id = index;
        this.visited = false;
    }

    public Node(int index, int data) {
        this.id = index;
        this.data = data;
        this.visited = false;
    }

}
