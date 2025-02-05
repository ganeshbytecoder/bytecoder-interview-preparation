package com.bytecoder.DSA.Part_2_With_Integer.Trees.BinaryTree;

import com.bytecoder.DSA.Part_2_With_Integer.Trees.Node;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.TraversalType;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree implements Tree {
    private Node root;

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Tree insert(int data) {
        if (isEmpty()) {
            root = new Node(data);
        } else {
            insertNode(root, data);
        }
        return this;
    }



    private void insertNode(Node current, int data) {
        if (current.getLeftChild() == null) {
            current.setLeftChild(new Node(data));
        } else if (current.getRightChild() == null) {
            current.setRightChild(new Node(data));
        } else {
            // Insert in the shortest subtree
            if (getHeight(current.getLeftChild()) <= getHeight(current.getRightChild())) {
                insertNode(current.getLeftChild(), data);
            } else {
                insertNode(current.getRightChild(), data);
            }
        }
    }

    @Override
    public void traverse(TraversalType traversalType) {
        switch (traversalType) {
            case PRE_ORDER -> preOrderTraversal(root);
            case IN_ORDER -> inOrderTraversal(root);
            case POST_ORDER -> postOrderTraversal(root);
            case LEVEL_ORDER -> levelOrderTraversal(root);
        }
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preOrderTraversal(node.getLeftChild());
            preOrderTraversal(node.getRightChild());
        }
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild());
            System.out.print(node.getData() + " ");
            inOrderTraversal(node.getRightChild());
        }
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.getLeftChild());
            postOrderTraversal(node.getRightChild());
            System.out.print(node.getData() + " ");
        }
    }

    private void levelOrderTraversal(Node node) {
        int height = getHeight();
        for (int i = 1; i <= height; i++) {
            printLevel(node, i);
            System.out.println();
        }
    }

    private void printLevel(Node node, int level) {
        if (node == null) return;
        if (level == 1) {
            System.out.print(node.getData() + " ");
        } else if (level > 1) {
            printLevel(node.getLeftChild(), level - 1);
            printLevel(node.getRightChild(), level - 1);
        }
    }

    @Override
    public int size() {
        return calculateSize(root);
    }

    private int calculateSize(Node node) {
        if (node == null) return 0;
        return 1 + calculateSize(node.getLeftChild()) + calculateSize(node.getRightChild());
    }

    @Override
    public int getMax() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return findMax(root);
    }

    private int findMax(Node node) {
        if (node == null) return Integer.MIN_VALUE;
        int max = node.getData();
        int leftMax = findMax(node.getLeftChild());
        int rightMax = findMax(node.getRightChild());
        return Math.max(max, Math.max(leftMax, rightMax));
    }

    @Override
    public int getMin() {
        if (isEmpty()) return Integer.MAX_VALUE;
        return findMin(root);
    }

    private int findMin(Node node) {
        if (node == null) return Integer.MAX_VALUE;
        int min = node.getData();
        int leftMin = findMin(node.getLeftChild());
        int rightMin = findMin(node.getRightChild());
        return Math.min(min, Math.min(leftMin, rightMin));
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
    }

    @Override
    public int getLevel(int data) {
        return findLevel(root, data, 1);
    }

    private int findLevel(Node node, int data, int level) {
        if (node == null) return -1;
        if (node.getData() == data) return level;
        
        int leftLevel = findLevel(node.getLeftChild(), data, level + 1);
        if (leftLevel != -1) return leftLevel;
        
        return findLevel(node.getRightChild(), data, level + 1);
    }

    @Override
    public List<Node> getNodesAtLevel(int level) {
        List<Node> nodes = new ArrayList<>();
        collectNodesAtLevel(root, level, 1, nodes);
        return nodes;
    }

    private void collectNodesAtLevel(Node node, int targetLevel, int currentLevel, List<Node> nodes) {
        if (node == null) return;
        if (currentLevel == targetLevel) {
            nodes.add(node);
            return;
        }
        collectNodesAtLevel(node.getLeftChild(), targetLevel, currentLevel + 1, nodes);
        collectNodesAtLevel(node.getRightChild(), targetLevel, currentLevel + 1, nodes);
    }

    @Override
    public boolean searchData(int data) {
        return search(root, data);
    }

    private boolean search(Node node, int data) {
        if (node == null) return false;
        if (node.getData() == data) return true;
        return search(node.getLeftChild(), data) || search(node.getRightChild(), data);
    }

    @Override
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node node, int data) {
        if (node == null) return null;
        
        if (node.getData() == data) {
            // Case 1: Leaf node
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }
            // Case 2: Node with one child
            if (node.getLeftChild() == null) return node.getRightChild();
            if (node.getRightChild() == null) return node.getLeftChild();
            
            // Case 3: Node with two children
            // Find the minimum value in right subtree (successor)
            Node successor = findMinToBeDeleted(node.getRightChild());
            node.setData(successor.getData());
            node.setRightChild(deleteNode(node.getRightChild(), successor.getData()));
            return node;
        }
        
        node.setLeftChild(deleteNode(node.getLeftChild(), data));
        node.setRightChild(deleteNode(node.getRightChild(), data));
        return node;
    }

    private Node findMinToBeDeleted(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }
}
