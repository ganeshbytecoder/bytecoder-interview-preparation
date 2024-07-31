package com.bytecoder.DSA.Trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
        BinaryTree binaryTree;

    @BeforeEach
    void setUp() {
//                    10
//                  6     12
//                9  30 11  10
//        insert();
        binaryTree = new BinaryTree<Integer>();


    }

    @Test
    void getRoot() {
        System.out.println(binaryTree.getRoot());
    }

    @Test
    void isEmpty() {
    }

    @Test
    void insert() {
        binaryTree.insert(1);
        binaryTree.insert(2);
        binaryTree.insert(3);
        binaryTree.insert(4);
        binaryTree.insert(5);
        binaryTree.insert(6);
        binaryTree.insert(7);
//        System.out.println(binaryTree.getRoot().getData());

    }

    @Test
    void insert_m2() {
        binaryTree.insert_m2(10);
        binaryTree.insert_m2(6);
        binaryTree.insert_m2(12);
        binaryTree.insert_m2(9);
        binaryTree.insert_m2(30);
        binaryTree.insert_m2(11);
        binaryTree.insert_m2(10);
        System.out.println(binaryTree.getRoot());
    }

    @Test
    void traverse() {

        insert();
        binaryTree.traverse(TraversalType.LEVEL_ORDER);
        binaryTree.traverse(TraversalType.PRE_ORDER);
        binaryTree.traverse(TraversalType.POST_ORDER);
        binaryTree.traverse(TraversalType.IN_ORDER);
    }

    @Test
    void traverse_m2() {
    }

    @Test
    void delete() {
    }

    @Test
    void delete_m2() {
    }

    @Test
    void getMax() {
        insert();
        System.out.println( binaryTree.getMax());
    }

    @Test
    void getMax_m2() {
    }

    @Test
    void getMin() {
    }

    @Test
    void getMin_m2() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void getHeight_m2() {
    }

    @Test
    void getLevel() {
    }

    @Test
    void getLevel_m2() {
    }

    @Test
    void getNodesAtLevel() {
    }

    @Test
    void getNodesAtLevel_m2() {
    }

    @Test
    void searchData() {
    }

    @Test
    void searchData_m2() {
    }
}