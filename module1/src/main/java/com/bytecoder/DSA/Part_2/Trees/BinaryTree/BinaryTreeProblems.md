# Tree -
a tree is a data structure similar to a linked list but instead of each node pointing simply to the next node in a linear fashion, each node
points to a number of nodes. it's non-linear and non-cyclic data structure. we will study binary tree, BST( Binary Search Tree) and Nary Tree

```java
public static class Node<T extends Comparable<T>> {
    T data;
    Node<T> first_child;
    Node<T> second_child;
    Node<T> third_child;
    .
    .
    .
    Node nth_child;
}

```


* In tree ADT, the order of  the elements  is not important. tree is a graph with special conditions
* **Level / depth of a node:** The count of edges on the path from the root node to that node. The root node has level 0.
*  **Height of a node :** is the length of the path from that node to the deepest node.
* **Height of tree :** is the max height among all the nodes

## Type of binary tree
1. **full binary tree:** if each node has exactly two children and all leaf nodes are at the same level. total nodes at height h will be 2^(h+1)-1.
2. **balanced binary tree:**
3. **complete binary tree :** if all leaf nodes are at height h or h-1. 
4. **perfect binary tree:** 


## Operations on binary tree
* Create – create a tree in the data structure.
* Insert − Inserts data in a tree.
* Search − Searches specific data in a tree to check whether it is present or not.
* 
* Traversal:
  * Depth-First-Search Traversal
    * In-order
    * pre-order
    * post-order
  * Breadth-First-Search Traversal
    * level order 
    * 
* finding the size of the tree 
* finding the height of the tree
* level of node 



## problems


https://chatgpt.com/share/66fe58f2-5008-8006-adbf-3f8e9aa04db2 


https://leetcode.com/problems/delete-nodes-and-return-forest/description/



* https://leetcode.com/problems/merge-two-binary-trees/
* merge two binary trees
```java
class Solution {
    TreeNode merge(TreeNode root1, TreeNode root2){


        if(root1 == null && root2 == null){
            return null;
        }

        if(root1 == null ){
            return root2;
        }

        if(root2 == null ){
            return root1;
        }

        root1.val +=root2.val;

        root1.left = merge(root1.left, root2.left);
        root1.right = merge(root1.right, root2.right);

        return root1;

    }
    
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        return merge(root1, root2);

        
    }
}
```
* https://leetcode.com/problems/invert-binary-tree/description/


* https://leetcode.com/problems/balanced-binary-tree/submissions/1327991003/

```java
   private int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        if(left ==-1 || right ==-1){
            return -1;
        }

        int diff = Math.abs(left-right);

        if( diff > 1 ){
            return -1;
        }

        return 1 + Math.max(left, right);
    }

    public boolean isBalanced(TreeNode root) {

        if(root == null){
            return true;
        }
        // int diff = Math.abs(height(root.left)-height(root.right));
        // if( diff>1 ){
        //     return false;
        // }
        return height(root) != -1;
    }
```

```java
    private int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean isBalanced(TreeNode root) {

        if(root == null){
            return true;
        }
        int diff = Math.abs(height(root.left)-height(root.right));
        if( diff>1 ){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
```


* https://leetcode.com/problems/populating-next-right-pointers-in-each-node/submissions/1328063250/



---

### **4. Diameter of a Tree**
The diameter is the longest path between any two nodes in the tree. Use a recursive DFS to calculate the height of the tree while keeping track of the diameter.

```java
class Result {
    int diameter = 0;
}

int diameterOfTree(Node root) {
    Result result = new Result();
    height(root, result);
    return result.diameter;
}

int height(Node node, Result result) {
    if (node == null) return 0;

    int leftHeight = height(node.left, result);
    int rightHeight = height(node.right, result);

    // Update the diameter at this node
    result.diameter = Math.max(result.diameter, leftHeight + rightHeight);

    // Return the height of the current node
    return 1 + Math.max(leftHeight, rightHeight);
}
```

**Time Complexity**: \(O(n)\), where \(n\) is the number of nodes.  
**Space Complexity**: \(O(h)\), where \(h\) is the height of the tree (due to recursion stack).

---

### **9. Left View of a Tree**
Perform level order traversal and track the first node at each level.

```java
void leftView(Node root) {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            Node node = queue.poll();

            // Print the first node at the current level
            if (i == 0) System.out.print(node.data + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(w)\), where \(w\) is the maximum width of the tree.

---

### **10. Right View of a Tree**
Similar to the left view, but print the last node at each level during level order traversal.

```java
void rightView(Node root) {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            Node node = queue.poll();

            // Print the last node at the current level
            if (i == size - 1) System.out.print(node.data + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(w)\).

---

### **11. Top View of a Tree**
Perform a level order traversal while keeping track of horizontal distances. Use a map to store the first node at each horizontal distance.

```java
void topView(Node root) {
    if (root == null) return;

    Map<Integer, Integer> topViewMap = new TreeMap<>();
    Queue<Pair<Node, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));

    while (!queue.isEmpty()) {
        Pair<Node, Integer> pair = queue.poll();
        Node node = pair.getKey();
        int hd = pair.getValue();

        // Add the node to the map if it's the first node at this horizontal distance
        if (!topViewMap.containsKey(hd)) {
            topViewMap.put(hd, node.data);
        }

        if (node.left != null) queue.add(new Pair<>(node.left, hd - 1));
        if (node.right != null) queue.add(new Pair<>(node.right, hd + 1));
    }

    for (int value : topViewMap.values()) {
        System.out.print(value + " ");
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(n)\) for the map.

---

### **12. Bottom View of a Tree**
Similar to the top view, but update the map for every node at a horizontal distance to track the last node.

```java
void bottomView(Node root) {
    if (root == null) return;

    Map<Integer, Integer> bottomViewMap = new TreeMap<>();
    Queue<Pair<Node, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));

    while (!queue.isEmpty()) {
        Pair<Node, Integer> pair = queue.poll();
        Node node = pair.getKey();
        int hd = pair.getValue();

        // Update the map with the current node for this horizontal distance
        bottomViewMap.put(hd, node.data);

        if (node.left != null) queue.add(new Pair<>(node.left, hd - 1));
        if (node.right != null) queue.add(new Pair<>(node.right, hd + 1));
    }

    for (int value : bottomViewMap.values()) {
        System.out.print(value + " ");
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(n)\) for the map.

---

### Key Notes:
1. **Top View vs. Bottom View**:
   - Top View: Track the first node at each horizontal distance.
   - Bottom View: Track the last node at each horizontal distance.

2. **Queue Pair**:
   - In both views, use a `Queue<Pair<Node, Integer>>` to store the node and its horizontal distance.

3. **TreeMap**:
   - TreeMap automatically sorts the horizontal distances, making it easier to print the result in order.

---

### 14. **Check if a Tree is Balanced**
   - A tree is balanced if the height difference between the left and right subtree is at most 1 for all nodes.
   - Calculate height and check balance recursively.

### 15. **Diagonal Traversal of a Binary Tree**
![img_1.png](img_1.png) 

**Output**: 8 10 14 3 6 7 13 1 4

   - Use a queue to traverse nodes diagonally. For each node, enqueue its left child and move to the right child.
   - To find the diagonal view of a binary tree, we perform a recursive  traversal that stores nodes in a hashmap based on their diagonal levels. Left children increase the diagonal level, while right children remain on the same level.


### 16. **Boundary Traversal of a Binary Tree**![img.png](img.png)
   - Traverse the left boundary, then leaf nodes, then the right boundary (in reverse order).

### 17. **Construct Binary Tree from String with Bracket Representation**
   - Use recursion and string parsing to interpret brackets as indicators of subtree boundaries.

### 18. **Convert Binary Tree into Doubly Linked List**
   - Perform in-order traversal, convert each node into a doubly linked list node, and link them together.

### 19. **Convert Binary Tree into Sum Tree**
   - Recursively replace each node with the sum of its left and right subtrees while returning  Finally, return the sum of new value and value (which is sum of values in the subtree rooted with this node).

### 20. **Construct Binary Tree from Inorder and Preorder Traversal**
   - Use recursion and index tracking to build the tree. The first element in preorder is the root; use inorder to find left and right subtrees.

### 21. **Find Minimum Swaps to Convert Binary Tree to BST**
   - In-order traversal of the tree yields a list. Count the minimum swaps required to sort the list.

### 22. **Check if Binary Tree is Sum Tree**
   - A sum tree is one where the value of each node is equal to the sum of its left and right subtree values.
   - Use a post-order traversal to verify the sum property.

### 23. **Check if All Leaf Nodes are at the Same Level**
   - Use level order traversal and check the levels of leaf nodes.

### 24. **Check if a Binary Tree Contains Duplicate Subtrees of Size 2 or More**
   - Use serialization (or hashing) of subtrees to detect duplicates during post-order traversal.

### 25. **Check if Two Trees are Mirror**
   - Recursively compare the left subtree of one tree with the right subtree of the other.

### 26. **Sum of Nodes on the Longest Path from Root to Leaf Node**
   - Use DFS to find the path with the maximum depth and calculate the sum of its nodes.

### 27. **Check if Given Graph is Tree**
   - A graph is a tree if it is connected and has no cycles.
   - Use BFS/DFS to verify connectivity and absence of cycles.

### 28. **Find Largest Subtree Sum in a Tree**
   - Perform a post-order traversal to calculate the sum of each subtree and track the maximum.

### 29. **Maximum Sum of Nodes in a Binary Tree Such that No Two are Adjacent**
   - Use dynamic programming with two states: include the current node or exclude it.

### 30. **Print All “K” Sum Paths in a Binary Tree**
   - Use DFS and backtracking to find all paths from any node that sum to `K`.

### 31. **Find LCA in a Binary Tree** (good)
   - Use recursion to find the lowest common ancestor by exploring left and right subtrees.

```java
    public void path(TreeNode node, TreeNode target, List<TreeNode> list, List<TreeNode> ans){
        if(node == null){
            return;
        }
        if(node == target){
            list.add(node);
            // ans = new ArrayList<TreeNode>(list);
            for(TreeNode n : list){
                ans.add(n);
            }
            return;
        }
        list.add(node);
        path(node.left, target, list, ans);
        path(node.right, target, list, ans);
        list.remove(node);
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> ans1 = new ArrayList<>();
        List<TreeNode> ans2 = new ArrayList<>();

        path(root, p, new ArrayList<>(), ans1);
        
        path(root, q, new ArrayList<>(), ans2);
        System.out.print(ans1.size());
        for(int i = ans1.size()-1 ; i >= 0; i--){
            System.out.println("check " + ans1.get(i).val);
            if(ans2.contains(ans1.get(i))){
                return ans1.get(i);
            }
        }

        System.out.println("ans root ");
        return root;

    }
```

### 32. **Find Distance Between Two Nodes in a Binary Tree** (good) (https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/)
   - Find LCA of the two nodes, then calculate the distance from LCA to each node.

### 33. **Kth Ancestor of a Node in a Binary Tree**
   - Use recursion to track ancestors and return the `Kth` one when the target node is found.

### 34. **Find All Duplicate Subtrees in a Binary Tree** (good)
   - Use serialization (or hashing) of subtrees during post-order traversal to detect duplicates.

### 35. **Tree Isomorphism Problem** 
   - Check if two trees can be transformed into each other by flipping subtrees.
   
````java

public class TreeIsomorphism {

    // Function to check if two trees are isomorphic
    public static boolean isIsomorphic(Node n1, Node n2) {
        // Both nodes are null, trees are isomorphic
        if (n1 == null && n2 == null) {
            return true;
        }

        // Exactly one of the nodes is null, trees are not isomorphic
        if (n1 == null || n2 == null) {
            return false;
        }

        // Data of the nodes doesn't match, trees are not isomorphic
        if (n1.data != n2.data) {
            return false;
        }

        // Two cases:
        // Case 1: Subtrees are not flipped
        // Case 2: Subtrees are flipped
        return (isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right, n2.right))
                || (isIsomorphic(n1.left, n2.right) && isIsomorphic(n1.right, n2.left));
    }
````


### **1. Get Sum of Nodes at a Given Level**
```java
int getSumOfNodesAtLevel(Node<T> root, int level) {
    if (root == null) return 0;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    int currentLevel = 0;
    int sum = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        sum = 0;  // Reset sum for the current level
        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            if (currentLevel == level) sum += node.data;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        if (currentLevel == level) break;
        currentLevel++;
    }
    return sum;
}
```

---

### **2. Print All Nodes at a Given Level**
```java
void printAllNodesGivenLevel(Node<T> node, int level) {
    if (node == null) return;
    if (level == 0) {
        System.out.print(node.data + " ");
        return;
    }
    printAllNodesGivenLevel(node.left, level - 1);
    printAllNodesGivenLevel(node.right, level - 1);
}
```

---

### **3. Level Order Traversal from Bottom to Top**
```java
void traverseFromBottom2Top(Node<T> root) {
    if (root == null) return;

    Queue<Node<T>> q = new LinkedList<>();
    Stack<List<Integer>> stack = new Stack<>();
    q.add(root);

    while (!q.isEmpty()) {
        int size = q.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            level.add(node.data);
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        stack.push(level);
    }

    while (!stack.isEmpty()) {
        for (int val : stack.pop()) System.out.print(val + " ");
        System.out.println();
    }
}
```

---

### **4. Find the Deepest Node**
```java
Node<T> getDeepestNode(Node<T> root) {
    if (root == null) return null;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    Node<T> deepestNode = null;

    while (!q.isEmpty()) {
        deepestNode = q.poll();
        if (deepestNode.left != null) q.add(deepestNode.left);
        if (deepestNode.right != null) q.add(deepestNode.right);
    }
    return deepestNode;
}
```

---

### **5. DFS Traversal Without Recursion**
```java
void dfsWithoutRecursion(Node<T> root) {
    if (root == null) return;

    Stack<Node<T>> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        Node<T> node = stack.pop();
        System.out.print(node.data + " ");
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
    }
}
```

---

### **6. BFS Traversal Without Recursion**
```java
void bfsWithoutRecursion(Node<T> root) {
    if (root == null) return;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
        Node<T> node = q.poll();
        System.out.print(node.data + " ");
        if (node.left != null) q.add(node.left);
        if (node.right != null) q.add(node.right);
    }
}
```

---

### **7. Get Height of Tree Without Recursion**
```java
int getHeight_m2(Node<T> root) {
    if (root == null) return 0;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    int height = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        height++;
    }
    return height;
}
```

---

### **8. Check if Two Trees Are Structurally Same**
```java
boolean areStructurallySameTree(Node<T> root1, Node<T> root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;
    return areStructurallySameTree(root1.left, root2.left) &&
           areStructurallySameTree(root1.right, root2.right);
}
```

---

### **9. Find Diameter of Binary Tree**
```java
int diameterOfBinaryTree(Node<T> root) {
    AtomicInteger diameter = new AtomicInteger(0);

    int height(Node<T> node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        diameter.set(Math.max(diameter.get(), leftHeight + rightHeight));
        return Math.max(leftHeight, rightHeight) + 1;
    }

    height(root);
    return diameter.get();
}
```

---

### **10.  give a algorithm for finding the level that has the maximum sum in binary tree**  
```java
int findLevelWithMaxSum(Node<T> root) {
    if (root == null) return -1;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    int maxSum = 0, maxLevel = 0, currentLevel = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        int levelSum = 0;

        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            levelSum += node.data;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }

        if (levelSum > maxSum) {
            maxSum = levelSum;
            maxLevel = currentLevel;
        }
        currentLevel++;
    }
    return maxLevel;
}
```

---

### **11. given a binary tree print all its root-to-leaf paths** 
```java
void pathsFinder(Node<T> root) {
    List<Integer> path = new ArrayList<>();
    dfs(root, path);

    void dfs(Node<T> node, List<Integer> path) {
        if (node == null) return;

        path.add(node.data);
        if (node.left == null && node.right == null) {
            System.out.println(path);
        } else {
            dfs(node.left, path);
            dfs(node.right, path);
        }
        path.remove(path.size() - 1);
    }
}
```

---


### **1. Sum of Root-to-Leaf Numbers**

given a binary tree containing digits from 0-9 only , each root-to-leaf path could represent a number. an example 
is the root-to-leaf path 1->2->3 which represents the number 123, find the total sum of all root-to-leaf numbers

```java
int sumRoot2LeafNumbers(Node<T> root) {
    return dfs(root, 0);

    int dfs(Node<T> node, int currentSum) {
        if (node == null) return 0;

        currentSum = currentSum * 10 + node.data;

        // If leaf node, return the computed number
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Recursive calls for left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
```

---

### **2. Maximum Path Sum**

given a binary tree . find the max path sum. the path may start and end at any node in the tree.
```java
int maxPathSum(Node<T> root) {
    AtomicInteger maxSum = new AtomicInteger(Integer.MIN_VALUE);

    int dfs(Node<T> node) {
        if (node == null) return 0;

        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);

        // Update maxSum with the path passing through the current node
        maxSum.set(Math.max(maxSum.get(), left + right + node.data));

        // Return the maximum path sum including the current node
        return Math.max(left, right) + node.data;
    }

    dfs(root);
    return maxSum.get();
}
```

---

### **3. Check Existence of Path with Given Sum**
give a algorithm for checking the existence of path with given sum. that means , given a sum, check whether there exists a path from root to any of the nodes.
```java
boolean hasPathSum(Node<T> root, int sum) {
    if (root == null) return false;

    // Check if we have reached a leaf node with the exact sum
    if (root.left == null && root.right == null) {
        return sum == root.data;
    }

    // Recur for left and right subtrees with the reduced sum
    return hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data);
}
```

---

### **4. Convert Tree to Its Mirror**
   - **Recursive Approach:** Swap the left and right children of each node recursively.
   - **Iterative Approach:** Use level order traversal and swap children at each level.

```java
void mirrorOfBinaryTree(Node<T> root) {
    if (root == null) return;

    // Swap the left and right children
    Node<T> temp = root.left;
    root.left = root.right;
    root.right = temp;

    // Recur for left and right subtrees
    mirrorOfBinaryTree(root.left);
    mirrorOfBinaryTree(root.right);
}
```

---

### **5. Check If Two Trees Are Mirrors**
```java
boolean areMirrors(Node<T> root1, Node<T> root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;

    // Check if the data matches and the left subtree of one is the mirror of the right subtree of the other
    return root1.data.equals(root2.data) &&
           areMirrors(root1.left, root2.right) &&
           areMirrors(root1.right, root2.left);
}
```

---

### **6. Print All Ancestors of a Node**
 give an algorithm for printing all the ancestors of a node in a binary tree

```java
boolean printAllAncestors(Node<T> root, Node<T> target) {
    if (root == null) return false;

    // If the target node is found in either left or right subtree
    if (root.left == target || root.right == target ||
        printAllAncestors(root.left, target) ||
        printAllAncestors(root.right, target)) {
        System.out.print(root.data + " ");
        return true;
    }

    return false;
}
```

---

### **7. Find Vertical Sum of a Binary Tree**
Give an algorithm for finding thr vertical sum of a binary tree
```java
List<Integer> getVerticalSum(Node<T> root) {
    TreeMap<Integer, Integer> columnSumMap = new TreeMap<>();

    dfs(root, 0, columnSumMap);

    return new ArrayList<>(columnSumMap.values());
}

void dfs(Node<T> node, int column, TreeMap<Integer, Integer> columnSumMap) {
    if (node == null) return;

    // Update the sum for the current column
    columnSumMap.put(column, columnSumMap.getOrDefault(column, 0) + node.data);

    // Recur for left and right subtrees
    dfs(node.left, column - 1, columnSumMap);
    dfs(node.right, column + 1, columnSumMap);
}
```

---

### **8. Zigzag Traversal**
   - Use two stacks to alternate between left-to-right and right-to-left traversals at each level.

```java
void zigZagTraversal(Node<T> root) {
    if (root == null) return;

    Queue<Node<T>> queue = new LinkedList<>();
    queue.add(root);
    boolean leftToRight = true;

    while (!queue.isEmpty()) {
        int size = queue.size();
        LinkedList<Integer> level = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            Node<T> node = queue.poll();

            if (leftToRight) {
                level.add(node.data);
            } else {
                level.addFirst(node.data);
            }

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        System.out.println(level);
        leftToRight = !leftToRight;  // Toggle direction
    }
}
```

