package com.bytecoder.DSA.Trees;

import java.util.*;


public class BinaryTreeWithoutRecursion<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public BinaryTreeWithoutRecursion() {
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }



    private void insert(Node<T> node, T data) {
        if (this.isEmpty()) {
            root = new Node<>(data);
            return;
        }
        if (node == null) {
            return;
        }
        if (node.getLeftChild() == null) {
            node.setLeftChild(new Node<>(data));
            return;
        }
        if (node.getRightChild() == null) {
            node.setRightChild(new Node<>(data));
            return;
        }

        insert(node.getLeftChild(), data);
        insert(node.getRightChild(), data);
    }


//    without recursion
    @Override
    public Tree<T> insert(T data) {
        insert(root, data);
        return this;
    }



    @Override
    public void traverse(TraversalType traversalType) {

        System.out.println("\n Solving using without recursion");
        System.out.println(traversalType);
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

    private void traverseLevelOrder(Node<T> root) {

        System.out.println("Not implemented");
    }

    private void traversePreOrder(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node<T> temp = stack.pop();
            System.out.print(String.format(" %d", temp.getData()));
              if (temp.getRightChild() != null) {
                stack.add(temp.getRightChild());
            }
            if (temp.getLeftChild() != null) {
                stack.add(temp.getLeftChild());
            }

        }
    }

    private void traversePostOrder(Node<T> root) {
    }

    private void traverseInOrder(Node<T> root) {

    }




    @Override
    public void delete(T data) {

    }


    private int max_value = Integer.MIN_VALUE;

    private int get_max(Node node) {
        Node<T> minNode  = node;
        if (node== null){
            return max_value;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node<T> temp = queue.poll();
        if (temp.getData().compareTo(minNode.getData()) > 0) {
                max_value = (Integer) temp.getData();
            }
            if(temp.getLeftChild()!=null){
                queue.add(temp.getLeftChild());
            }
            if(temp.getRightChild() != null){
                queue.add(temp.getRightChild());
            }
        }

        return max_value;
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
