# Tree -
a tree is a data structure similar to a linked list but instead of each node pointing simply to the next node in a linear fashion, each node
points to a number of nodes. 

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

* **Level / depth of a node:** The count of edges on the path from the root node to that node. The root node has level 0.
*  **Height of a node :** is the length of the path from that node to the deepest node.
* **Height of tree :** is the max height among all the nodes

## Type of binary tree
1. **full binary tree:** if each node has exactly two children and all leaf nodes are at the same level
2. **balanced binary tree:**
3. **complete binary tree :**
4. **perfect binary tree:** 


## Operations on binary tree
* Create – create a tree in the data structure.
* Insert − Inserts data in a tree.
* Search − Searches specific data in a tree to check whether it is present or not.
* Traversal:
  * Depth-First-Search Traversal
    * In-order
    * pre-order
    * post-order
  * Breadth-First-Search Traversal
    * level order 
### note: inoreder with any other combination can form a tree

| cc  |   |   |   |   |
|-----|---|---|---|---|
| efe |   |   |   |   |
|     |   |   |   |   |
|     |   |   |   |   |
|     |   |   |   |   |
|     |   |   |   |   |
