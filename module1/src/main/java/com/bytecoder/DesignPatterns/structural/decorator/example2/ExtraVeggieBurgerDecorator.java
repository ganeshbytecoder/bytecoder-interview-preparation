package com.bytecoder.DesignPatterns.structural.decorator.example2;

public class ExtraVeggieBurgerDecorator extends BurgerWrapper {
    public ExtraVeggieBurgerDecorator(Burger burger) {
        super(burger);
    }

    @Override
    public String getBurger() {
        return "With extra veggie " + burger.getBurger();
    }
}
