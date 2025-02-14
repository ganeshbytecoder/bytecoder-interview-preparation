package com.bytecoder.DesignPatterns.LLD.oops.polymorphism;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * This class demonstrates advanced polymorphism concepts including:
 * - Static vs Dynamic polymorphism
 * - Method overloading resolution
 * - Generic method polymorphism
 * - Double dispatch
 * - Modern Java features
 */
public class AdvancedPolymorphismExample {

    // Example of method overloading with generics
    static class GenericProcessor {
        // Generic method
        public <T> void process(T item) {
            System.out.println("Processing single item: " + item);
        }

        // Generic list method
        public <T> void process(List<T> items) {
            System.out.println("Processing list of items: " + items);
        }

        // Specific type method
        public void process(String item) {
            System.out.println("Processing string specifically: " + item);
        }

        // Varargs method
        public void process(Object... items) {
            System.out.println("Processing varargs: " + List.of(items));
        }
    }

    // Example of double dispatch using visitor pattern
    interface Shape {
        void accept(ShapeVisitor visitor);
    }

    interface ShapeVisitor {
        void visit(Circle circle);
        void visit(Rectangle rectangle);
        void visit(Triangle triangle);
    }

    static class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }

        public double getRadius() {
            return radius;
        }
    }

    static class Rectangle implements Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }

        public double getWidth() { return width; }
        public double getHeight() { return height; }
    }

    static class Triangle implements Shape {
        private double base;
        private double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }

        public double getBase() { return base; }
        public double getHeight() { return height; }
    }

    // Concrete visitor implementing shape-specific operations
    static class AreaCalculator implements ShapeVisitor {
        private double totalArea = 0;

        @Override
        public void visit(Circle circle) {
            totalArea += Math.PI * circle.getRadius() * circle.getRadius();
        }

        @Override
        public void visit(Rectangle rectangle) {
            totalArea += rectangle.getWidth() * rectangle.getHeight();
        }

        @Override
        public void visit(Triangle triangle) {
            totalArea += 0.5 * triangle.getBase() * triangle.getHeight();
        }

        public double getTotalArea() {
            return totalArea;
        }
    }

    // Example of covariant return types
    static class Animal {
        public Animal reproduce() {
            return new Animal();
        }
    }

    static class Dog extends Animal {
        @Override
        public Dog reproduce() {  // Covariant return type
            return new Dog();
        }
    }

    // Example of polymorphic behavior with lambda expressions
    interface Transformer<T> {
        T transform(T input);
    }

    static class StringTransformer implements Transformer<String> {
        @Override
        public String transform(String input) {
            return input.toUpperCase();
        }
    }

    public static void main(String[] args) {
        // Demonstrating method overloading resolution
        GenericProcessor processor = new GenericProcessor();
        processor.process("Hello");  // Calls String-specific method
        processor.process(42);       // Calls generic method
        processor.process(List.of("a", "b", "c")); // Calls list method
        processor.process("x", "y", "z");  // Calls varargs method

        // Demonstrating double dispatch
        List<Shape> shapes = List.of(
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 8)
        );

        AreaCalculator calculator = new AreaCalculator();
        shapes.forEach(shape -> shape.accept(calculator));
        System.out.println("Total area: " + calculator.getTotalArea());

        // Demonstrating covariant return types
        Animal animal = new Dog();
        Dog dog = (Dog) animal.reproduce();  // Safe cast due to covariant return

        // Demonstrating polymorphism with lambda expressions
        Transformer<String> upperCaseTransformer = String::toUpperCase;
        Transformer<String> lowerCaseTransformer = String::toLowerCase;
        
        String text = "Hello World";
        System.out.println(upperCaseTransformer.transform(text));
        System.out.println(lowerCaseTransformer.transform(text));

        // Demonstrating method reference polymorphism
        List<String> strings = new ArrayList<>();
        Consumer<String> consumer = strings::add;  // Method reference
        consumer.accept("Hello");
        consumer.accept("World");
        System.out.println(strings);
    }
}
