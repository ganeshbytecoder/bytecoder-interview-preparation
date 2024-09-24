package com.bytecoder.DSA.Part_1.Array_List;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxFrequency {
    //    creation of streams -> from array, string, file , collection,
//    transformation and filter
//    reduce -> max, min,
//    collect,
//    note util classes and functions-> collections, arrays, comparator,

    /***
     * find max occurred int in give integer array
     * find max occurred character in given string
     */
    public static void getMaxOccurredInt(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }

        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : map.entrySet()
        ) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        Map.Entry<Integer, Long> entry = Collections.max(map.entrySet(), (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        System.out.println(entry.getKey() + " : " + entry.getValue());

        Comparator<Map.Entry<Integer, Long>> comparator = (o1, o2) -> (int) (o1.getValue() - o2.getValue());
        List<Map.Entry<Integer, Long>> list1 = new ArrayList<>(map.entrySet());
        Collections.sort(list1, comparator);
        list1.forEach(x ->
                System.out.println(x.getKey() + " - " + x.getValue())
        );

//        Comparator.comparing((a,b)-> ab);

        System.out.println(Collections.max(list1, (e1, e2) -> e1.getKey().compareTo(e2.getKey())));
//        max digit repeation
        String collect = list.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(collect);
        List<Character> characterList = new ArrayList<>();
        for (Character c : collect.toCharArray()) {
            characterList.add(c);
        }

        Map<Character, Long> map1 = characterList.stream().collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
        map1.entrySet().stream().filter(e -> e.getValue() > 2).forEach(System.out::println);
        Map<Character, Long> map3 = characterList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map3.entrySet().stream().filter(e -> e.getValue() <= 2).forEach(System.out::println);

        Map<Character, List<Character>> map2 = characterList.stream().collect(Collectors.groupingBy(x -> x, Collectors.mapping(x -> x, Collectors.toList())));
        map2.entrySet().stream().forEach(System.out::println);


//        Set<> set = new HashSet<>();
//        set.add((1,2,3) , (2,3,4))
//
//        dictionary
        char c = map3.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        System.out.println("max c  " + c);
    }

    public static void getMaxCharFrequencyInString(String str){
        List<Character> characters = new ArrayList<>();
        for (char c : str.toLowerCase().toCharArray()) {
            characters.add(c);
        }
        Map.Entry<Character, Long> entry =  characters.stream()
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
