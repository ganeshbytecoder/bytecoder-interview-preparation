package com.bytecoder.DesignPatterns.structural.decorator.example1;

public class AlloyWheelCarDecorator extends CarDecorator {
    public AlloyWheelCarDecorator(Car car) {
        super(car);
    }

    @Override
    public int getCarPrice() {
        return car.getCarPrice() + 50000;
    }

    @Override
    public String getCarDetails() {
        return car.getCarDetails() + " with alloy wheel";
    }
}
