package com.bytecoder.DSA.Trees;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public BinaryTree() {
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }


    @Override
    public Tree<T> insert(T data) {
        if (this.isEmpty()) {
            root = new Node(data);
            return this;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();
            if (temp.getLeftChild() == null) {
                temp.setLeftChild(new Node<>(data));
            } else {
                queue.add(temp.getLeftChild());
            }

            if (temp.getRightChild() == null) {
                temp.setRightChild(new Node<>(data));
            } else {
                queue.add(temp.getRightChild());
            }
        }
        return this;
    }

    @Override
    public Tree<T> insert_m2(T data) {
        return null;
    }


    @Override
    public void traverse(TraversalType traversalType) {
        if ((traversalType.equals(TraversalType.IN_ORDER))) {
            traverseInOrder(this.root);
        }
        if (traversalType.equals(TraversalType.POST_ORDER)) {
            traversePostOrder(this.root);

        }
        if (traversalType.equals(TraversalType.PRE_ORDER)) {
            traversePreOrder(this.root);

        }
        if (traversalType.equals(TraversalType.LEVEL_ORDER)) {
            traverseLevelOrder(this.root);

        }
    }

    @Override
    public void traverse_m2(TraversalType traversalType) {

    }


    @Override
    public void delete(T data) {


    }

    @Override
    public void delete_m2(T data) {

    }


    private int max_value= Integer.MIN_VALUE;

    private int get_max(Node node) {
        if (node == null) {
            return max_value;
        }
        if (node.getData().compareTo(max_value) > 0) {
            max_value = (int) node.getData();
        }
        get_max(node.getLeftChild());
        get_max(node.getRightChild());
        return max_value;
    }

    private int get_max_m2(Node node) {

        return max_value;
    }


    @Override
    public int getMax() {
        if (isEmpty()) {
            return max_value;
        }
        int m1 = get_max(root);
        int m2 = get_max_m2(root);
        if (m1-m2 != 0) {
            throw new RuntimeException("Both M1 and M2 results are not same");
        }
        return max_value;
    }

    @Override
    public int getMax_m2() {
        return 0;
    }


    @Override
    public int getMin() {
        if (isEmpty()) {
            return -1;
        }

        return -1;
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
    public boolean isEmpty() {
        return root == null;
    }


    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }


    private void traversePreOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        System.out.println(node.getData());
        traverseInOrder(node.getLeftChild());
        traverseInOrder(node.getRightChild());
    }

    private void traversePostOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeftChild());
        traverseInOrder(node.getRightChild());
        System.out.println(node.getData());

    }


    private void traverseInOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeftChild());
        System.out.println(node.getData());
        traverseInOrder(node.getRightChild());
    }

    private void traverseLevelOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        Node<T> curr = node;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();
            System.out.println(temp.getData());

            if (temp.getLeftChild() != null) {
                queue.add(temp.getLeftChild());
            }

            if (temp.getRightChild() != null) {
                queue.add(temp.getRightChild());
            }

        }
    }

}
