package com.bytecoder.DSA.Part_4.Trees.BinaryTree;
import java.lang.*;
import com.bytecoder.DSA.Part_4.Trees.Node;
import com.bytecoder.DSA.Part_4.Trees.TraversalType;
import com.bytecoder.DSA.Part_4.Trees.Tree;

import java.util.*;


public class IntegerBinaryTree implements Tree<Integer> {

    private Node<Integer> root;

    public IntegerBinaryTree() {
    }

    @Override
    public Node<Integer> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }


    @Override
    public IntegerBinaryTree insert(Integer data) {
        if (this.isEmpty()) {
            root = new Node<Integer>(data);
            return this;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<Integer> temp = queue.poll();

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


    public void traverseInOrder(Node<Integer> node) {

        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeftChild());
        System.out.print(String.format(" %s", node.getData()));
        traverseInOrder(node.getRightChild());
    }

    public void traverseLevelOrder(Node<Integer> node) {

        if (node == null) {
            return;
        }
        int height =0 ;
        Node<Integer> curr = node;
        List<List<Integer>> lists = new ArrayList<>();
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()) {
            int h = queue.size();
            List<Integer> tempResult = new ArrayList<>();
            while (h > 0) {
                Node<Integer> temp = queue.poll();
                tempResult.add(temp.getData());
                if (temp.getLeftChild() != null) {
                    queue.add(temp.getLeftChild());
                }

                if (temp.getRightChild() != null) {
                    queue.add(temp.getRightChild());
                }
                h--;
            }
            h++;
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

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        int counter=0;
        while(!queue.isEmpty()){
            Node<Integer> temp =queue.poll();
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

    public int size(Node<Integer> node) {
        if(node== null){
            return 0;
        }
        return 1 + size(node.getLeftChild()) + size(node.getRightChild());
    }


    private Node<Integer> minNode(Node<Integer> node) {
        if (node.getLeftChild() == null) {
            return node;
        }

        return minNode(node.getLeftChild());
    }


    private Node<Integer> delete(Node<Integer> node, Integer key) {
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
                Node<Integer> temp = minNode(node.getRightChild());
                node.setData(temp.getData());
                node.setRightChild(delete(node.getRightChild(), temp.getData()));
            }
        }

        node.setLeftChild(delete(node.getLeftChild(), key));
        node.setRightChild(delete(node.getRightChild(), key));

        return node;
    }

    @Override
    public void delete(Integer data) {

        this.root = delete(root, data);
    }



    private int get_max(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max((Integer) node.getData(), Math.max(get_max(node.getLeftChild()),get_max(node.getRightChild())));
    }

    private int getMaxUsingBFS(Node<Integer> node) {
        int maxValue = Integer.MIN_VALUE;
        if (node == null) {
            return maxValue;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            Node<Integer> temp = queue.poll();

            maxValue = Math.max(maxValue, temp.getData());

            if(temp.getLeftChild() != null){
                queue.add(temp.getLeftChild());
            }

            if(temp.getRightChild() != null){
                queue.add(temp.getRightChild());
            }
        }
        return maxValue;
    }



    @Override
    public int getMax() {
        if (isEmpty()) {
            return -1;
        }
        return get_max(root);
    }


    private int get_min(Node<Integer> node) {
        if (node == null) {
            return java.lang.Integer.MIN_VALUE;
        }
        return Math.min( node.getData(), Math.min(get_min(node.getLeftChild()), get_min(node.getRightChild())));
    }

    private int getMinUsingBFS(Node<Integer> node) {
        int minValue = Integer.MAX_VALUE;
        if (node == null) {
            return minValue;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            Node<Integer> temp = queue.poll();

            minValue = Math.min(minValue, temp.getData());

            if(temp.getLeftChild() != null){
                queue.add(temp.getLeftChild());
            }

            if(temp.getRightChild() != null){
                queue.add(temp.getRightChild());
            }
        }
        return minValue;
    }

    @Override
    public int getMin() {
        if (isEmpty()) {
            return -1;
        }
        return get_min(root);
    }


    private int getHeight(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
    }

    private int getHeightUsinBfs(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);
        int height =0;
        while(!queue.isEmpty()){
            int level = queue.size();
            while(level > 0){
                Node<Integer> temp = queue.poll();
                if(temp.getLeftChild() != null){
                    queue.add(temp.getLeftChild());
                }
                if(temp.getRightChild() != null){
                    queue.add(temp.getRightChild());
                }
                level--;
            }
            height++;
        }
        return height;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }


    private int getLevel(Node<Integer> node, Integer data, int level) {
        if (node == null) {
            return -1;
        }
        if (node.getData().equals(data)) {
            return level;
        }
        return Math.max(getLevel(node.getLeftChild(), data, level + 1), getLevel(node.getLeftChild(), data, level + 1));
    }


    @Override
    public int getLevel(Integer data) {
        return getLevel(root, data, 0);
    }


    private void getNodesAtLevel(Node<Integer> node, int currentLevel, List<Node<Integer>> list, int level) {

        if (node == null) {
            return;
        }
        if (currentLevel == level) {
            list.add(node);
        }
        getNodesAtLevel(node.getLeftChild(), currentLevel + 1, list, level);
        getNodesAtLevel(node.getRightChild(), currentLevel + 1, list, level);

    }

    private Queue<Node<Integer>> getNodesAtLevel(Node<Integer> node, int currentLevel) {
        int height  = 0 ;
        if (node == null) {
            return new LinkedList<>();
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            int level = queue.size();
            while(level>0){
                Node<Integer> temp = queue.poll();

                if(temp.getLeftChild() != null){
                    queue.add(temp.getLeftChild());
                }

                if(temp.getRightChild() != null){
                    queue.add(temp.getRightChild());
                }
                level--;
            }
            height++;
            if(height == currentLevel){
                return queue;
            }

        }
        return queue;

    }



    @Override
    public List<Node<Integer>> getNodesAtLevel(int level) {
        List<Node<Integer>> list = new ArrayList<>();

        getNodesAtLevel(root, 0, list, level);

        return list;
    }



    private Node<Integer> searchDataNode(Integer data) {
        Stack<Node<Integer>> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node<Integer> temp = stack.pop();

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
    public boolean searchData(Integer data) {
        return searchDataNode(data) != null? true : false;
    }


}
