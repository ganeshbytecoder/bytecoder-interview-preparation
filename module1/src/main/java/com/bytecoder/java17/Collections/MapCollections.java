package com.bytecoder.java17.Collections;

import java.util.*;
import java.util.stream.Collectors;

public class MapCollections {
//        Queue<Integer> queue = new LinkedList<>()
//    List.of(1,2,3)
//
        PriorityQueue priorityQueue = new PriorityQueue<>();

        public static void main(String[] args) {
                String name = "Alice";
                int age = 25;
                String message = String.format("Hello, my name is %s and I am %d years old.", name, age);
                System.out.println(message);

                String [] arr = {"arr", "dd"};
                String arrStr = Arrays.toString(arr);
                System.out.println(arrStr);

                List<String> list = Arrays.asList(arr);
//              list.toArray(new T[0]) to convert array
                String[] a = list.toArray(new String[0]);

        }


}
