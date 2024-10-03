package com.bytecoder;

// not make method or variable class must be public
@FunctionalInterface
interface BaseAnimal {
    int x = 10;

    void print();

    default void printDetails() {
        System.out.println("testing ");
    }
}
class Animal implements BaseAnimal {
    String name;

    int age;

    @Override
    public void print() {
        System.out.println("name  " + this.name);
    }

    public void details() {
        System.out.println("details");
    }

    class Calculator{
        public int sum(int a, int b) {
            System.out.println(age);
            return a + b;

        }

        public int sum(int a, int b, int c) {
            return a + b + c;
        }
    }
}
class Cat extends Animal {
    @Override
    public void print() {
        System.out.printf("name %s age %d %n", name, age);
    }
}

class Thread1 extends Thread{
    @Override
    public void run(){
        Animal animal = new Animal();
        System.out.println(Thread.currentThread().getName());
    }
}
//
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        Cat cat = new Cat();
        cat.name = "cat";
        cat.print();
        cat.details();
        cat.printDetails();
        BaseAnimal cat2 = new Cat();
        cat2.print();
        cat2.printDetails();


        Animal animal = new Animal();
        Animal.Calculator c = animal.new Calculator();
        c.sum(1,2);

        Thread t1 = new Thread1();
        t1.start();
        Thread t2 = new Thread1();
        t2.start();

        System.out.println(t1 instanceof Thread);
    }

}
