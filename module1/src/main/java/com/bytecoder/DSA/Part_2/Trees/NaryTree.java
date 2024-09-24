package com.bytecoder.DSA.Part_2.Trees;

import java.util.Collections;
import java.util.List;

public class NaryTree<T extends Comparable<T>> implements Tree<T> {
    @Override
    public Node<T> getRoot() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Tree<T> insert(T data) {
        return null;
    }

    @Override
    public void traverse(TraversalType traversalType) {

    }


    @Override
    public int getMax() {
        return 0;
    }


    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }


    @Override
    public int getLevel(T data) {
        return 0;
    }


    @Override
    public List<Node<T>> getNodesAtLevel(int level) {
        return Collections.emptyList();
    }


    @Override
    public boolean searchData(T data) {
        return false;
    }


    @Override
    public void delete(T data) {

    }

}
