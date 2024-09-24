package com.bytecoder.java17.FunctionalProgramming;

import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalService {
    public static void execute(Filter<Integer, Integer> function) {
        System.out.println(function.execute(10));
    }

    public static void main(String[] args) {
        Filter<Integer, Integer> filter = (value) -> value;
        filter.map((input) -> 10 + input).then((value) -> value * 2).execute(20);

        Function<String, String> converter = (str) -> str + " task 1";
        converter = converter.andThen((str) -> str + " task2 ").andThen((str) -> str + " task3");
        System.out.println(converter.apply("ganesh"));
        System.out.println();

        Function<String, String> function = ((Function<String, String>) s -> "ganesh")
                .andThen((String s) -> s.length())
                .andThen((Integer s) -> "ganesh " + s);

        String st = function.apply("sytarrt");
        System.out.println(st);

        ((Consumer<String>) (String s) -> System.out.println(s)).andThen((str) -> System.out.println(str)).accept("agesg");


        Filter<Integer, Integer> filter1 = ((Filter<Integer, String>) (a) -> String.valueOf(a)).then((s) -> Integer.valueOf(s) + 10).then((a) -> 5 * a);

        execute(filter1);

    }
}
