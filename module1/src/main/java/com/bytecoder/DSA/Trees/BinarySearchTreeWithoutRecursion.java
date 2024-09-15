package com.bytecoder.DSA.Trees;

import java.util.Collections;
import java.util.List;


/***
 * Don't use recursion use only queue, stack and array to solve tree problems
 * @param <T>
 */
public class BinarySearchTreeWithoutRecursion<T extends Comparable<T>> implements Tree<T> {



    private Node<T> root;

    public BinarySearchTreeWithoutRecursion() {
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }


    public Node<T> insertNode_v2(T data, Node<T> node) {

        if (node == null) {
            return new Node<>(data);
        }
        if (node.getData().compareTo(data) > 0) {
            node.setLeftChild(insertNode_v2(data, node.getLeftChild()));
        } else {
            node.setRightChild(insertNode_v2(data, node.getRightChild()));
        }
        return node;
    }


    @Override
    public Tree<T> insert(T data) {
        return null;
    }

    @Override
    public void delete(T data) {


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


}
