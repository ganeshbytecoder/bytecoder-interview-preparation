package com.bytecoder.DSA.Part_1.ArrayLinkedList;

import lombok.Data;

@Data
public class Node<T extends Comparable<T>> {

    private T data;

    private Node<T> next;

}
