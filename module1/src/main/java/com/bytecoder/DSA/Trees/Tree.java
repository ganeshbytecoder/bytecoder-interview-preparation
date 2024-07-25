package com.bytecoder.DSA.Trees;

import java.util.List;

public interface Tree<T extends Comparable<T>> {

    Node<T> getRoot();
    boolean isEmpty();


    Tree<T> insert(T data);


    //    without recursion
    Tree<T> insert_m2(T data);

    void traverse(TraversalType traversalType);

    //    without recursion
    void traverse_m2(TraversalType traversalType);

    int getMax();

    //    without recursion
    int getMax_m2();

    int getMin();

    //    without recursion
    int getMin_m2();

    int getHeight();

    //    without recursion
    int getHeight_m2();

    int getLevel(T data);

    //    without recursion
    int getLevel_m2(T data);

    List<Node<T>> getNodesAtLevel(int level);

    //    without recursion
    List<Node<T>> getNodesAtLevel_m2(int level);

    boolean searchData(T data);

    //    without recursion
    boolean searchData_m2(T data);


    void delete(T data);

    //    without recursion
    void delete_m2(T data);

}
