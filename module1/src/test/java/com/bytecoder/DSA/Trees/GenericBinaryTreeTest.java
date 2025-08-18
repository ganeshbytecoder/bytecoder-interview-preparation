package com.bytecoder.DSA.Trees;

import com.bytecoder.DSA.Part_4.Trees.BinaryTree.GenericBinaryTree;
import com.bytecoder.DSA.Part_4.Trees.TraversalType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class GenericBinaryTreeTest {
    GenericBinaryTree<Integer> genericBinaryTree;

    @BeforeEach
    void setUp() {
//                     10
//                  6      12
//                9   30  11   10
//        insert();
        genericBinaryTree = new GenericBinaryTree<>();


    }

    @Test
    void getRoot() {
        System.out.println(genericBinaryTree.getRoot());
    }

    @Test
    void isEmpty() {

        Assertions.assertTrue(genericBinaryTree.isEmpty(), "Implementation is not correct");

    }

    @Test
    void insert() {
        genericBinaryTree.insert(1);
        genericBinaryTree.insert(2);
        genericBinaryTree.insert(3);
        genericBinaryTree.insert(4);
        genericBinaryTree.insert(5);
        genericBinaryTree.insert(6);
        genericBinaryTree.insert(7);
        genericBinaryTree.traverse(TraversalType.LEVEL_ORDER);
        genericBinaryTree.traverse(TraversalType.LEVEL_ORDER);
        Assertions.assertTrue(true, "Implementation is not correct");

    }


    @Test
    void traverse() {
        insert();
        genericBinaryTree.traverse(TraversalType.LEVEL_ORDER);
        genericBinaryTree.traverse(TraversalType.PRE_ORDER);
        genericBinaryTree.traverse(TraversalType.POST_ORDER);
        genericBinaryTree.traverse(TraversalType.IN_ORDER);
    }


    @Test
    void delete() {

                    /*
                      1
                  2         3
               4    5   6       7
                     */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> genericBinaryTree.insert(i));
        genericBinaryTree.delete(2);
        Assertions.assertTrue(false, "delete is not true");

        genericBinaryTree.traverse(TraversalType.LEVEL_ORDER);

    }


    @Test
    void getMax() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> genericBinaryTree.insert(i));
        Assertions.assertEquals(Collections.max(list), genericBinaryTree.getMax(), "Max is not true");
    }


    @Test
    void getMin() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> genericBinaryTree.insert(i));
        Assertions.assertEquals(Collections.min(list), genericBinaryTree.getMin(), "min is correct.");


    }


    @Test
    void getHeight() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> genericBinaryTree.insert(i));
        Assertions.assertEquals(3, genericBinaryTree.getHeight(), "height is correct.");

    }


    @Test
    void getLevel() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> genericBinaryTree.insert(i));
        Assertions.assertEquals(1, genericBinaryTree.getLevel(2), "level is not as expected");

    }


    @Test
    void getNodesAtLevel() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> genericBinaryTree.insert(i));

        Assertions.assertEquals(List.of(2,3), genericBinaryTree.getNodesAtLevel(1).stream().map(node -> node.getData()).collect(Collectors.toList()), "lis is not matching");


    }


    @Test
    void searchData() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 54, 5, 65);
        list.forEach(i -> genericBinaryTree.insert(i));
        Assertions.assertTrue(genericBinaryTree.searchData(54), "value is  not found");
    }


}