package com.bytecoder.DSA.Part_2_With_Integer.Trees.NaryTree;

import com.bytecoder.DSA.Part_2_With_Integer.Trees.Node;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.TraversalType;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.Tree;

import java.util.*;

/**
 * Enhanced N-ary Tree implementation with common FAANG interview questions.
 * Each node can have multiple children stored in a list.
 * Time and Space complexity provided for each operation.
 */
public class NaryTree implements Tree {
    private NaryNode root;

    // Custom Node class for N-ary Tree
    public static class NaryNode extends Node {
        private List<NaryNode> children;

        public NaryNode(int data) {
            super(data);
            this.children = new ArrayList<>();
        }

        public List<NaryNode> getChildren() {
            return children;
        }

        public void addChild(NaryNode child) {
            children.add(child);
        }

        public void removeChild(NaryNode child) {
            children.remove(child);
        }
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // Time: O(1), Space: O(1)
    @Override
    public Tree insert(int data) {
        if (root == null) {
            root = new NaryNode(data);
        } else {
            // Add as child to the leftmost node at the last level
            insertAtLastLevel(data);
        }
        return this;
    }

    private void insertAtLastLevel(int data) {
        Queue<NaryNode> queue = new LinkedList<>();
        queue.offer(root);
        NaryNode lastParent = root;

        while (!queue.isEmpty()) {
            NaryNode current = queue.poll();
            if (current.getChildren().isEmpty()) {
                lastParent = current;
                break;
            }
            queue.addAll(current.getChildren());
        }
        lastParent.addChild(new NaryNode(data));
    }

    // FAANG Question 1: Serialize and Deserialize N-ary Tree
    // Time: O(n), Space: O(n)
    public String serialize() {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(NaryNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.getData()).append(",");
        sb.append(node.getChildren().size()).append(",");
        for (NaryNode child : node.getChildren()) {
            serializeHelper(child, sb);
        }
    }

    public void deserialize(String data) {
        if (data.isEmpty()) {
            root = null;
            return;
        }
        String[] values = data.split(",");
        int[] index = {0};
        root = deserializeHelper(values, index);
    }

    private NaryNode deserializeHelper(String[] values, int[] index) {
        if (index[0] >= values.length) return null;
        
        NaryNode node = new NaryNode(Integer.parseInt(values[index[0]]));
        index[0]++;
        int numChildren = Integer.parseInt(values[index[0]]);
        index[0]++;
        
        for (int i = 0; i < numChildren; i++) {
            node.addChild(deserializeHelper(values, index));
        }
        return node;
    }

    // FAANG Question 2: Maximum Depth
    // Time: O(n), Space: O(n)
    public int maxDepth() {
        if (root == null) return 0;
        return maxDepthHelper(root);
    }

    private int maxDepthHelper(NaryNode node) {
        if (node == null) return 0;
        int maxChildDepth = 0;
        for (NaryNode child : node.getChildren()) {
            maxChildDepth = Math.max(maxChildDepth, maxDepthHelper(child));
        }
        return 1 + maxChildDepth;
    }

    // FAANG Question 3: Level Order Traversal
    // Time: O(n), Space: O(n)
    public List<List<Integer>> levelOrder() {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<NaryNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                NaryNode node = queue.poll();
                currentLevel.add(node.getData());
                queue.addAll(node.getChildren());
            }
            result.add(currentLevel);
        }
        return result;
    }

    // FAANG Question 4: Find Path Sum
    // Time: O(n), Space: O(h)
    public boolean hasPathSum(int targetSum) {
        if (root == null) return false;
        return hasPathSumHelper(root, targetSum);
    }

    private boolean hasPathSumHelper(NaryNode node, int remainingSum) {
        if (node == null) return false;
        if (node.getChildren().isEmpty()) return node.getData() == remainingSum;
        
        for (NaryNode child : node.getChildren()) {
            if (hasPathSumHelper(child, remainingSum - node.getData())) {
                return true;
            }
        }
        return false;
    }

    // FAANG Question 5: Find Maximum Path Sum
    // Time: O(n), Space: O(h)
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum() {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(NaryNode node) {
        if (node == null) return 0;
        
        int currentSum = node.getData();
        int maxChildSum = 0;
        
        for (NaryNode child : node.getChildren()) {
            maxChildSum = Math.max(maxChildSum, maxPathSumHelper(child));
        }
        
        maxSum = Math.max(maxSum, currentSum + maxChildSum);
        return currentSum + maxChildSum;
    }

    // Original Tree Interface Methods
    @Override
    public void traverse(TraversalType type) {
        List<Integer> result = new ArrayList<>();
        switch (type) {
            case PREORDER:
                preorderTraversal(root, result);
                break;
            case POSTORDER:
                postorderTraversal(root, result);
                break;
            case LEVELORDER:
                levelorderTraversal(result);
                break;
            default:
                System.out.println("Inorder traversal not applicable for N-ary trees");
                return;
        }
        System.out.println(type + " traversal: " + result);
    }

    private void preorderTraversal(NaryNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.getData());
        for (NaryNode child : node.getChildren()) {
            preorderTraversal(child, result);
        }
    }

    private void postorderTraversal(NaryNode node, List<Integer> result) {
        if (node == null) return;
        for (NaryNode child : node.getChildren()) {
            postorderTraversal(child, result);
        }
        result.add(node.getData());
    }

    private void levelorderTraversal(List<Integer> result) {
        if (root == null) return;
        Queue<NaryNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            NaryNode node = queue.poll();
            result.add(node.getData());
            queue.addAll(node.getChildren());
        }
    }

    @Override
    public int size() {
        return calculateSize(root);
    }

    private int calculateSize(NaryNode node) {
        if (node == null) return 0;
        int size = 1;
        for (NaryNode child : node.getChildren()) {
            size += calculateSize(child);
        }
        return size;
    }

    @Override
    public int getMax() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return findMax(root);
    }

    private int findMax(NaryNode node) {
        if (node == null) return Integer.MIN_VALUE;
        int max = node.getData();
        for (NaryNode child : node.getChildren()) {
            max = Math.max(max, findMax(child));
        }
        return max;
    }

    @Override
    public int getMin() {
        if (isEmpty()) return Integer.MAX_VALUE;
        return findMin(root);
    }

    private int findMin(NaryNode node) {
        if (node == null) return Integer.MAX_VALUE;
        int min = node.getData();
        for (NaryNode child : node.getChildren()) {
            min = Math.min(min, findMin(child));
        }
        return min;
    }

    @Override
    public int getHeight() {
        return maxDepth() - 1;
    }

    @Override
    public int getLevel(int data) {
        return findLevel(root, data, 0);
    }

    private int findLevel(NaryNode node, int data, int level) {
        if (node == null) return -1;
        if (node.getData() == data) return level;
        
        for (NaryNode child : node.getChildren()) {
            int childLevel = findLevel(child, data, level + 1);
            if (childLevel != -1) return childLevel;
        }
        return -1;
    }

    @Override
    public List<Node> getNodesAtLevel(int level) {
        List<Node> nodes = new ArrayList<>();
        collectNodesAtLevel(root, level, 0, nodes);
        return nodes;
    }

    private void collectNodesAtLevel(NaryNode node, int targetLevel, int currentLevel, List<Node> nodes) {
        if (node == null) return;
        if (currentLevel == targetLevel) {
            nodes.add(node);
            return;
        }
        for (NaryNode child : node.getChildren()) {
            collectNodesAtLevel(child, targetLevel, currentLevel + 1, nodes);
        }
    }

    @Override
    public boolean searchData(int data) {
        return search(root, data);
    }

    private boolean search(NaryNode node, int data) {
        if (node == null) return false;
        if (node.getData() == data) return true;
        
        for (NaryNode child : node.getChildren()) {
            if (search(child, data)) return true;
        }
        return false;
    }

    @Override
    public void delete(int data) {
        if (root == null) return;
        if (root.getData() == data) {
            root = null;
            return;
        }
        deleteNode(root, data);
    }

    private boolean deleteNode(NaryNode parent, int data) {
        for (NaryNode child : parent.getChildren()) {
            if (child.getData() == data) {
                parent.removeChild(child);
                return true;
            }
            if (deleteNode(child, data)) return true;
        }
        return false;
    }
}
