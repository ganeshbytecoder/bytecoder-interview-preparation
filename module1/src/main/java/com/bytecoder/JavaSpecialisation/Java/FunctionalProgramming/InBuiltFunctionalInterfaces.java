package com.bytecoder.JavaSpecialisation.Java.FunctionalProgramming;



import java.util.Random;
import java.util.function.*;

public class InBuiltFunctionalInterfaces {

    public static void execute(Predicate<String> predicate){
        System.out.println(predicate.test("test string"));
    }

    public static void execute(BiPredicate<String, String> biPredicate){
        System.out.println(biPredicate.test("test", "string"));
    }

    public static void execute(Consumer<String> consumer){
        System.out.println(Thread.currentThread());
        consumer.accept( "test string");
    }

    public static void execute(BiConsumer<String, String> biConsumer){
        System.out.println(Thread.currentThread());
        biConsumer.accept("test", "string");

    }


    public static void main(String[] args) {
        Predicate<String> predicate = (x) -> x.length() > 10;

        execute(predicate);

        BiPredicate<String , String> biPredicate = (str1, str2) -> str1.equals(str2);

        execute(biPredicate);

        Function<String, Integer> function = (str) -> str.length();

        System.out.println(function.apply("test string"));

        BiFunction<String, String, Integer> biFunction = (str1, str2) -> (str1 + str2).length();

        System.out.println(biFunction.apply("string 1", " string 2"));

        Consumer<String> consumer =  (str)-> System.out.println(str);

        execute(consumer);

        BiConsumer<String, String> biConsumer = (str, str2) -> System.out.println("accepts two input arguments " + str + str2);

        execute(biConsumer);

        Random rand = new Random();

        Supplier<Integer> supplier = ()-> rand.nextInt(100);

        System.out.println(supplier.get());
    }
}
