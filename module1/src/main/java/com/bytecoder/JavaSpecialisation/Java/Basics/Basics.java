package com.bytecoder.JavaSpecialisation.Java.Basics;

public class Basics {

    public static void main(String[] args) {
        int a  = (int) Integer.parseInt("1");


        System.out.println();

        System.out.println(Character.toChars(123)); // 'A'
        System.out.println(Character.getNumericValue(123)); // 'A'
        System.out.println(Character.toUpperCase('a')); // 'A'
        System.out.println(Character.toUpperCase('A')); // 'A' (no change)




//      Checking Character Properties
        boolean c = Character.isAlphabetic('a');

//        isLetter(char ch):
        System.out.println(Character.isLetter('A')); // true
        System.out.println(Character.isLetter('1')); // false

//      isDigit(char ch):
        System.out.println(Character.isDigit('5')); // true
        System.out.println(Character.isDigit('A')); // false

//      isWhitespace(char ch):


        System.out.println(Character.isUpperCase('A')); // true
        System.out.println(Character.isUpperCase('a')); // false


        System.out.println(Character.isLowerCase('a')); // true
        System.out.println(Character.isLowerCase('A')); // false



        System.out.println(Character.isLetterOrDigit('5')); // true
        System.out.println(Character.isLetterOrDigit('@')); // false


    }
}
