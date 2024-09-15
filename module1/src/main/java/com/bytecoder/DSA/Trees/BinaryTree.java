package com.bytecoder.DSA.Trees;

import java.util.ArrayList;
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
            return -1;
        }
        return get_max(root);
    }


    private int min_value = Integer.MAX_VALUE;

    private int get_min(Node node) {
        if (node == null) {
            return min_value;
        }
        if (node.getData().compareTo(min_value) > 0) {
            min_value = (int) node.getData();
        }
        get_max(node.getLeftChild());
        get_max(node.getRightChild());
        return min_value;
    }

    @Override
    public int getMin() {
        if (isEmpty()) {
            return -1;
        }

        return get_min(root);
    }


    private int getHeight(Node<T> node) {

        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    int level = Integer.MIN_VALUE;

    private int getLevel(Node<T> node, T data, int level) {
        if (node == null) {
            return -1;
        }
        if (node.getData().equals(data)) {
            this.level = level;
            System.out.println("level of tree " + this.level);
            return level;
        }
        getLevel(node.getLeftChild(), data, level + 1);
        getLevel(node.getLeftChild(), data, level + 1);
        return -1;
    }


    @Override
    public int getLevel(T data) {
        getLevel(root, data, 0);
        return this.level;
    }


    private List<T> getNodesAtLevel(Node<T> node, int currentLevel, List<Node<T>> list, int level) {

        if (currentLevel == level) {
            list.add(node);
        }
        getNodesAtLevel(node.getLeftChild(), currentLevel + 1, list, level);
        getNodesAtLevel(node.getLeftChild(), currentLevel + 1, list, level);

        return null;
    }


    @Override
    public List<Node<T>> getNodesAtLevel(int level) {
        List<Node<T>> list = new ArrayList<>();

        getNodesAtLevel(root, 0, list, level);

        return list;
    }


    private boolean searchData(Node<T> node, T data) {

        if (node.getData().equals(data)) {
            return true;
        }
        searchData(node.getLeftChild(), data);
        searchData(node.getRightChild(), data);

        return false;
    }

    @Override
    public boolean searchData(T data) {
        return searchData(getRoot(), data);
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
