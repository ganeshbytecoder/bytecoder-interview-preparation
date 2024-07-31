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
            root = new Node(data);
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
        System.out.println(root);
        return this;
    }

    private void insert_m2(Node<T> node, T data) {
        if (this.isEmpty()) {
            root = new Node<>(data);
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

        insert_m2(node.getLeftChild(), data);
        insert_m2(node.getRightChild(), data);
    }

    @Override
    public Tree<T> insert_m2(T data) {
        insert_m2(root, data);
        return this;
    }


    @Override
    public void traverse(TraversalType traversalType) {
        System.out.println("\n Solving using recursion");
        System.out.println(traversalType);
        if ((traversalType.equals(TraversalType.IN_ORDER))) {
            traverseInOrder(this.root);
            traverse_m2(traversalType);

        }
        if (traversalType.equals(TraversalType.POST_ORDER)) {
            traversePostOrder(this.root);
            traverse_m2(traversalType);


        }
        if (traversalType.equals(TraversalType.PRE_ORDER)) {
            traversePreOrder(this.root);
            traverse_m2(traversalType);


        }
        if (traversalType.equals(TraversalType.LEVEL_ORDER)) {
            traverseLevelOrder(this.root);
            traverse_m2(traversalType);

        }
    }

    @Override
    public void traverse_m2(TraversalType traversalType) {

        System.out.println("\n Solving using without recursion");
        System.out.println(traversalType);
        if ((traversalType.equals(TraversalType.IN_ORDER))) {
            traverseInOrder_m2(this.root);
        }
        if (traversalType.equals(TraversalType.POST_ORDER)) {
            traversePostOrder_m2(this.root);

        }
        if (traversalType.equals(TraversalType.PRE_ORDER)) {
            traversePreOrder_m2(this.root);

        }
        if (traversalType.equals(TraversalType.LEVEL_ORDER)) {
            traverseLevelOrder_m2(this.root);

        }

    }

    private void traverseLevelOrder_m2(Node<T> root) {


    }

    private void traversePreOrder_m2(Node<T> root) {
        Stack<Node> stack = new Stack<>();
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

    private void traversePostOrder_m2(Node<T> root) {
    }

    private void traverseInOrder_m2(Node<T> root) {

    }


    @Override
    public void delete(T data) {


    }

    @Override
    public void delete_m2(T data) {

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

    private int get_max_m2(Node node) {
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
        if (isEmpty()) {
            return max_value;
        }
        int m1 = get_max(root);
        int m2 = get_max_m2(root);
        if (m1 - m2 != 0) {

            throw new RuntimeException(String.format("Both M1 and M2 results are not same %d  %d", m1, m2));
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
