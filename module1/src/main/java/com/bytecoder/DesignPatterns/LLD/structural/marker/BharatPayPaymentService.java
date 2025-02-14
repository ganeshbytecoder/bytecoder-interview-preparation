package com.bytecoder.DesignPatterns.LLD.structural.marker;

public class BharatPayPaymentService implements PaymentService,MarkerInterface{
    @Override
    public void execute(String str) {
        System.out.println("Payment service for bharat pay");
    }
}
