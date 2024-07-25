package com.bytecoder.DSA.Trees;

import java.util.Collections;
import java.util.List;

public class NaryTree <T extends Comparable<T>> implements Tree<T> {
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
    public Tree<T> insert_m2(T data) {
        return null;
    }

    @Override
    public void traverse(TraversalType traversalType) {

    }

    @Override
    public void traverse_m2(TraversalType traversalType) {

    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public int getMax_m2() {
        return 0;
    }

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getMin_m2() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getHeight_m2() {
        return 0;
    }

    @Override
    public int getLevel(T data) {
        return 0;
    }

    @Override
    public int getLevel_m2(T data) {
        return 0;
    }

    @Override
    public List<Node<T>> getNodesAtLevel(int level) {
        return Collections.emptyList();
    }

    @Override
    public List<Node<T>> getNodesAtLevel_m2(int level) {
        return Collections.emptyList();
    }

    @Override
    public boolean searchData(T data) {
        return false;
    }

    @Override
    public boolean searchData_m2(T data) {
        return false;
    }

    @Override
    public void delete(T data) {

    }

    @Override
    public void delete_m2(T data) {

    }
}
