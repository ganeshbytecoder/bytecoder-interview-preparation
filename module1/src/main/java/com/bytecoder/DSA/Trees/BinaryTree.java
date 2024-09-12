package com.bytecoder.DSA.Trees;

import java.util.*;


public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public BinaryTree() {
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }


    @Override
    public Tree<T> insert(T data) {
        if (this.isEmpty()) {
            root = new Node<T>(data);
            return this;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();

            if (temp.getLeftChild() == null) {
                temp.setLeftChild(new Node<>(data));
                break;
            } else {
                queue.add(temp.getLeftChild());
            }

            if (temp.getRightChild() == null) {
                temp.setRightChild(new Node<>(data));
                break;
            } else {
                queue.add(temp.getRightChild());
            }
        }
        return this;
    }




    @Override
    public void traverse(TraversalType traversalType) {
        System.out.println("\n Solving using recursion");
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



    @Override
    public void delete(T data) {


    }


    private int max_value = Integer.MIN_VALUE;

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



    @Override
    public int getMax() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return get_max(root);
    }


    @Override
    public int getMin() {
        if (isEmpty()) {
            return -1;
        }

        return -1;
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
        System.out.print(String.format(" %s", node.getData()));
        traversePreOrder(node.getLeftChild());
        traversePreOrder(node.getRightChild());
    }

    private void traversePostOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        traversePostOrder(node.getLeftChild());
        traversePostOrder(node.getRightChild());
        System.out.print(String.format(" %s", node.getData()));

    }


    private void traverseInOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeftChild());
        System.out.print(String.format(" %s", node.getData()));
        traverseInOrder(node.getRightChild());
    }

    private void traverseLevelOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        Node<T> curr = node;
        List<List<Integer>> lists = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()) {
            int h = queue.size();
            List<Integer> tem = new ArrayList<>();
            while (h > 0) {
                Node<T> temp = queue.poll();
                tem.add((Integer) temp.getData());
                if (temp.getLeftChild() != null) {
                    queue.add(temp.getLeftChild());
                }

                if (temp.getRightChild() != null) {
                    queue.add(temp.getRightChild());
                }
                h--;
            }
            lists.add(tem);

        }
        System.out.println(lists);
    }

}
