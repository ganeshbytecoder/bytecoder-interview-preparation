package com.bytecoder.DSA.LinearDS.String;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringProblems {
//    multiple values (array2list and list2array
//    strings permutation and cobinations
//    permitive streams
    public static int n =0;
    public static void stringPermutation(String prefix, String str){
        int n = str.length();
        if(prefix.length()==2){
            StringProblems.n+=1;
            System.out.print(prefix + " , ");
        }else {
            for (int i = 0; i < n; i++) {
                stringPermutation(prefix + str.charAt(i), str.substring(i+1)+str.substring(0,i));
            }
        }
    }



    public static void main(String[] args) {
        int [] values = {1,2,4,5,4};

        Arrays.asList(values).forEach(x-> System.out.println(x));

        IntStream.of(1,2,3,4).map(x-> 2*x).forEach(System.out::println);
//        CharStream.of(1,2,3,4).map(x-> x).forEach(System.out::println);

        List<String> list = Arrays.asList("a","b", "c");
        list.stream().flatMap(str1-> list.stream().map(str2-> str1 + str2)).collect(Collectors.toList()).stream().forEach(
                System.out::print
        );

        stringPermutation("", "ABCD");
        System.out.println(StringProblems.n);




    }
}
