package com.bytecoder.DSA.Part_2.Trees.BST;

import com.bytecoder.DSA.Part_2.Trees.Node;
import com.bytecoder.DSA.Part_2.Trees.TraversalType;
import com.bytecoder.DSA.Part_2.Trees.Tree;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public BinarySearchTree() {
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

//        find the node with this data
//        find the right/left most leaf
//        replace the value and delete leaf


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
//        if (isEmpty()) {
//            return -1;
//        }
//        Node<T> curr = root;
//        while (curr.getRightChild() != null) {
//            curr = curr.getRightChild();
//        }
//        return (int) curr.getData();
        return (int) getMax(getRoot());
    }


    @Override
    public int getMin() {
//        if (isEmpty()) {
//            return -1;
//        }
//        Node<T> curr = root;
//        while (curr.getLeftChild() != null) {
//            curr = curr.getLeftChild();
//        }
//
//        return (int) curr.getData();
        return (int) getMin(getRoot());
    }

    private int getHeight(Node<T> node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
    }

    @Override
    public int getHeight() {
        return getHeight(getRoot());
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

    private int max_value = Integer.MIN_VALUE;

    private int getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getLeftChild());
        }
        node.getData();
        return max_value;
    }

    private int min_value = Integer.MAX_VALUE;

    private int getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        node.getData();
        return min_value;
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
