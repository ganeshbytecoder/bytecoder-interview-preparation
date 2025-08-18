package com.bytecoder.DSA.Part_2.Trees;

import lombok.Data;

@Data
public class Node {
     public int data;
     public Node leftChild;
     public Node rightChild;


    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return Integer.toString(data);
    }

}