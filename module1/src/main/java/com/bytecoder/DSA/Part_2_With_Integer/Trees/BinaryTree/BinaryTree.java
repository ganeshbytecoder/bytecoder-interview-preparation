package com.bytecoder.DSA.Part_2_With_Integer.Trees.BinaryTree;

import com.bytecoder.DSA.Part_2_With_Integer.Trees.Node;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.TraversalType;
import com.bytecoder.DSA.Part_2_With_Integer.Trees.Tree;

import java.util.*;

/**
 * Comprehensive Binary Tree implementation with common FAANG interview questions.
 * Time and Space complexity provided for each operation.
 */
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
            insertNode_BFS(data);
        }
        return this;
    }

    private void insertNode_DFS(Node current, int data) {
        if (current.getLeftChild() == null) {
            current.setLeftChild(new Node(data));
        } else if (current.getRightChild() == null) {
            current.setRightChild(new Node(data));
        } else {
            // Insert in the shortest subtree
            if (getHeight(current.getLeftChild()) <= getHeight(current.getRightChild())) {
                insertNode_DFS(current.getLeftChild(), data);
            } else {
                insertNode_DFS(current.getRightChild(), data);
            }
        }
    }

    // Time: O(n), Space: O(w) where w is max width
    private void insertNode_BFS(int data) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.getLeftChild() == null) {
                current.setLeftChild(new Node(data));
                return;
            }
            if (current.getRightChild() == null) {
                current.setRightChild(new Node(data));
                return;
            }

            queue.add(current.getLeftChild());
            queue.add(current.getRightChild());
        }
    }

    // FAANG Question 1: Check if tree is balanced
    // Time: O(n), Space: O(h) where h is height
    public boolean isBalanced() {
        return checkBalance(root) != -1;
    }

    private int checkBalance(Node node) {
        if (node == null) return 0;
        
        int leftHeight = checkBalance(node.getLeftChild());
        if (leftHeight == -1) return -1;
        
        int rightHeight = checkBalance(node.getRightChild());
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // FAANG Question 2: Serialize and Deserialize Binary Tree
    // Time: O(n), Space: O(n)
    public String serialize() {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString().trim();
    }

    private void serializeHelper(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append("null ");
            return;
        }
        sb.append(node.getData()).append(" ");
        serializeHelper(node.getLeftChild(), sb);
        serializeHelper(node.getRightChild(), sb);
    }

    public void deserialize(String data) {
        if (data == null || data.equals("null")) {
            root = null;
            return;
        }
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(" ")));
        root = deserializeHelper(nodes);
    }

    private Node deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        if (val == null || val.equals("null")) return null;
        Node node = new Node(Integer.parseInt(val));
        node.setLeftChild(deserializeHelper(nodes));
        node.setRightChild(deserializeHelper(nodes));
        return node;
    }

    // FAANG Question 3: Lowest Common Ancestor
    // Time: O(n), Space: O(h)
    public Node lowestCommonAncestor(Node p, Node q) {
        if (root == null || p == null || q == null) return null;
        return findLCA(root, p, q);
    }

    private Node findLCA(Node node, Node p, Node q) {
        if (node == null || node == p || node == q) return node;
        
        Node left = findLCA(node.getLeftChild(), p, q);
        Node right = findLCA(node.getRightChild(), p, q);
        
        if (left != null && right != null) return node;
        return left != null ? left : right;
    }

    // FAANG Question 4: Maximum Path Sum
    // Time: O(n), Space: O(h)
    private int maxSum;
    
    public int maxPathSum() {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(Node node) {
        if (node == null) return 0;
        
        int leftGain = Math.max(maxPathSumHelper(node.getLeftChild()), 0);
        int rightGain = Math.max(maxPathSumHelper(node.getRightChild()), 0);
        
        maxSum = Math.max(maxSum, node.getData() + leftGain + rightGain);
        
        return node.getData() + Math.max(leftGain, rightGain);
    }

    // FAANG Question 5: Right Side View -> bfs as well
    // Time: O(n), Space: O(h)
    public List<Integer> rightSideView() {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    private void rightView(Node node, List<Integer> result, int level) {
        if (node == null) return;
        
        if (level == result.size()) {
            result.add(node.getData());
        }
        
        rightView(node.getRightChild(), result, level + 1);
        rightView(node.getLeftChild(), result, level + 1);
    }


    // FAANG Question 5: Right Side View using BFS
    // Time: O(n), Space: O(h)
    public List<Integer> rightSideView(Node root) {
        List<Integer> result =  new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){


            boolean flag= true;
            int level = queue.size();
            while(level >0){
                Node temp = queue.poll();
                if(flag){
                    result.add(temp.data);
                    flag= false;
                }
                if(temp.rightChild != null ){
                    queue.add(temp.rightChild);
                }
                if(temp.leftChild != null){
                    queue.add(temp.leftChild);
                }
                level--;
            }
        }

        return result;
    }



    // FAANG Question 6: Diameter of Binary Tree -> Q2 - max width of BT (level order )
    // Time: O(n), Space: O(h)
    private int maxDiameter;
    
    public int diameterOfBinaryTree() {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;
    }

    private int calculateHeight(Node node) {
        if (node == null) return 0;
        
        int leftHeight = calculateHeight(node.getLeftChild());
        int rightHeight = calculateHeight(node.getRightChild());
        
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // FAANG Question 7: Check if Binary Tree is Symmetric -> Q2 two tree are same or not or mirror or symmetric ?
    // Time: O(n), Space: O(h)
    public boolean isSymmetric() {
        if (root == null) return true;
        return isMirror(root.getLeftChild(), root.getRightChild());
    }

    private boolean isMirror(Node left, Node right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        
        return left.getData() == right.getData()
            && isMirror(left.getLeftChild(), right.getRightChild())
            && isMirror(left.getRightChild(), right.getLeftChild());
    }

    // FAANG Question 8: Vertical Order Traversal
    // Time: O(n log n), Space: O(n)
    public List<List<Integer>> verticalOrder() {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Map<Integer, List<Integer>> columnTable = new TreeMap<>();
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        
        while (!queue.isEmpty()) {
            Pair<Node, Integer> p = queue.poll();
            Node node = p.getKey();
            int column = p.getValue();
            
            columnTable.computeIfAbsent(column, k -> new ArrayList<>()).add(node.getData());
            
            if (node.getLeftChild() != null) {
                queue.offer(new Pair<>(node.getLeftChild(), column - 1));
            }
            if (node.getRightChild() != null) {
                queue.offer(new Pair<>(node.getRightChild(), column + 1));
            }
        }
        
        result.addAll(columnTable.values());
        return result;
    }

    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    // Original Tree Interface Methods
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
            Node successor = findMinNode(node.getRightChild());
            node.setData(successor.getData());
            node.setRightChild(deleteNode(node.getRightChild(), successor.getData()));
            return node;
        }
        
        node.setLeftChild(deleteNode(node.getLeftChild(), data));
        node.setRightChild(deleteNode(node.getRightChild(), data));
        return node;
    }

    private Node findMinNode(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    // Standard traversals
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

    // Helper methods
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())) + 1;
    }
}
