package com.bytecoder.DSA.Trees;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public BinarySearchTree() {
    }

    public Node getRoot() {
        return root;
    }


    public void insertNode(T data, Node<T> node) {
        if (node.getData().compareTo(data) > 0) {
            if (node.getLeftChild() != null) {
                insertNode(data, node.getLeftChild());

            } else {
                node.setLeftChild(new Node<>(data));

            }
        } else {
            if (node.getRightChild() != null) {
                insertNode(data, node.getRightChild());
            } else {
                node.setRightChild(new Node<>(data));
            }
        }
    }

    @Override
    public Tree<T> insert(T data) {
        if (this.isEmpty()) {
            root = new Node(data);
        } else {
            insertNode(data, root);
        }
        return this;
    }


    @Override
    public void delete(T data) {


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
    public int getMax() {
        if (isEmpty()) {
            return -1;
        }
        Node<T> curr = root;
        while (curr.getRightChild() != null) {
            curr = curr.getRightChild();
        }

        return 1;
    }


    @Override
    public int getMin() {
        if (isEmpty()) {
            return -1;
        }
        Node<T> curr = root;
        while (curr.getLeftChild() != null) {
            curr = curr.getLeftChild();
        }
        return 1;
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
