package com.bytecoder.java17.Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxFrequency {
    //  creation of streams -> from array, string, file , collection,
//    transformation and filter
//    reduce -> max, min,
//    collect is used to collect elements in list,set, map, ect
//    note util classes and functions-> collections, Arrays, Comparator,
    public static void getMaxOccurredInt(Integer[] array) {


        Map<Integer, Long> map = Arrays.stream(array).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);

        Map.Entry<Integer, Long> maxEntry = Collections.max(map.entrySet(), (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        System.out.println(maxEntry);
        Map.Entry<Integer, Long> minEntry = Collections.min(map.entrySet(), Comparator.comparing(Map.Entry::getValue));
        System.out.println(minEntry);

        Comparator<Map.Entry<Integer, Long>> comparator = (o1, o2) -> (int) (o1.getValue() - o2.getValue());
        List<Map.Entry<Integer, Long>> list1 = new ArrayList<>(map.entrySet());
        Collections.sort(list1, comparator);
        list1.forEach(x ->
                System.out.println(x.getKey() + " - " + x.getValue())
        );


// converting all the digits in string then join them
        String arrayString = Arrays.stream(array).map(String::valueOf).collect(Collectors.joining());
        System.out.println(arrayString);
        List<Character> characterList = new ArrayList<>();
        for (Character c : arrayString.toCharArray()) {
            characterList.add(c);
        }
// calculating character frequency  "123413342"
        Map<Character, Long> map3 = characterList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map3);
//{1=2, 2=2, 3=3, 4=2}
        Map<Character, List<Character>> map2 = characterList.stream().collect(Collectors.groupingBy(x -> x, Collectors.mapping(x -> x, Collectors.toList())));

        System.out.println(map2);
//{1=[1, 1], 2=[2, 2], 3=[3, 3, 3], 4=[4, 4]}
        char c = map3.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        System.out.println("max occurrence of char  " + c);
//max occurrence of char  3
        System.out.println(map2.entrySet().stream().collect(Collectors.counting()));


    }

    public static void main(String[] args) {
//        getMaxOccurredInt(new Integer[]{1, 2, 3, 4, 133, 4, 2});

        String[] arr =  Arrays.stream("TEST".split("")).map(te -> te.toUpperCase()).toArray(size -> new String[size]);

        Arrays.stream(arr).peek(e -> System.out.println("Filtered value: " + e)).sorted().forEach(System.out::println);



        List<String> list = Arrays.stream("TEST".split("")).map(te -> te.toUpperCase()).collect(Collectors.toList());

        list.stream().peek(e -> System.out.println("Filtered value: " + e)).sorted().forEach(System.out::println);


        Integer[] numbers = new Integer[] { 1, 2, 3 };
        List<Integer> l = Arrays.asList(numbers);
        l.stream().forEach(System.out::println);
        Arrays.stream(l.toArray(new Integer[0])).forEach(System.out::println);


        List<String> stringList = List.of("apple", "banana", "cherry");

        // Convert List to Array
        String[] stringArray = stringList.toArray(new String[0]);
        stringArray = stringList.stream().toArray(String[]::new);

    }
}
