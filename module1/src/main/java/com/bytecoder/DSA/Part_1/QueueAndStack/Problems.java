package com.bytecoder.DSA.Part_1.QueueAndStack;

import java.util.Stack;

public class Problems {

    public static int reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int temp = stack.pop();
        int ans  = reverseStack(stack);
        stack.add(temp);
        return ans;
    }


    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        stack.add(10);
        stack.add(9);
        stack.add(8);
        stack.add(7);
    }
}
