package com.bytecoder.DSA.Part_2.Trees;

import java.util.List;


//* finding the size of the tree
//* finding the height of the tree
//* level of node
//* Create – create a tree in the data structure.
//* Insert − Inserts data in a tree.

public interface Tree<T extends Comparable<T>> {

    Node<T> getRoot();

    boolean isEmpty();

    //  using DFS and BFS
    Tree<T> insert(T data);

    //  using DFS and BFS
    void traverse(TraversalType traversalType);

    //    using DFS and BFS
    int size();

    //  using DFS and BFS
    int getHeight();

    //  using DFS and BFS
    int getLevel(T data);

    //  using DFS and BFS
    int getMax();

    //  using DFS and BFS
    int getMin();

    //  using DFS and BFS
    List<Node<T>> getNodesAtLevel(int level);

    //  using DFS and BFS
    boolean searchData(T data);

    //  using DFS and BFS
    void delete(T data);


}
