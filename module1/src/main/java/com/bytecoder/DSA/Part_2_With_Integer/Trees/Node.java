package com.bytecoder.DSA.Part_2_With_Integer.Trees;

import lombok.Data;

@Data
public class Node {
    private int data;
    private Node leftChild;
    private Node rightChild;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return Integer.toString(data);
    }

}