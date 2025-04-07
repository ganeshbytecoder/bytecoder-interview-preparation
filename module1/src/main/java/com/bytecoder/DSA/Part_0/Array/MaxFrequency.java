package com.bytecoder.DSA.Part_0.Array;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxFrequency {

    /***
     * find max occurred int in give integer array
     * find max occurred character in given string
     */
    public static void getMaxOccurredInt(int[] array) {
        List<Integer> list = Arrays.stream(array).boxed().toList();
        HashMap<Integer, Integer> map = new HashMap<>();

        list.forEach(value -> map.put(value, map.getOrDefault(value,0)+1));

        map.entrySet().forEach((entry)-> System.out.println(entry.getKey() + " : " + entry.getValue()) );

        map.forEach((key, value) -> System.out.println(key + " : " + value));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }



    //  Comparator.comparing((a,b)-> ab);
        Map.Entry<Integer, Integer> entry = Collections.max(map.entrySet(), (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        System.out.println(entry.getKey() + " : " + entry.getValue());

        Comparator<Map.Entry<Integer, Integer>> comparator = (o1, o2) ->  (o1.getValue() - o2.getValue());


        List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>();
        map.entrySet().forEach((a)-> list1.add(a));

        Collections.sort(list1, comparator);
//        this will work since it's set because sets are unordered
//        Collections.sort(map.entrySet(), comparator);

        list1.forEach(x ->
                System.out.println(x.getKey() + " - " + x.getValue())
        );

        System.out.println(Collections.max(list1, (e1, e2) -> e1.getKey().compareTo(e2.getKey())));
        System.out.println(Collections.max(map.entrySet(), (e1, e2) -> e1.getKey().compareTo(e2.getKey())));


//      max digit repeation
        String collect = List.of(1,2,3,4).stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println("collected " + collect);


        List<Character> characterList = new ArrayList<>();
        for (Character c : collect.toCharArray()) {
            characterList.add(c);
        }


        String s = "absbcd";
        Map<Character, Integer> map1 = new HashMap<>();

        for (Character character : s.toCharArray()){
            map1.put(character, map1.getOrDefault(character,0)+1);
        }

        char c = map1.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        System.out.println("max b  " + c);
    }

    public static void getMaxCharFrequencyInString(String str) {
        List<Character> characters = new ArrayList<>();
        for (char c : str.toLowerCase().toCharArray()) {
            characters.add(c);
        }
        Map.Entry<Character, Long> entry = characters.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get();
        System.out.println("max frequency of " + entry.getKey() + " is " + entry.getValue());
    }


    public static void main(String[] args) {



        getMaxOccurredInt(new int[]{1, 2, 3, 4, 133, 4, 2});


        getMaxCharFrequencyInString("ganeshSinghChoudhary");


    }
}
