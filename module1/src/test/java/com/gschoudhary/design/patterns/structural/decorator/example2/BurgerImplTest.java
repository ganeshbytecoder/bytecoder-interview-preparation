package com.gschoudhary.design.patterns.structural.decorator.example2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.structural.decorator.example2.Burger;
import com.bytecoder.DesignPatterns.structural.decorator.example2.BurgerImpl;
import com.bytecoder.DesignPatterns.structural.decorator.example2.CheeseBurgerDecorator;
import com.bytecoder.DesignPatterns.structural.decorator.example2.ExtraVeggieBurgerDecorator;

class BurgerImplTest {
    Burger burger = new BurgerImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBurger() {
        System.out.println(burger.getBurger());
        System.out.println(new CheeseBurgerDecorator(burger).getBurger());
        System.out.println(new ExtraVeggieBurgerDecorator(new CheeseBurgerDecorator(burger)).getBurger());
    }
    @Test
    void getBurger2() {
        System.out.println(burger.getBurger());
        System.out.println(new ExtraVeggieBurgerDecorator(burger).getBurger());
    }
}