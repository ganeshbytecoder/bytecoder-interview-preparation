package com.bytecoder.DSA.Part_2.Trees;

import java.util.List;

public interface Tree<T extends Comparable<T>> {

    Node<T> getRoot();

    boolean isEmpty();

    Tree<T> insert(T data);


    void traverse(TraversalType traversalType);


    int getMax();


    int getMin();


    int getHeight();


    int getLevel(T data);


    List<Node<T>> getNodesAtLevel(int level);


    boolean searchData(T data);


    void delete(T data);


}
