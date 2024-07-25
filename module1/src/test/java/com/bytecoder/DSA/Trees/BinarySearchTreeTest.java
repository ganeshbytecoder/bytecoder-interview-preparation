package com.bytecoder.DSA.Trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void insertNode() {

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(10);
        binarySearchTree.insert(6);
        binarySearchTree.insert(12);
        binarySearchTree.insert(9);
        binarySearchTree.insert(30);

        binarySearchTree.traverse(TraversalType.LEVEL_ORDER);
    }
}