package com.bytecoder.DSA.Trees;

import com.bytecoder.DSA.Part_2.Trees.BinarySearchTree;
import com.bytecoder.DSA.Part_2.Trees.TraversalType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class BinarySearchTreeTest {

    BinarySearchTree<Integer> binarySearchTree;

    @BeforeEach
    void setUp() {
//                     10
//                  6      12
//                9   30  11   10
//        insert();
        binarySearchTree = new BinarySearchTree<Integer>();


    }

    @Test
    void getRoot() {
        System.out.println(binarySearchTree.getRoot());
    }

    @Test
    void isEmpty() {

        Assertions.assertTrue(binarySearchTree.isEmpty(), "Implementation is not correct");

    }

    @Test
    void insert() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));
        binarySearchTree.traverse(TraversalType.LEVEL_ORDER);
        Assertions.assertTrue(true, "Implementation is not correct");

    }


    @Test
    void traverse() {
        insert();
        binarySearchTree.traverse(TraversalType.LEVEL_ORDER);
        binarySearchTree.traverse(TraversalType.PRE_ORDER);
        binarySearchTree.traverse(TraversalType.POST_ORDER);
        binarySearchTree.traverse(TraversalType.IN_ORDER);
    }


    @Test
    void delete() {

                    /*
                      1
                  2         3
               4    5   6       7
                     */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));
        binarySearchTree.delete(2);
        Assertions.assertTrue(false, "delete is not true");

        binarySearchTree.traverse(TraversalType.LEVEL_ORDER);

    }


    @Test
    void getMax() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));
        Assertions.assertEquals(Collections.max(list), binarySearchTree.getMax(), "Max is not true");
    }


    @Test
    void getMin() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));
        Assertions.assertEquals(Collections.min(list), binarySearchTree.getMin(), "min is correct.");


    }


    @Test
    void getHeight() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));
        Assertions.assertEquals(3, binarySearchTree.getHeight(), "height is correct.");

    }


    @Test
    void getLevel() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));
        Assertions.assertEquals(1, binarySearchTree.getLevel(2), "level is not as expected");

    }


    @Test
    void getNodesAtLevel() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));

        Assertions.assertEquals(List.of(2, 3), binarySearchTree.getNodesAtLevel(1).stream().map(node -> node.getData()).collect(Collectors.toList()), "lis is not matching");


    }


    @Test
    void searchData() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> binarySearchTree.insert(i));
        Assertions.assertTrue(binarySearchTree.searchData(54), "value is  not found");
    }

}