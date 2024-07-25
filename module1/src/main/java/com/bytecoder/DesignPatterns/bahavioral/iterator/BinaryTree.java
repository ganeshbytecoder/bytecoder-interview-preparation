package com.bytecoder.DesignPatterns.bahavioral.iterator;

import java.util.Iterator;

public class BinaryTree  {

    public void insert(Node root, int data){
        if(root == null){
            root= new Node(data);
        }
        if(root.data >= data){
            insert(root.left, data);
        }else {
            insert(root.right, data);
        }
    }




}

class BinaryTreeIterator implements Iterator<Node>{
    Node root, curr;
    boolean started;

    BinaryTreeIterator(Node root){
        this.root = root;
        this.curr= root;
        while (curr.left != null){
            curr= curr.left;
        }
    }

    @Override
    public boolean hasNext() {
        if(!started){
            return curr != null;
        }
        if(curr.right != null){
            curr = curr.right;
            while (curr.left != null){
                curr = curr.left;
            }
            return true;

        }else {
            curr = curr;

        }
        return false;
    }

    @Override
    public Node next() {
        return curr;
    }
}
