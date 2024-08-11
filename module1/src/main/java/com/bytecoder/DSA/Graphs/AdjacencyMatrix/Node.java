package com.bytecoder.DSA.Graphs.AdjacencyMatrix;

import lombok.Data;

@Data
class Node<T> {
    int id;

    T data;

    boolean visited;

    Node(int index, T data) {
        this.id = index;
        this.data = data;
        this.visited = false;
    }

}
