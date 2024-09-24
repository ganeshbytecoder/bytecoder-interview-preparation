package com.bytecoder.java17.oops.inheritance;

public class Animal {

    String name;
    String sound;
    int age;

    public void print(){
        System.out.printf(" name {} sound {} age {}%n", name, sound, age);
    }
}

class Cat extends Animal{

    public Cat(String name, String sound, int age){
        this.name=name;
    }
}
