package com.bytecoder.DesignPatterns.structural.marker;

public class PaytmPaymentService implements MarkerInterface, PaymentService{
    @Override
    public void execute(String str) {
        System.out.println("Executing paytm payment service");
    }
}
