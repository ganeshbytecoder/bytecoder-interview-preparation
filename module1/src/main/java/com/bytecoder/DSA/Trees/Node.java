package com.bytecoder.DSA.Trees;

import lombok.Data;

@Data
public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T data) {
        this.data = data;
    }
}