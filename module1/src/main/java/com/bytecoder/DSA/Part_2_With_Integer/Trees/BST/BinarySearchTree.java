package com.bytecoder.DSA.Part_2_With_Integer.Trees.BST;

import com.bytecoder.DSA.Part_2_With_Integer.Trees.Node;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.TraversalType;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.Tree;

import java.util.*;

/**
 * Enhanced Binary Search Tree implementation with common FAANG interview questions.
 * Time and Space complexity provided for each operation.
 */
public class BinarySearchTree implements Tree {
    private Node root;

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // Time: O(h), Space: O(h) where h is height
    @Override
    public Tree insert(int data) {
        root = insertRec(root, data);
        return this;
    }

    private Node insertRec(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.getData()) {
            node.setLeftChild(insertRec(node.getLeftChild(), data));
        } else if (data > node.getData()) {
            node.setRightChild(insertRec(node.getRightChild(), data));
        }

        return node;
    }

    // FAANG Question 1: Validate BST
    // Time: O(n), Space: O(h)
    public boolean isValidBST() {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(Node node, long min, long max) {
        if (node == null) return true;
        
        if (node.getData() <= min || node.getData() >= max) return false;
        
        return isValidBST(node.getLeftChild(), min, node.getData()) &&
               isValidBST(node.getRightChild(), node.getData(), max);
    }

    // FAANG Question 2: Kth Smallest Element
    // Time: O(h + k), Space: O(h)
    public int kthSmallest(int k) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeftChild();
            }
            
            current = stack.pop();
            if (--k == 0) return current.getData();
            current = current.getRightChild();
        }
        
        throw new IllegalArgumentException("k is larger than the size of BST");
    }

    // FAANG Question 3: Convert BST to Greater Tree
    // Time: O(n), Space: O(h)
    private int sum = 0;
    
    public Node convertToGreaterTree() {
        sum = 0;
        convertGreaterTreeHelper(root);
        return root;
    }

    private void convertGreaterTreeHelper(Node node) {
        if (node == null) return;
        
        convertGreaterTreeHelper(node.getRightChild());
        sum += node.getData();
        node.setData(sum);
        convertGreaterTreeHelper(node.getLeftChild());
    }

    // FAANG Question 4: Recover BST (Two nodes swapped)
    // Time: O(n), Space: O(h)
    public void recoverBST() {
        Node[] swapped = new Node[2];
        Node[] prev = new Node[1];
        findSwappedNodes(root, prev, swapped);
        
        // Swap the values
        int temp = swapped[0].getData();
        swapped[0].setData(swapped[1].getData());
        swapped[1].setData(temp);
    }

    private void findSwappedNodes(Node node, Node[] prev, Node[] swapped) {
        if (node == null) return;
        
        findSwappedNodes(node.getLeftChild(), prev, swapped);
        
        if (prev[0] != null && prev[0].getData() > node.getData()) {
            if (swapped[0] == null) {
                swapped[0] = prev[0];
            }
            swapped[1] = node;
        }
        prev[0] = node;
        
        findSwappedNodes(node.getRightChild(), prev, swapped);
    }

    // FAANG Question 5: Lowest Common Ancestor in BST
    // Time: O(h), Space: O(1)
    public Node lowestCommonAncestor(Node p, Node q) {
        Node current = root;
        
        while (current != null) {
            if (p.getData() < current.getData() && q.getData() < current.getData()) {
                current = current.getLeftChild();
            } else if (p.getData() > current.getData() && q.getData() > current.getData()) {
                current = current.getRightChild();
            } else {
                return current;
            }
        }
        
        return null;
    }

    // FAANG Question 6: Closest Value in BST
    // Time: O(h), Space: O(1)
    public int closestValue(double target) {
        int closest = root.getData();
        Node current = root;
        
        while (current != null) {
            if (Math.abs(current.getData() - target) < Math.abs(closest - target)) {
                closest = current.getData();
            }
            current = target < current.getData() ? current.getLeftChild() : current.getRightChild();
        }
        
        return closest;
    }

    // FAANG Question 7: Range Sum BST
    // Time: O(n), Space: O(h)
    public int rangeSumBST(int low, int high) {
        return rangeSumHelper(root, low, high);
    }

    private int rangeSumHelper(Node node, int low, int high) {
        if (node == null) return 0;
        
        if (node.getData() < low) {
            return rangeSumHelper(node.getRightChild(), low, high);
        }
        if (node.getData() > high) {
            return rangeSumHelper(node.getLeftChild(), low, high);
        }
        
        return node.getData() + 
               rangeSumHelper(node.getLeftChild(), low, high) + 
               rangeSumHelper(node.getRightChild(), low, high);
    }

    // FAANG Question 8: Construct BST from Preorder Traversal
    // Time: O(n), Space: O(h)
    private int preorderIndex = 0;
    
    public Node constructFromPreorder(int[] preorder) {
        preorderIndex = 0;
        return constructFromPreorderHelper(preorder, Integer.MAX_VALUE);
    }

    private Node constructFromPreorderHelper(int[] preorder, int bound) {
        if (preorderIndex == preorder.length || preorder[preorderIndex] > bound) {
            return null;
        }
        
        Node node = new Node(preorder[preorderIndex++]);
        node.setLeftChild(constructFromPreorderHelper(preorder, node.getData()));
        node.setRightChild(constructFromPreorderHelper(preorder, bound));
        return node;
    }

    // Original Tree Interface Methods
    @Override
    public void traverse(TraversalType type) {
        List<Integer> result = new ArrayList<>();
        switch (type) {
            case INORDER:
                inorderTraversal(root, result);
                break;
            case PREORDER:
                preorderTraversal(root, result);
                break;
            case POSTORDER:
                postorderTraversal(root, result);
                break;
            case LEVELORDER:
                levelOrderTraversal(result);
                break;
        }
        // Print the result
        System.out.println(type + " traversal: " + result);
    }

    private void inorderTraversal(Node node, List<Integer> result) {
        if (node == null) return;
        inorderTraversal(node.getLeftChild(), result);
        result.add(node.getData());
        inorderTraversal(node.getRightChild(), result);
    }

    private void preorderTraversal(Node node, List<Integer> result) {
        if (node == null) return;
        result.add(node.getData());
        preorderTraversal(node.getLeftChild(), result);
        preorderTraversal(node.getRightChild(), result);
    }

    private void postorderTraversal(Node node, List<Integer> result) {
        if (node == null) return;
        postorderTraversal(node.getLeftChild(), result);
        postorderTraversal(node.getRightChild(), result);
        result.add(node.getData());
    }

    private void levelOrderTraversal(List<Integer> result) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            result.add(node.getData());
            
            if (node.getLeftChild() != null) queue.offer(node.getLeftChild());
            if (node.getRightChild() != null) queue.offer(node.getRightChild());
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
        Node current = root;
        while (current.getRightChild() != null) {
            current = current.getRightChild();
        }
        return current.getData();
    }

    @Override
    public int getMin() {
        if (isEmpty()) return Integer.MAX_VALUE;
        Node current = root;
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current.getData();
    }

    @Override
    public int getLevel(int data) {
        return findLevel(root, data, 1);
    }

    private int findLevel(Node node, int data, int level) {
        if (node == null) return -1;
        if (node.getData() == data) return level;
        
        if (data < node.getData()) {
            return findLevel(node.getLeftChild(), data, level + 1);
        }
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
        
        if (data < node.getData()) {
            return search(node.getLeftChild(), data);
        }
        return search(node.getRightChild(), data);
    }

    @Override
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node node, int data) {
        if (node == null) return null;
        
        if (data < node.getData()) {
            node.setLeftChild(deleteNode(node.getLeftChild(), data));
        } else if (data > node.getData()) {
            node.setRightChild(deleteNode(node.getRightChild(), data));
        } else {
            // Case 1: Leaf node
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }
            // Case 2: Node with one child
            if (node.getLeftChild() == null) return node.getRightChild();
            if (node.getRightChild() == null) return node.getLeftChild();
            
            // Case 3: Node with two children
            Node successor = findMin(node.getRightChild());
            node.setData(successor.getData());
            node.setRightChild(deleteNode(node.getRightChild(), successor.getData()));
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    @Override
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(calculateHeight(node.getLeftChild()), calculateHeight(node.getRightChild()));
    }
}
