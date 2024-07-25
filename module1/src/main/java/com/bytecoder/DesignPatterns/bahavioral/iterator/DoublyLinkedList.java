package com.bytecoder.DesignPatterns.bahavioral.iterator;

import java.util.Iterator;

public class DoublyLinkedList {
    public DoublyNode insertNode(DoublyNode root, int data) {
        if (root == null) {
            root = new DoublyNode(data);
        }
        DoublyNode curr = root;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new DoublyNode(data);
        curr.next.previous = curr;

        return root;
    }

    public Iterator<DoublyNode> getIterator(DoublyNode root){
        return new LinkedListIterator(root);
    }

    public Iterator<DoublyNode> getReverseIterator(DoublyNode root){
        return new ReverseLinkedListIterator(root);
    }

}
class LinkedListIterator implements Iterator<DoublyNode> {
    public DoublyNode root;

    public DoublyNode curr;

    public boolean started;

    LinkedListIterator(DoublyNode root) {
        this.root = root;
        this.curr = root;


    }

    @Override
    public boolean hasNext() {
        if (!started) {
            started = true;
            return curr != null;
        }
        curr = curr.next;
        return curr != null;
    }

    @Override
    public DoublyNode next() {

        return curr;
    }
}

class ReverseLinkedListIterator implements Iterator<DoublyNode> {
    public DoublyNode root;

    public DoublyNode curr;

    public boolean started;

    ReverseLinkedListIterator(DoublyNode root) {
        this.root = root;
        this.curr = root;
        while (curr.next != null){
            curr = curr.next;
        }


    }

    @Override
    public boolean hasNext() {
        if (!started) {
            started = true;
            return curr != null;
        }
        curr = curr.previous;
        return curr != null;
    }

    @Override
    public DoublyNode next() {

        return curr;
    }
}
