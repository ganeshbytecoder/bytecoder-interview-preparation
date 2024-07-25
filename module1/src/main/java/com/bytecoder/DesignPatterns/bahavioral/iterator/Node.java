package com.bytecoder.DesignPatterns.bahavioral.iterator;

public class Node {

    int data;
    Node left, right, parent ;

    public Node(int data){
        this.data= data;
        left.parent=right.parent=this;
    }
}
