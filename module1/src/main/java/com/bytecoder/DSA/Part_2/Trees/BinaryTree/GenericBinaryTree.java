package com.bytecoder.DSA.Part_2.Trees.BinaryTree;

import com.bytecoder.DSA.Part_2.Trees.Node;
import com.bytecoder.DSA.Part_2.Trees.TraversalType;
import com.bytecoder.DSA.Part_2.Trees.Tree;

import java.util.*;


public class GenericBinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public GenericBinaryTree() {
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


    public void traverseInOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeftChild());
        System.out.print(String.format(" %s", node.getData()));
        traverseInOrder(node.getRightChild());
    }

    public void traverseLevelOrder(Node<T> node) {

        if (node == null) {
            return;
        }
        Node<T> curr = node;
        List<List<T>> lists = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()) {
            int h = queue.size();
            List<T> tempResult = new ArrayList<>();
            while (h > 0) {
                Node<T> temp = queue.poll();
                tempResult.add(temp.getData());
                if (temp.getLeftChild() != null) {
                    queue.add(temp.getLeftChild());
                }

                if (temp.getRightChild() != null) {
                    queue.add(temp.getRightChild());
                }
                h--;
            }
            lists.add(tempResult);

        }
        System.out.println(lists);
    }

    @Override
    public void traverse(TraversalType traversalType) {
        System.out.println("\n Solving using recursion");
        System.out.println(traversalType);
        if ((traversalType.equals(TraversalType.IN_ORDER))) {
            traverseInOrder(this.getRoot());

        }
        if (traversalType.equals(TraversalType.LEVEL_ORDER)) {
            traverseLevelOrder(this.getRoot());

        }
    }

    @Override
    public int size() {
        if(getRoot()== null){
            return 0;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        int counter=0;
        while(!queue.isEmpty()){
            Node<T> temp =queue.poll();
            counter++;
            if(temp.getLeftChild() != null){
                queue.add(temp.getLeftChild());
            }
            if(temp.getRightChild() != null){
                queue.add(temp.getRightChild());
            }
        }

        return counter;
    }

    public int size(Node<T> node) {
        if(node== null){
            return 0;
        }
        return 1 + size(node.getLeftChild()) + size(node.getRightChild());
    }


//    private int minNode(Node<T> node) {
//        if (node.getLeftChild() == null) {
//            return Integer.MIN_VALUE;
//        }
//        return Math.min((int) node.getData(), Math.min(minNode(node.getRightChild()), minNode(node.getRightChild())));
//    }


    private Node<T> minNode(Node<T> node) {
        if (node.getLeftChild() == null) {
            return node;
        }
        return minNode(node.getLeftChild());
    }


    private Node<T> delete(Node<T> node, T key) {
        if (node == null) {
            return null;
        }
        if (node.getData() == key) {
            if (node.getLeftChild() == null || node.getRightChild() == null) {
                if (node.getLeftChild() == null) {
                    return node.getRightChild();

                } else {
                    return node.getLeftChild();

                }
            } else {
                Node<T> temp = minNode(node.getRightChild());
                node.setData(temp.getData());
                node.setRightChild(delete(node.getRightChild(), temp.getData()));
            }
        }

        node.setLeftChild(delete(node.getLeftChild(), key));
        node.setRightChild(delete(node.getRightChild(), key));

        return node;
    }

    @Override
    public void delete(T data) {

        this.root = delete(root, data);
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


    private int get_min(Node<T> node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        return Math.min((Integer) node.getData(), Math.min(get_min(node.getLeftChild()), get_min(node.getRightChild())));
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


    private int getLevel(Node<T> node, T data, int level) {
        if (node == null) {
            return -1;
        }
        if (node.getData().equals(data)) {
            return level;
        }
        return Math.max(getLevel(node.getLeftChild(), data, level + 1), getLevel(node.getLeftChild(), data, level + 1));
    }


    @Override
    public int getLevel(T data) {
        return getLevel(root, data, 0);
    }


    private void getNodesAtLevel(Node<T> node, int currentLevel, List<Node<T>> list, int level) {

        if (node == null) {
            return;
        }
        if (currentLevel == level) {
            list.add(node);
        }
        getNodesAtLevel(node.getLeftChild(), currentLevel + 1, list, level);
        getNodesAtLevel(node.getRightChild(), currentLevel + 1, list, level);

    }


    @Override
    public List<Node<T>> getNodesAtLevel(int level) {
        List<Node<T>> list = new ArrayList<>();

        getNodesAtLevel(root, 0, list, level);

        return list;
    }



    private Node<T> searchDataNode(T data) {
        Stack<Node<T>> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node<T> temp = stack.pop();

            if (temp.getData() == data) {
                return temp;
            }

            if (temp.getRightChild() != null) {
                stack.add(temp.getRightChild());
            }
            if (temp.getLeftChild() != null) {
                stack.add(temp.getLeftChild());
            }
        }
        return null;
    }

    @Override
    public boolean searchData(T data) {
        return searchDataNode(data) != null? true : false;
    }


}
