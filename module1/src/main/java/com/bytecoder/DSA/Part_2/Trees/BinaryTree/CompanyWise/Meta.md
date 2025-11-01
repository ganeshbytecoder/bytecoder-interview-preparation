## 🎨 Essential Tree Patterns for Meta for last 6 months

## 🟢 Easy Problems

### 1. Invert Binary Tree (LC 226)

**Difficulty:** Easy | **Pattern:** DFS

Swap left and right children recursively.

**Time:** O(n) | **Space:** O(h)

**Approach:** Simple recursive swap. Base case: null node.

---

### 2. Same Tree (LC 100)

**Difficulty:** Easy | **Pattern:** DFS

Check if two trees are structurally identical and have same values.

**Time:** O(n) | **Space:** O(h)

---

### 3. Maximum Depth (LC 104)

**Difficulty:** Easy | **Pattern:** DFS

Find maximum depth (height) of tree.

**Time:** O(n) | **Space:** O(h)

**Formula:** max(depth(left), depth(right)) + 1

---

### 4. Minimum Depth (LC 111)

**Difficulty:** Easy | **Pattern:** BFS, DFS

Find minimum depth to nearest leaf. BFS preferred for early termination.

**Time:** O(n) | **Space:** O(w) BFS, O(h) DFS

---

### 5. Balanced Binary Tree (LC 110)

**Difficulty:** Easy | **Pattern:** DFS, Postorder

Check if tree is height-balanced (heights differ by at most 1).

**Time:** O(n) | **Space:** O(h)

**Trick:** Return -1 to indicate imbalance, propagate up immediately.

---

### 6. Path Sum (LC 112)

**Difficulty:** Easy | **Pattern:** DFS, Path Sum

Check if root-to-leaf path exists with given sum.

**Time:** O(n) | **Space:** O(h)

---

### 7. Diameter of Binary Tree (LC 543)

**Difficulty:** Easy | **Pattern:** DFS, Postorder

Longest path between any two nodes (not necessarily through root).

**Time:** O(n) | **Space:** O(h)

**Formula:** At each node, diameter = leftHeight + rightHeight. Track global max.

---

### 8. Merge Two Binary Trees (LC 617)

**Difficulty:** Easy | **Pattern:** DFS

Merge two trees by summing overlapping nodes.

**Time:** O(n) | **Space:** O(h)

---

### 9. Binary Tree Paths (LC 257)

**Difficulty:** Easy | **Pattern:** DFS, Backtracking

Return all root-to-leaf paths as strings.

**Time:** O(n) | **Space:** O(h)

---

### 10. Sum of Left Leaves (LC 404)

**Difficulty:** Easy | **Pattern:** DFS

Sum all left leaves (leaves that are left children).

**Time:** O(n) | **Space:** O(h)

---

### 11. Average of Levels (LC 637)

**Difficulty:** Easy | **Pattern:** BFS

Calculate average value at each level.

**Time:** O(n) | **Space:** O(w)

---

### 12. Range Sum of BST (LC 938)

**Difficulty:** Easy | **Pattern:** BST, DFS

Sum all nodes with values in range [low, high].

**Time:** O(n) | **Space:** O(h)

**Optimizati****on:** Prune branches using BST property.

---

### 13. Closest BST Value (LC 270)

**Difficulty:** Easy | **Pattern:** BST

Find value closest to given target in BST.

**Time:** O(h) | **Space:** O(1)

---

### 14. Sum Root to Leaf Binary Numbers (LC 1022)

**Difficulty:** Easy | **Pattern:** DFS

Treat root-to-leaf paths as binary numbers, sum them.

**Time:** O(n) | **Space:** O(h)

**Formula:** val = (val << 1) | node.val

---

### 15. Cousins in Binary Tree (LC 993)

**Difficulty:** Easy | **Pattern:** BFS

Check if two nodes are cousins (same depth, different parents).

**Time:** O(n) | **Space:** O(w)

---

### 16. Inorder Traversal (LC 94)

**Difficulty:** Easy | **Pattern:** Traversal, Stack

Iterative inorder traversal using stack.

**Time:** O(n) | **Space:** O(h)

---

## 🟡 Medium Problems

### 1. Validate Binary Search Tree (LC 98)

**Difficulty:** Medium | **Pattern:** BST, DFS

Check if tree is valid BST. Each node must be within valid range.

**Time:** O(n) | **Space:** O(h)

**Two Approaches:** 1) Inorder must be strictly increasing. 2) Pass (low, high) bounds down recursively.

---

### 2. Level Order Traversal (LC 102)

**Difficulty:** Medium | **Pattern:** BFS

Return level-by-level traversal as list of lists.

**Time:** O(n) | **Space:** O(w)

---

### 3. Zigzag Level Order (LC 103)

**Difficulty:** Medium | **Pattern:** BFS

Level order but alternate left-to-right and right-to-left.

**Time:** O(n) | **Space:** O(w)

**Trick:** Use flag to reverse every other level.

---

### 4. Construct from Preorder & Inorder (LC 105)

**Difficulty:** Medium | **Pattern:** Tree Construction, DFS

Build tree from preorder and inorder traversals.

**Time:** O(n) | **Space:** O(n)

**Key:** Preorder gives root, inorder splits left/right. Use hashmap for O(1) position lookup.

---

### 5. Path Sum II (LC 113)

**Difficulty:** Medium | **Pattern:** DFS, Backtracking

Find all root-to-leaf paths with given sum.

**Time:** O(n) | **Space:** O(h)

## 6. Flatten Tree to Linked List (LC 114)

**Difficulty:** Medium | **Pattern:** DFS, Postorder

Flatten tree to linked list in preorder.

**Time:** O(n) | **Space:** O(h)

**Trick:** Process in reverse preorder, maintain prev pointer.

---

### 7. Populating Next Pointers (Perfect BT) (LC 116)

**Difficulty:** Medium | **Pattern:** BFS, Level Linking

Connect each node to its next right node in perfect binary tree.

**Time:** O(n) | **Space:** O(1)

**O(1) Space:** Use already established next pointers to traverse level.

---

### 8. Populating Next Pointers II (LC 117)

**Difficulty:** Medium | **Pattern:** BFS, Level Linking

Connect next pointers in any binary tree (not necessarily perfect).

**Time:** O(n) | **Space:** O(1)

---

### 9. Sum Root to Leaf Numbers (LC 129)

**Difficulty:** Medium | **Pattern:** DFS

Sum all numbers formed by root-to-leaf paths.

**Time:** O(n) | **Space:** O(h)

**Formula:** num = num * 10 + node.val

---

### 10. BST Iterator (LC 173)

**Difficulty:** Medium | **Pattern:** BST, Stack

Implement iterator for BST with O(h) space.

**Time:** O(1) average per next() | **Space:** O(h)

**Approach:** Controlled inorder using stack. Push all left nodes.

---

### 11. Right Side View (LC 199)

**Difficulty:** Medium | **Pattern:** BFS, Tree Views

Return rightmost node at each level.

**Time:** O(n) | **Space:** O(w)

---

### 12. Kth Smallest in BST (LC 230)

**Difficulty:** Medium | **Pattern:** BST, Inorder

Find kth smallest element in BST.

**Time:** O(h + k) | **Space:** O(h)

**Optimization:** Iterative inorder, stop at k. Or Morris for O(1) space.

---

### 13. LCA of BST (LC 235)

**Difficulty:** Medium | **Pattern:** BST, LCA

Find lowest common ancestor in BST.

**Time:** O(h) | **Space:** O(1) iterative

---

### 14. LCA of Binary Tree (LC 236)

**Difficulty:** Medium | **Pattern:** LCA, DFS

Find LCA in general binary tree.

**Time:** O(n) | **Space:** O(h)

---

### 15. Binary Tree Longest Consecutive (LC 298)

**Difficulty:** Medium | **Pattern:** DFS

Find longest consecutive sequence path.

**Time:** O(n) | **Space:** O(h)

---

### 16. Vertical Order Traversal (LC 314)

**Difficulty:** Medium | **Pattern:** BFS, Tree Views

Group nodes by column, maintain top-to-bottom order.

**Time:** O(n) | **Space:** O(n)

---

### 17. Largest BST Subtree (LC 333)

**Difficulty:** Medium | **Pattern:** Subtree Properties, Postorder

Find size of largest BST subtree.

**Time:** O(n) | **Space:** O(h)

**Return:** (isBST, size, min, max) tuple from postorder.

---

### 18. Convert BST to Doubly Linked List (LC 426)

**Difficulty:** Medium | **Pattern:** BST, Inorder

Convert BST to sorted circular doubly linked list.

**Time:** O(n) | **Space:** O(h)

---

### 19. Path Sum III (LC 437)

**Difficulty:** Medium | **Pattern:** Path Sum, Prefix Sum

Count paths with given sum (not necessarily root-to-leaf).

**Time:** O(n) | **Space:** O(n)

**Key:** Use prefix sum hashmap to find paths ending at current node.

---

### 20. Construct from String (LC 536)

**Difficulty:** Medium | **Pattern:** Tree Construction, Stack

Construct tree from string representation.

**Time:** O(n) | **Space:** O(h)

---

### 21. Boundary of Binary Tree (LC 545)

**Difficulty:** Medium | **Pattern:** Tree Views, DFS

Return boundary in counter-clockwise order.

**Time:** O(n) | **Space:** O(h)

**Three parts:** Left boundary + Leaves + Reversed right boundary

---

### 22. Maximum Width (LC 662)

**Difficulty:** Medium | **Pattern:** BFS

Find maximum width using heap indexing (2*i, 2*i+1).

**Time:** O(n) | **Space:** O(w)

---

### 23. All Nodes Distance K (LC 863)

**Difficulty:** Medium | **Pattern:** Parent Pointer, BFS

Find all nodes at distance K from target.

**Time:** O(n) | **Space:** O(n)

---

### 24. Smallest Subtree with Deepest Nodes (LC 865)

**Difficulty:** Medium | **Pattern:** DFS, Postorder

Find LCA of deepest leaves.

**Time:** O(n) | **Space:** O(h)

---

### 25. Check Completeness (LC 958)

**Difficulty:** Medium | **Pattern:** BFS

Check if tree is complete (all levels filled except possibly last).

**Time:** O(n) | **Space:** O(w)

**Trick:** Once null seen in BFS, no more non-null nodes allowed.

---

### 26. Construct BST from Preorder (LC 1008)

**Difficulty:** Medium | **Pattern:** BST, Tree Construction

Build BST from preorder traversal only.

**Time:** O(n) | **Space:** O(h)

---

### 27. LCA of Deepest Leaves (LC 1123)

**Difficulty:** Medium | **Pattern:** DFS

Same as LC 865.

**Time:** O(n) | **Space:** O(h)

---

### 28. Maximum Level Sum (LC 1161)

**Difficulty:** Medium | **Pattern:** BFS

Find level with maximum sum.

**Time:** O(n) | **Space:** O(w)

---

### 29. All Elements in Two BSTs (LC 1305)

**Difficulty:** Medium | **Pattern:** BST, Merge

Merge two BSTs into sorted array.

**Time:** O(n1 + n2) | **Space:** O(h1 + h2)

---

### 30. Validate Binary Tree Nodes (LC 1361)

**Difficulty:** Medium | **Pattern:** Graph, BFS

Check if given edges form valid binary tree.

**Time:** O(n) | **Space:** O(n)

---

### 31. Minimum Time to Collect Apples (LC 1443)

**Difficulty:** Medium | **Pattern:** DFS, Tree

Find minimum time to collect all apples in tree.

**Time:** O(n) | **Space:** O(n)

---

### 32. Clone Tree with Random Pointer (LC 1485)

**Difficulty:** Medium | **Pattern:** DFS, HashMap

Deep copy tree with random pointers.

**Time:** O(n) | **Space:** O(n)

---

### 33. Diameter of N-Ary Tree (LC 1522)

**Difficulty:** Medium | **Pattern:** DFS

Find diameter of N-ary tree.

**Time:** O(n) | **Space:** O(h)

---

### 34. LCA with Parent Pointers (LC 1650)

**Difficulty:** Medium | **Pattern:** LCA, Two Pointers

Find LCA when nodes have parent pointers.

**Time:** O(h) | **Space:** O(1)

**Trick:** Like finding intersection of linked lists.

---

### 35. Count Nodes Equal to Average (LC 2265)

**Difficulty:** Medium | **Pattern:** Subtree Properties

Count nodes where value equals average of subtree.

**Time:** O(n) | **Space:** O(h)

---

## Quick Reference Table

| Pattern            | Time | Space | Key Technique                 |
| ------------------ | ---- | ----- | ----------------------------- |
| DFS Recursive      | O(n) | O(h)  | Preorder/Inorder/Postorder    |
| BFS Level Order    | O(n) | O(w)  | Queue, process level by level |
| BST Search         | O(h) | O(1)  | Use ordering property         |
| Path Sum           | O(n) | O(n)  | Prefix sum + hashmap          |
| LCA                | O(n) | O(h)  | Postorder, return when found  |
| Morris Traversal   | O(n) | O(1)  | Threading technique           |
| Tree Construction  | O(n) | O(n)  | Hashmap for O(1) lookup       |
| Subtree Properties | O(n) | O(h)  | Postorder with tuple return   |

---

## Interview Tips

1. **Always clarify:** Is it a BST or general binary tree?
2. **Ask about:** Balanced? Complete? Perfect? Node count?
3. **Consider:** Recursive vs Iterative (stack overflow?)
4. **Space optimization:** Can Morris traversal help?
5. **BST problems:** Think inorder = sorted
6. **Path problems:** Consider prefix sum
7. **Level problems:** BFS is your friend
8. **Subtree problems:** Postorder to get child info first

---

**Good luck with your FAANG interviews! 🚀**
