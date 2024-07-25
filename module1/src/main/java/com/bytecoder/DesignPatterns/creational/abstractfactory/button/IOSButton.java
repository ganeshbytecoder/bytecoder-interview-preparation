package com.bytecoder.DesignPatterns.creational.abstractfactory.button;

public class IOSButton implements Button{
    @Override
    public String getButton() {
        return "IOS button";
    }
}
