package com.bytecoder.DSA.Collections;

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
                Comparator.reverseOrder();
                String [] arr = {"arr", "dd"};
                String arrStr = Arrays.toString(arr);
                System.out.println(arrStr);

                List<String> list = Arrays.asList(arr);
//              list.toArray(new T[0]) to convert array
                String[] a = list.toArray(new String[0]);
                HashSet<Integer> set = new HashSet<>();

//                set= Collections.sort(set);
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(1, 10);
                map.put(2, 5);
                map.put(3, 15);
                List<Integer> l = map.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // Fixing the comparator usage
                        .map(Map.Entry::getValue) // Mapping to values
                        .toList();

                List<Integer> x = map.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // Fixing the comparator usage
                        .map(Map.Entry::getValue) // Mapping to values
                        .collect(Collectors.toList());


        }


}
