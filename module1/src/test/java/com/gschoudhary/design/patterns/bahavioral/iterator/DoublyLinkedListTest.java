package com.gschoudhary.design.patterns.bahavioral.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.bahavioral.iterator.DoublyLinkedList;
import com.bytecoder.DesignPatterns.bahavioral.iterator.DoublyNode;

import java.util.Iterator;

class DoublyLinkedListTest {
    DoublyNode root;
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

    @BeforeEach
    void setUp() {
        root = new DoublyNode(1);
        doublyLinkedList.insertNode(root, 2);
        doublyLinkedList.insertNode(root, 3);
        doublyLinkedList.insertNode(root, 4);
        doublyLinkedList.insertNode(root, 5);

    }



    @Test
    void getIterator() {
      Iterator<DoublyNode> iterator = doublyLinkedList.getIterator(root);
      while (iterator.hasNext()){
          System.out.println(iterator.next().getData());
      }
    }

    @Test
    void getReverseIterator() {
      Iterator<DoublyNode> iterator = doublyLinkedList.getReverseIterator(root);
      while (iterator.hasNext()){
          System.out.println(iterator.next().getData());
      }
    }
}