package com.bytecoder.DSA.Part_2_With_Integer.ArrayLinkedList;

import lombok.Data;

@Data
public class Node<T extends Comparable<T>> {

    private T data;

    private Node<T> next;

}
