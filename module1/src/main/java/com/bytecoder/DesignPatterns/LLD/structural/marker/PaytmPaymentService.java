package com.bytecoder.DesignPatterns.LLD.structural.marker;

public class PaytmPaymentService implements MarkerInterface, PaymentService{
    @Override
    public void execute(String str) {
        System.out.println("Executing paytm payment service");
    }
}
