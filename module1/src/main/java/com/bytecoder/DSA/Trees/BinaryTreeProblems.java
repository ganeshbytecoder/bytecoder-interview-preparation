package com.bytecoder.DSA.Trees;

public interface BinaryTreeProblems<T extends Comparable<T>> {


    int getSumOfNodesAtLevel(Node<T> root);

    int getSumOfNodesAtLevel_m2(Node<T> root);


//    get level order traverse from bottom to top
    void traverseFromBottom2Top(Node<T> root);

//    Give an algorithm for finding the deepest node of the binary tree
    Node<T> getDeepestNode(Node<T> root);


//    traverse using DFS without recursion
//    traverse using BFS without recursion

//  11. int getHeight or getDepth of tree without recursion (use q.push[node,level])
    int getHeight_m2();

//  17.  given teo binary tree ,return true if they are structurally same
    boolean areStructurallySameTree(Node<T> root1, Node<T> root2);


//  18.   give an algorithm for finding the diameter of the binary tree.
    int diameterOfBinaryTree(Node<T> node);

//  19*.  give a algorithm for finding the level that has the maximum sum in binary tree
    int findLevelWithMaxSum(Node<T> root);

//  20* given a binary tree print all its root-to-leaf paths
    void pathsFinder();


//  21* given a binary tree containing digits from 0-9 only , each root-to-leaf path could represent a number. an example
//    is the root-to-leaf path 1->2->3 which represents the number 123, find the total sum of all root-to-leaf numbers
    int sumRoot2LeafNumbers(Node<T> root);

//  22* given a binary tree . find the max path sum. the path may start and end at any node in the tree.

//  23* give a algorithm for checking the existence of path with given sum. that means , given a sum, check whether there exists a path from root to any of the nodes.

//  26* give an algorithm for converting a tree to its mirror.

    void mirrorOfBinaryTree(Node<T> root);

//  27* given two trees, give a algorithm for checking whether they are mirrors of each other ?.
    boolean areMirrors(Node<T> root1 , Node<T> root2);

//    31* give an algorithm for printing all the ancestors of a node in a binary tree,

    void printAllAncestors(Node<T> root);

}
