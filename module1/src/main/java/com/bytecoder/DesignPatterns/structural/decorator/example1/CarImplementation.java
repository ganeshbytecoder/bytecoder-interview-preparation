package com.bytecoder.DesignPatterns.structural.decorator.example1;

public class CarImplementation implements Car {
    private String name;

    private int price;

    private String model;

    public CarImplementation(String name, String model, int price) {
        this.model = model;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getCarPrice() {
        return this.price;
    }

    @Override
    public String getCarDetails() {
        return this.name + " - " + this.model;
    }
}
