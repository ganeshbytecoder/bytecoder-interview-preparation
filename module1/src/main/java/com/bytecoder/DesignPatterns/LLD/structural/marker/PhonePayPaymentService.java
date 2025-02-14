package com.bytecoder.DesignPatterns.LLD.structural.marker;


public class PhonePayPaymentService implements PaymentService{
    @Override
    public void execute(String str) {
        System.out.println("Executing phonePay payment service");
    }
}
