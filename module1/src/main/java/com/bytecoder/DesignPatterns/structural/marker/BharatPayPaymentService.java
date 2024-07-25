package com.bytecoder.DesignPatterns.structural.marker;

public class BharatPayPaymentService implements PaymentService,MarkerInterface{
    @Override
    public void execute(String str) {
        System.out.println("Payment service for bharat pay");
    }
}
