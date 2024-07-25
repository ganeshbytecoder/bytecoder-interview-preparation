package com.bytecoder.DesignPatterns.structural.decorator.example1;

public class ColorCarDecorator extends CarDecorator {
    private String color;

    public ColorCarDecorator(Car car, String color)  {
        super(car);
        this.color = color;
    }

    @Override
    public int getCarPrice() {
        return car.getCarPrice() + 20000;
    }

    @Override
    public String getCarDetails() {
        return car.getCarDetails() + " with " + this.color + " color ";
    }
}
