package com.bytecoder.DesignPatterns.structural.bridge;

public class YesBankPaymentMonolithServiceImpl {

    public void makePayment(String paymentMode) {
        if (paymentMode.equals("UPI")) {
            System.out.println("Making payment with " + paymentMode);

        }
        if (paymentMode.equals("DebitCard")) {
            System.out.println("Making payment with " + paymentMode);

        }
        if (paymentMode.equals("Cash")) {
            System.out.println("Making payment with " + paymentMode);

        }

    }
}
