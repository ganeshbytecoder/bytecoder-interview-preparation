package com.gschoudhary.design.patterns.creational;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.creational.abstractfactory.AbstractFactory;
import com.bytecoder.DesignPatterns.creational.abstractfactory.AndroidAbstractFactory;
import com.bytecoder.DesignPatterns.creational.abstractfactory.IOSAbstractFactory;
import com.bytecoder.DesignPatterns.creational.abstractfactory.button.Button;
import com.bytecoder.DesignPatterns.creational.abstractfactory.textbox.TextBox;

class AbstractFactoryTest {
    AbstractFactory iosAbstractFactory;

    AbstractFactory androidAbstractFactory;

    @BeforeEach
    void setUp() {
        iosAbstractFactory = new IOSAbstractFactory();
        androidAbstractFactory = new AndroidAbstractFactory();
    }

    @Test
    void getButton() {
        Button button = androidAbstractFactory.getButton();
        Assertions.assertTrue(button.getButton().contains("android"));
    }

    @Test
    void getTextBox() {
        TextBox textBox = iosAbstractFactory.getTextBox();
        Assertions.assertTrue(textBox.getTextBox().contains("IOS"));
    }
}