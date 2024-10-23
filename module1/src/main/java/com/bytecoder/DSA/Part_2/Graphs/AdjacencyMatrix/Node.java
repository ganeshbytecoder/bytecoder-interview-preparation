package com.bytecoder.DSA.Part_2.Graphs.AdjacencyMatrix;

import lombok.Data;

@Data
class Node<T> {
    int id;

    T data;

    boolean visited;

    public Node(int index) {
        this.id = index;
        this.visited = false;
    }

    public Node(int index, T data) {
        this.id = index;
        this.data = data;
        this.visited = false;
    }

}
