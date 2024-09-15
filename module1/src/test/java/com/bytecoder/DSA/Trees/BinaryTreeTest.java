package com.bytecoder.DSA.Trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BinaryTreeTest {
    BinaryTree<Integer> binaryTree;

    @BeforeEach
    void setUp() {
//                    10
//                  6     12
//                9  30 11  10
//        insert();
        binaryTree = new BinaryTree<>();


    }

    @Test
    void getRoot() {
        System.out.println(binaryTree.getRoot());
    }

    @Test
    void isEmpty() {

        Assertions.assertTrue(binaryTree.isEmpty(), "Implementation is not correct");

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
        binaryTree.traverse(TraversalType.LEVEL_ORDER);
        binaryTree.traverse(TraversalType.LEVEL_ORDER);
        Assertions.assertTrue(true, "Implementation is not correct");

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
    void delete() {
        binaryTree.insert(1);
        binaryTree.insert(2);
        binaryTree.insert(3);
        binaryTree.insert(4);
        binaryTree.insert(5);
        binaryTree.insert(6);
        binaryTree.insert(7);

        binaryTree.delete(5);
                    /*
                      1
                  2         3
               4    5   6       7
                     */
        binaryTree.traverse(TraversalType.LEVEL_ORDER);

    }


    @Test
    void getMax() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binaryTree.insert(i));
        Assertions.assertEquals(Collections.max(list), binaryTree.getMax(), "Max is not true");
    }


    @Test
    void getMin() {

    }


    @Test
    void getHeight() {
    }


    @Test
    void getLevel() {
    }


    @Test
    void getNodesAtLevel() {
    }


    @Test
    void searchData() {
    }


}