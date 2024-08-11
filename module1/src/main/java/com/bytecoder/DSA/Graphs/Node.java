package com.bytecoder.DSA.Graphs;

import lombok.Data;

@Data
class Node<T> {
    int id;

    T node;

    boolean visited;

    Node(int index, T node) {
        this.id = index;
        this.node = node;
        this.visited = false;
    }

}
