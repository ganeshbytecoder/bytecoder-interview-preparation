package com.bytecoder.DesignPatterns.structural.adapter;

public class TradeService {
    public void trade(Rupees rupee) {
        System.out.println("trading with rupees " + rupee.getValue());
    }
}
