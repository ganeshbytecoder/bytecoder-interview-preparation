package com.bytecoder.DSA.Part_2.Trees;

import lombok.Data;

@Data
public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return (String) data;
    }

}